package com.jobayour.jwt;

import com.jobayour.modules.user.User;
import com.jobayour.modules.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        List<User> userList = userRepository.findUserByUserId(userId);

        User user = userList.stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없음. 사용자명: " + userId));

        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getUserPwd(),
                Collections.emptyList()
        );
    }
}