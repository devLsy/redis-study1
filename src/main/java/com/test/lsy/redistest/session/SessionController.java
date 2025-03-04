package com.test.lsy.redistest.session;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class SessionController {

    // 세션에 데이터 저장
    @PostMapping("/session/set")
    public String setSession(@RequestParam String key, @RequestParam String value, HttpSession session) {
        session.setAttribute(key, value);  // 세션에 key-value 저장
        return "Session data saved: " + key + " = " + value;
    }

    // 세션에서 데이터 조회
    @GetMapping("/session/get")
    public String getSession(@RequestParam String key, HttpSession session) {
        Object value = session.getAttribute(key);  // 세션에서 데이터 조회
        if (value != null) {
            return "Session data: " + key + " = " + value;
        } else {
            return "No data found for key: " + key;
        }
    }

    // 세션 삭제
    @PostMapping("/session/remove")
    public String removeSession(@RequestParam String key, HttpSession session) {
        session.removeAttribute(key);  // 세션에서 데이터 삭제
        return "Session data removed for key: " + key;
    }
}
