package com.czl.user.dao;

import com.czl.entity.user.*;
import com.czl.req.user.LocationUpdateReq;
import com.czl.req.user.RoleMenuReq;
import com.czl.req.user.RolePermissionReq;
import com.czl.req.user.UserQueryReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zerechen
 * @date 2017/11/1 下午8:37
 * @description 用户相关DAO
 */
@Mapper
public interface UserDAO {

    /**
     * 查询用户信息
     * @param userQueryReq 查询请求
     * @return 查询结果
     */
    List<UserEntity> findUsers(@Param("userQueryReq") UserQueryReq userQueryReq);


    /**
     * 删除角色-权限关系
     * @param roleId 角色ID
     */
    void deleteRolePermission(String roleId);
    /**
     * 插入角色-权限关系
     * @param rolePermissionReq
     */
    void insertRolePermission(@Param("rolePermissionReq") RolePermissionReq rolePermissionReq);

    /**
     * 查询所有权限
     * @return 权限列表
     */
    List<PermissionEntity> findPermissions();

}
