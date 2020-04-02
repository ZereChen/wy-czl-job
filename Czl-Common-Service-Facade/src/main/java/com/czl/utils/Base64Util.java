package com.czl.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util {
    public static String encode(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.encodeBase64String(b);
        }
        return s;
    }

    // 解密
    public static String decode(String str) {
        byte[] b = null;
        String s = null;
        try {
           b = Base64.decodeBase64(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new String(b);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(encode("czl"));
        System.out.println(decode("czl"));
    }
}