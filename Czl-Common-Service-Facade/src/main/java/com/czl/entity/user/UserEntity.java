package com.czl.entity.user;

import com.czl.enumeration.user.UserStateEnum;
import com.czl.enumeration.user.UserTypeEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author zerechen
 * @Date 2017/10/30 下午6:11
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

    /** 邮箱 */
    private String mail;

    /** 注册时间 */
    private Timestamp registerTime;

    /** 用户类型 {@link com.czl.enumeration.user.UserTypeEnum} */
    private UserTypeEnum userTypeEnum;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", registerTime=" + registerTime +
                ", userTypeEnum=" + userTypeEnum +
                ", userStateEnum=" + userStateEnum +
                ", roleEntity=" + roleEntity +
                '}';
    }
}
