package com.czl.facade.user;

import com.czl.entity.user.*;
import com.czl.req.BatchReq;
import com.czl.req.user.*;

import java.util.List;

/**
 * @author zerechen
 * @date 2017/11/1 上午10:06
 * @description
 */
public interface UserService {

    public UserEntity login(LoginReq loginReq);

    public List<UserEntity> findUsers(UserQueryReq userQueryReq);

    List<RoleEntity> findRoles();

    void deleteRole(String roleId);

    void updatePermissionOfRole(RolePermissionReq rolePermissionReq);

    List<PermissionEntity> findPermissions();

}
