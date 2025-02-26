package com.test.lsy.redistest.user.service;

import com.test.lsy.redistest.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("userService2")
@Slf4j
@RequiredArgsConstructor
public class UserService2 {

    private final UserRepository repository;
    private final RedisTemplate redisTemplate;

    @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public User getUserData(Long id) {
        return repository.findById(id).orElse(null);
    }
}
