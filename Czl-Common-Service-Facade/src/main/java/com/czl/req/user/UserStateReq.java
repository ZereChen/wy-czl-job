package com.czl.req.user;

import com.czl.req.AbsReq;

/**
 * @author zerechen
 * @date 2017/11/4 下午3:45
 * @description 用户状态处理请求
 */
public class UserStateReq extends AbsReq {

    /** 用户ID */
    private String userId;

    /** 用户状态 {@link com.czl.enumeration.user.UserStateEnum} */
    private int userState;

    public UserStateReq(String userId, int userState) {
        this.userId = userId;
        this.userState = userState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }
}
