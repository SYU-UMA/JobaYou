package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

public interface JwtUserService {
    User registerUser(User user);
    String loginUser(User user);
    Map<String, Object> getUserInfo(String userId) throws UsernameNotFoundException;
    Map<String, Object> modifyUser(String userId, Map<String, String> updateData);

}