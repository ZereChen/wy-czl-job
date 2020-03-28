package com.czl.facade.user;

import com.czl.entity.user.*;
import com.czl.req.BatchReq;
import com.czl.req.user.*;

import java.util.List;

/**
 * @author zerechen
 * @description
 */
public interface UserService {

    public UserEntity login(LoginReq loginReq);

    void updatePermissionOfRole(RolePermissionReq rolePermissionReq);

    List<PermissionEntity> findPermissions();

}
