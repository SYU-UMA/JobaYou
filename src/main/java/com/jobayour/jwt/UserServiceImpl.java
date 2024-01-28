package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import com.jobayour.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements JwtUserService {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            //동일 ID일 시
            throw new DuplicateKeyException("중복된 아이디입니다.");
        }

        return userRepository.save(user);
    }

    @Override
    public String loginUser(User user) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserId());

            if (!userDetails.getPassword().equals(user.getUserPwd())) {
                throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
            }
            return generateAccessToken(userDetails.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.", e);
        }
    }
    private String generateAccessToken(String userId) {
        return jwtTokenProvider.createToken(userId, Collections.emptyList()).get("accessToken");
    }
    @Override
    public Map<String, Object> getUserInfo(String userId) {
        User user = userRepository.findUserByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없음."));
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", user.getUserId());
        userInfo.put("userPwd", user.getUserPwd());
        userInfo.put("userName", user.getUserName());
        userInfo.put("userBirthday", user.getUserBirthday());
        userInfo.put("userPhone", user.getUserPhone());
        userInfo.put("userEmail", user.getUserEmail());

        return userInfo;

    }

    @Override
    public Map<String, Object> modifyUser(String userId, Map<String, String> updateData) {
        User user = userRepository.findUserByUserId(userId)
                .stream()
                .findFirst()
                .orElseThrow(null);

        // 제공된 데이터를 기반으로 사용자 정보를 업데이트합니다.
        if (updateData.containsKey("userName")) {
            user.setUserName(updateData.get("userName"));
        }
        if (updateData.containsKey("userBirthday")) {
            // userBirthday가 "yyyy-MM-dd" 형식으로 가정합니다.
            user.setUserBirthday(updateData.get("userBirthday"));
        }
        if (updateData.containsKey("userPhone")) {
            user.setUserPhone(updateData.get("userPhone"));
        }
        if (updateData.containsKey("userEmail")) {
            user.setUserEmail(updateData.get("userEmail"));
        }
        if (updateData.containsKey("userPwd")){
            user.setUserPwd(updateData.get("userPwd"));
        }

        // 업데이트된 사용자 정보를 데이터베이스에 저장합니다.
        userRepository.save(user);

        // 업데이트된 사용자 정보를 반환합니다.
        return getUserInfo(userId);
    }
}
