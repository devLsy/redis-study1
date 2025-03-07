package com.test.lsy.redistest.user.service;

import com.test.lsy.redistest.user.model.User;
import com.test.lsy.redistest.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("userService1")
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RedisTemplate redisTemplate;

    public User getUserData(Long id) {

        long startTime = System.currentTimeMillis();
        String cacheKey = "user:" + id;

        User cachedData = (User) redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            log.info("redis data exist~");
            long endTime = System.currentTimeMillis(); // 끝 시간 측정
            long elapsedTime = endTime - startTime; // 시간 차이 (밀리초 단위)
            log.info("Cache retrieval time: {} ms", elapsedTime);
            return cachedData;
        }

        log.info("redis data not exist~");
        long dbStartTime = System.currentTimeMillis(); // DB 조회 시작 시간
        User findUser = repository.findById(id).orElse(null);

        if (findUser != null) {
            redisTemplate.opsForValue().set(cacheKey, findUser, 400, TimeUnit.SECONDS);
        }

        long dbEndTime = System.currentTimeMillis(); // DB 조회 끝 시간
        long dbElapsedTime = dbEndTime - dbStartTime; // DB 조회 시간
        long endTime = System.currentTimeMillis(); // 전체 종료 시간
        long totalElapsedTime = endTime - startTime; // 전체 시간

        log.info("DB retrieval time: {} ms", dbElapsedTime);
        log.info("Total time: {} ms", totalElapsedTime);

        return findUser;
    }
}
