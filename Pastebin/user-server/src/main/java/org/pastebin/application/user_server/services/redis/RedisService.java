package org.pastebin.application.user_server.services.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String HASH_KEY, String key, Object value) {
        redisTemplate.opsForHash().put(HASH_KEY, key, value);
    }

    public Object get(String HASH_KEY, String key) {
        return redisTemplate.opsForHash().get(HASH_KEY, key);
    }

    public void delete(String HASH_KEY, String key) {
        redisTemplate.opsForHash().delete(HASH_KEY, key);
    }
}
