package com.czl.entity.user;

import com.czl.enumeration.user.UserStateEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zerechen
 */
public class UserEntity implements Serializable {

    /** 主键 */
    private String id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 手机号 */
    private String phone;

    /** 昵称 */
    private String nickname;

    /** 注册时间 */
    private Timestamp registerTime;


    /** 账号状态 {@link com.czl.enumeration.user.UserStateEnum } */
    private UserStateEnum userStateEnum;

    /** 用户的角色 */
    private RoleEntity roleEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", registerTime=" + registerTime +
                ", userStateEnum=" + userStateEnum +
                ", roleEntity=" + roleEntity +
                '}';
    }

    public UserStateEnum getUserStateEnum() {
        return userStateEnum;
    }

    public void setUserStateEnum(UserStateEnum userStateEnum) {
        this.userStateEnum = userStateEnum;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }


}
