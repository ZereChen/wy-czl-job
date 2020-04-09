package com.czl.JWT;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.czl.entity.user.UserEntity;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.redis.RedisService;
import com.czl.utils.Base64Util;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Enumeration;

@Component
public class JwtTokenUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "x-token";

    public static final String TOKEN_PREFIX = "Bearer ";
    @Autowired
    private RedisService redisService;
    /**
     * 解析jwt
     * @param request
     * @param base64Security
     * @return
     */
    public  Claims parseJWT(HttpServletRequest request, String base64Security) {
        try {
            String jsonWebToken = getToken(request);
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException  eje) {
            logger.error("===== Token Expire=====", eje);
            throw new CommonBizException(ExpCodeEnum.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e){
            logger.error("===== Token Invalid Exception =====", e);
            throw new CommonBizException(ExpCodeEnum.PERMISSION_TOKEN_INVALID);
        }
    }

    /**
     * 构建jwt
     * @param userId
     * @param username
     * @param role
     * @param audience
     * @return
     */
    public  String createJWT(String userId, String username, String role, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //userId是重要信息，进行加密下
            String encryId = Base64Util.encode(userId);

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("role", role)
                    .claim("userId", encryId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            logger.error("签名失败", e);
            throw new CommonBizException(ExpCodeEnum.PERMISSION_SIGNATURE_ERROR);
        }
    }

    /**
     * 从request中获取用户名
     * @param request
     * @param base64Security
     * @return
     */
    public  String getUsername(HttpServletRequest request, String base64Security){
        return parseJWT(request, base64Security).getSubject();
    }

    /**
     * 从request中获取用户ID
     * @param request
     * @param audience
     * @return
     */
    public String getUserId(HttpServletRequest request, Audience audience){
        String userId = parseJWT(request, audience.getBase64Secret()).get("userId", String.class);
        return Base64Util.decode(userId);
    }

    /**
     * 获取UserEntity
     * @param request
     * @param audience
     * @return
     */
    public  UserEntity getUserEntity(HttpServletRequest request,Audience audience){
        String userId = getUserId(request,audience);
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        Object userEntity = redisService.get(userId);
        if (userEntity == null) {
            return null;
        }
        return (UserEntity) userEntity;
    }
    /**
     * token是否已过期
     * @param request
     * @param base64Security
     * @return
     */
    public  boolean isExpiration(HttpServletRequest request, String base64Security) {
        return parseJWT(request, base64Security).getExpiration().before(new Date());
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {

        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
//        final String authHeader1 = request.getHeader("access-control-request-headers");
//        logger.info("## authHeader= {}", authHeader1);

        //检测是否登录
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            logger.info("### 用户未登录，请先登录 ###");
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }

        // 返回token
        return authHeader.substring(7);
    }
}
