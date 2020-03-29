package com.czl.user.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.czl.entity.user.*;
import com.czl.enumeration.KeyGeneratorPrefixEnum;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.user.UserService;
import com.czl.req.user.*;
import com.czl.user.dao.UserDAO;
import com.czl.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zerechen
 * @description 用户相关操作
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public UserEntity login(LoginReq loginReq) {

        // 创建用户查询请求
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);

        // 查询用户
        List<UserEntity> userEntityList = userDAO.findUsers(userQueryReq);

        // 查询失败
        if (CollectionUtils.isEmpty(userEntityList)) {
            return null;
        }

        // 查询成功
        return userEntityList.get(0);
    }

    /**
     * 构造用户查询对象
     * @param loginReq 登录请求
     * @return 用户查询请求
     */
    private UserQueryReq buildUserQueryReq(LoginReq loginReq) {
        UserQueryReq userQueryReq = new UserQueryReq();
        // 设置密码
        if (StringUtils.isNotEmpty(loginReq.getPassword())) {
            userQueryReq.setPassword(loginReq.getPassword());
        }
        // 设置用户名
        if (StringUtils.isNotEmpty(loginReq.getUsername())) {
            userQueryReq.setUsername(loginReq.getUsername());
        }
        // 设置手机号
        if (StringUtils.isNotEmpty(loginReq.getPhone())) {
            userQueryReq.setPhone(loginReq.getPhone());
        }

        return userQueryReq;
    }
    /**
     * 生成User主键('USER'+UUID)
     * @return 主键
     */
    private String generateKey() {
        return  KeyGenerator.getKey(KeyGeneratorPrefixEnum.USER_PREFIX);
    }

}
