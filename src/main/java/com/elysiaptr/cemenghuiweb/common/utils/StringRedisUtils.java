package com.elysiaptr.cemenghuiweb.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class StringRedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        getValueOperations().set(key, value);
    }

    public String get(String key) {
        return getValueOperations().get(key);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    private ValueOperations<String, String> getValueOperations() {
        return stringRedisTemplate.opsForValue();
    }
}
