package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class JwtUserController {

    @Autowired
    private JwtUserService jwtuserService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JwtExceptionFilter jwtExceptionFilter;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return jwtuserService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return jwtuserService.loginUser(user);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            String userId = jwtTokenProvider.getUserId(token);
            jwtTokenProvider.deleteRefreshToken(userId);
        }
    }

    @GetMapping("/findUserId")
    public String findUserId(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        return jwtTokenProvider.getUserId(token);
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userId = jwtTokenProvider.getUserId(token);
            return jwtuserService.getUserInfo(userId);
        }
        return null;
    }

    @PutMapping("/modify")
    public void modifyUser(HttpServletRequest request, @RequestBody Map<String, String> updateData) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String userId = jwtTokenProvider.getUserId(token);
            jwtuserService.modifyUser(userId, updateData);
        }
    }
}