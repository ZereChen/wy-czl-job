package com.czl.enumeration;

/**
 * @author zerechen
 * @description HTTP请求方式的枚举类
 */
public enum KeyGeneratorPrefixEnum {
    /**
     * 用户表主键的前缀
     */
    USER_PREFIX(1, "USER"),
    /**
     * SessionID的前缀
     */
    SESSION_ID_PREFIX(3, "SESSION"),
    /**
     * 订单的前缀
     **/
    ORDER_ID_PREFIX(4, "ORDER"),
    /**
     * 产品的前缀
     **/
    PRODUCT_ID_PREFIX(5, "PRODUCT"),
    /**
     * 购物车的前缀
     **/
    CAR_ID_PREFIX(6, "CAR");

    private int code;
    private String msg;

    KeyGeneratorPrefixEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
