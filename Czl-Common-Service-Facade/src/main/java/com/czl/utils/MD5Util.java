package com.czl.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String encode(String str){
        return DigestUtils.md5Hex(str);
    }
    public static void main(String[] args) {
        System.out.println(encode("reyub"));
    }
}
