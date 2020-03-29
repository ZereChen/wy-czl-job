package com.czl.controller.user;

import com.czl.annotation.Login;
import com.czl.annotation.Permission;
import com.czl.entity.user.*;
import com.czl.req.BatchReq;
import com.czl.req.user.*;
import com.czl.rsp.Result;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author zerechen
 */
@RestController
@CrossOrigin
public interface UserController {

    /**
     * 登录
     * @param loginReq 登录请求参数
     * @param httpRsp HTTP响应
     * @return 登录是否成功
     */
    @GetMapping("/login")
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp);

    /**
     * 退出
     * @param httpReq HTTP请求
     * @param httpRsp HTTP响应
     * @return 是否登出成功
     */
    @GetMapping("/logout")
    @Login
    public Result logout(HttpServletRequest httpReq, HttpServletResponse httpRsp);

    /**
     * 判断指定用户是否登录
     * @return
     */
    @GetMapping("/isLogin")
    @Login
    public Result isLogin(HttpServletRequest request);

}
