package com.czl.req.user;

import com.czl.req.QueryReq;

/**
 * @author zerechen
 * @description 用户查询请求
 */
public class UserQueryReq extends QueryReq {

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

    /** 注册时间(起始) */
    private String registerTimeStart;
    /** 注册时间(结束) */
    private String registerTimeEnd;


    /** 账号状态 {@link com.czl.enumeration.user.UserStateEnum } */
    private Integer userState;

    /** 用户角色ID */
    private String roleId;


    /** 根据注册时间排序 {@link com.czl.enumeration.OrderEnum} */
    private Integer orderByRegisterTime;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Integer getUserState() {
        return userState;
    }

    @Override
    public String toString() {
        return "UserQueryReq{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", registerTimeStart='" + registerTimeStart + '\'' +
                ", registerTimeEnd='" + registerTimeEnd + '\'' +
                ", userState=" + userState +
                ", roleId='" + roleId + '\'' +
                ", orderByRegisterTime=" + orderByRegisterTime +
                '}';
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRegisterTimeStart() {
        return registerTimeStart;
    }

    public void setRegisterTimeStart(String registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }

    public String getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(String registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
    }

    public Integer getOrderByRegisterTime() {
        return orderByRegisterTime;
    }

    public void setOrderByRegisterTime(Integer orderByRegisterTime) {
        this.orderByRegisterTime = orderByRegisterTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
