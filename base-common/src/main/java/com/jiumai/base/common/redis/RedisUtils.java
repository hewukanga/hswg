package com.jiumai.base.common.redis;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // 锁前缀
    private static final String REDIS_LOCK_PREFIX = "lock:";
    // 锁超时时间（秒）
    public static final long REDIS_LOCK_TTL = 15L;

    /**
     * 加锁
     *
     * @param lockKey   锁名称
     * @param lockValue 锁的值
     * @return
     */
    public boolean lock(String lockKey, String lockValue) {
        //判断是否上锁成功
        Boolean ifPresent = false;
        try {
            ifPresent = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_PREFIX + lockKey, lockValue, REDIS_LOCK_TTL, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("【redis锁】加锁失败", e);
        }

        return ifPresent == null ? false : ifPresent;

    }


    /**
     * 释放锁
     *
     * @param lockKey
     */
    public void unLock(String lockKey, String lockValue) {
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(lockValue)) {
            log.error("【redis锁】解锁失败，lockKey：{}，lockValue：{}。", lockKey, lockValue);
            return;
        }
        try {
            String redisLockKey = REDIS_LOCK_PREFIX + lockKey;
            String redisLockValue = stringRedisTemplate.opsForValue().get(redisLockKey);
            stringRedisTemplate.watch(redisLockKey);
            if (lockValue.equals(redisLockValue)) {
                //获取到了并且值匹配才释放 说明是当前线程加锁并且释放锁 防止其他线程释放掉不属于自己的锁
                stringRedisTemplate.delete(redisLockKey);
            } else {
                log.error("解锁失败，lockKey：{}，lockValue：{}，redisValue：{}", redisLockKey, lockValue, redisLockValue);
            }
            stringRedisTemplate.unwatch();
        } catch (Exception e) {
            log.error("【redis锁】解锁失败，lockKey：{}，lockValue：{}，等待锁过期", lockKey, lockValue, e);
        }
    }

}
