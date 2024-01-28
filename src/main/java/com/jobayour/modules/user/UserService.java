package com.jobayour.modules.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //유저 전체 리스트 조회
    public List<UserDTO> findAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> list = userList.stream()
                .sorted(Comparator.comparing(User::getUserId))
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //유저 아이디 찾기
    public List<UserDTO> findUserById(String id){
        List<User> userList = userRepository.findUserByUserId(id);
        List<UserDTO> list = userList.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //유저 추가
    public void addUser(User userinfo) {
        User user = new User();
        user.setUserId(userinfo.getUserId());
        user.setUserPwd(userinfo.getUserPwd());
        user.setUserName(userinfo.getUserName());
        user.setUserBirthday(userinfo.getUserBirthday());
        user.setUserPhone(userinfo.getUserPhone());
        user.setUserEmail(userinfo.getUserEmail());
        userRepository.save(modelMapper.map(user, User.class));
    }

    //유저 수정
    @Transactional
    public void modifyUser(User userinfo) {
        User user = userRepository.findById(userinfo.getUserId()).orElseThrow(IllegalArgumentException::new);
        user.setUserId(userinfo.getUserId());
        user.setUserPwd(userinfo.getUserPwd());
        user.setUserName(userinfo.getUserName());
        user.setUserBirthday(userinfo.getUserBirthday());
        user.setUserPhone(userinfo.getUserPhone());
        user.setUserEmail(userinfo.getUserEmail());
    }

    //유저 삭제
    @Transactional
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
