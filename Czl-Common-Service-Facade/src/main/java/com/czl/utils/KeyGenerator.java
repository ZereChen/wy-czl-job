package com.czl.utils;

import com.czl.enumeration.KeyGeneratorPrefixEnum;

import java.util.UUID;

/**
 * @author zerechen
 * @description 主键生成器
 */
public class KeyGenerator {
    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getKey(KeyGeneratorPrefixEnum keyGeneratorPrefixEnum){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return keyGeneratorPrefixEnum.getMsg() + uuid.replaceAll("-", "");
    }
    public static String getKey(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
    public static void main(String[] args) {
        System.out.println(getKey(KeyGeneratorPrefixEnum.USER_PREFIX));
    }
}
