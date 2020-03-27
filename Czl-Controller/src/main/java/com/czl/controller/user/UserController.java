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


    /**
     * 查询所有角色
     * @return 返回所有角色
     */
    @GetMapping("/role")
    @Login
    @Permission("role:query")
    public Result<List<RoleEntity>> findRoles();

    /**
     * 删除指定角色
     * @param roleId 角色ID
     * @return 是否删除成功
     */
    @DeleteMapping("/role")
    @Login
    @Permission("role:delete")
    public Result deleteRole(String roleId);


    /**
     * 修改角色对应的权限
     * @param rolePermissionReq 角色-权限的修改请求
     * @return 是否修改成功
     */
    @PutMapping("/permissionOfRole")
    @Login
    @Permission("permissionOfRole:update")
    public Result updatePermissionOfRole(RolePermissionReq rolePermissionReq);

    /**
     * 查询所有的权限
     * @return 所有的权限列表
     */
    @GetMapping("permission")
    @Login
    @Permission("permission:query")
    public Result<List<PermissionEntity>> findPermissions();

}
