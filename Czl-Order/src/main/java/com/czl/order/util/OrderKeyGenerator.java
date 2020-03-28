package com.czl.order.util;

import com.czl.enumeration.KeyGeneratorPrefixEnum;
import com.czl.utils.KeyGenerator;

/**
 * @author zerechen
 * @date 2017/11/7 上午9:40
 * @description 订单主键生成器
 */
public class OrderKeyGenerator {


    public static String generateOrderKey() {
        return KeyGenerator.getKey(KeyGeneratorPrefixEnum.ORDER_ID_PREFIX);
    }

}
