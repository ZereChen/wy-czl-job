package com.czl.redis;

import com.czl.entity.user.AccessAuthEntity;
import com.czl.entity.user.UserEntity;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author zerechen
 * @date 2017/11/14 下午2:57
 * @description
 */
public class RedisServiceTemp {

    public static Map<String, AccessAuthEntity> accessAuthMap = Maps.newHashMap();

    public static Map<String, UserEntity> userMap = Maps.newHashMap();
}
