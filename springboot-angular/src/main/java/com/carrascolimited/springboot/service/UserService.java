package com.carrascolimited.springboot.service;

import java.util.List;

import com.carrascolimited.springboot.vo.CreateUserVO;
import com.carrascolimited.springboot.vo.UpdateUserVO;
import com.carrascolimited.springboot.vo.UserVO;

public interface UserService {

    UserVO getUserById(Long id);

    UserVO createUser(CreateUserVO user);

    UserVO updateUser(UpdateUserVO user);

    void deleteUser(Long id);

    List<UserVO> findAll();
}