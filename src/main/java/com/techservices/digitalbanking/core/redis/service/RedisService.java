/*
 * Copyright (c) 2025 Rova Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of Rova Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * Rova Engineering Team
 * Email: engineering@rova.com
 */ 
package com.techservices.digitalbanking.core.redis.service;

import com.techservices.digitalbanking.core.redis.configuration.RedisProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisProperty redisProperty;

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, Long.parseLong(redisProperty.getTtlMinutes()),
                TimeUnit.MINUTES);
    }
    public void saveWithCustomExpiration(String key, Object value, Integer expirationInMinutes) {
        redisTemplate.opsForValue().set(key, value, expirationInMinutes.longValue(), TimeUnit.MINUTES);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> T getAndRefresh(String key, Class<T> clazz) {
        Object rawValue = redisTemplate.opsForValue().get(key);
        if (rawValue != null) {
            redisTemplate.expire(key, Duration.ofMinutes(Long.parseLong(redisProperty.getTtlMinutes())));
            return clazz.cast(rawValue);
        }
        return null;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void expire(String key) {
        redisTemplate.expire(key, Long.parseLong(redisProperty.getTtlMinutes()), TimeUnit.MINUTES);
    }

    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
