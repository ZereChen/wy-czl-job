package com.czl.order.util;

import com.czl.utils.KeyGenerator;

/**
 * @author zerechen
 * @date 2017/11/7 上午9:40
 * @description 订单主键生成器
 */
public class OrderKeyGenerator {

    private static final String ORDER_ID_PREFIX = "ORDER";

    public static String generateOrderKey() {
        return ORDER_ID_PREFIX + KeyGenerator.getKey();
    }

}
