package com.czl.user.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.czl.entity.user.*;
import com.czl.enumeration.user.UserStateEnum;
import com.czl.enumeration.user.UserTypeEnum;
import com.czl.exception.CommonBizException;
import com.czl.exception.ExpCodeEnum;
import com.czl.facade.user.UserService;
import com.czl.req.BatchReq;
import com.czl.req.user.*;
import com.czl.user.dao.UserDAO;
import com.czl.utils.KeyGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author zerechen
 * @description 用户相关操作
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /** 用户表主键的前缀 */
    private static final String USER_KEY_PREFIX = "USER";

    @Override
    public UserEntity login(LoginReq loginReq) {

        // 校验参数
        if (StringUtils.isEmpty(loginReq.getPassword())) {
            throw new CommonBizException(ExpCodeEnum.PASSWD_NULL);
        }
        if (StringUtils.isEmpty(loginReq.getUsername())) {
            throw new CommonBizException(ExpCodeEnum.AUTH_NULL);
        }

        // 创建用户查询请求
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);

        // 查询用户
        List<UserEntity> userEntityList = userDAO.findUsers(userQueryReq);

        // 查询失败
        if (CollectionUtils.isEmpty(userEntityList)) {
            throw new CommonBizException(ExpCodeEnum.LOGIN_FAIL);
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
        userQueryReq.setPassword(loginReq.getPassword());
        // 设置用户名
        if (StringUtils.isNotEmpty(loginReq.getUsername())) {
            userQueryReq.setUsername(loginReq.getUsername());
        }
        // 设置邮箱
        if (StringUtils.isNotEmpty(loginReq.getMail())) {
            userQueryReq.setMail(loginReq.getMail());
        }
        // 设置手机号
        if (StringUtils.isNotEmpty(loginReq.getPhone())) {
            userQueryReq.setPhone(loginReq.getPhone());
        }

        return userQueryReq;
    }

    @Override
    public List<UserEntity> findUsers(UserQueryReq userQueryReq) {
        return userDAO.findUsers(userQueryReq);
    }

    @Override
    public List<RoleEntity> findRoles() {
        return userDAO.findRoles();
    }

    @Override
    public void deleteRole(String roleId) {
        // 参数校验
        if (StringUtils.isEmpty(roleId)) {
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }

        // 删除角色
        userDAO.deleteRole(roleId);

        // 删除角色-权限关系
        userDAO.deleteRolePermission(roleId);

    }


    @Override
    public void updatePermissionOfRole(RolePermissionReq rolePermissionReq) {
        // 参数校验
        checkParam(rolePermissionReq);

        // 删除该角色下的所有权限
        userDAO.deleteRolePermission(rolePermissionReq.getRoleId());

        // 插入该角色下的新权限
        userDAO.insertRolePermission(rolePermissionReq);
    }

    @Override
    public List<PermissionEntity> findPermissions() {
        return userDAO.findPermissions();
    }


    private void checkParam(RolePermissionReq rolePermissionReq) {
        // 参数不能为空
        if (rolePermissionReq==null) {
            throw new CommonBizException(ExpCodeEnum.PARAM_NULL);
        }

        // roleId不能为空 & TODO roleId 必须存在
        if (StringUtils.isEmpty(rolePermissionReq.getRoleId())) {
            throw new CommonBizException(ExpCodeEnum.ROLEID_NULL);
        }

        // 权限Id列表不能为空 & 权限Id必须都存在
        if (CollectionUtils.isEmpty(rolePermissionReq.getPermissionIdList())) {
            throw new CommonBizException(ExpCodeEnum.PERMISSIONIDLIST_NULL);
        }
    }

    /**
     * 生成User主键('USER'+UUID)
     * @return 主键
     */
    private String generateKey() {
        return USER_KEY_PREFIX + KeyGenerator.getKey();
    }

}
