package com.erp.erpbackend.services.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;


@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedissonClient redissonClient;

    public <T> void set(String key, T value, long minute) {
        set(key, value, Duration.ofMinutes(minute));
    }

    public <T> void set(String key, T value, Duration duration) {
        RBucket<T> savedKey = redissonClient.getBucket(key);
        savedKey.set(value, duration);
    }

    public <T> T get(String key) {
        RBucket<T> keyFromRedis = redissonClient.getBucket(key);
        return keyFromRedis.get();
    }

}
