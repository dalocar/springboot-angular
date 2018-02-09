package com.carrascolimited.springboot.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.belikesoftware.security.repository.UserAuthRepository;
import com.carrascolimited.springboot.assembler.UserAssembler;
import com.carrascolimited.springboot.domain.User;
import com.carrascolimited.springboot.exception.ResourceNotFoundException;
import com.carrascolimited.springboot.repo.UserRepository;
import com.carrascolimited.springboot.vo.CreateUserVO;
import com.carrascolimited.springboot.vo.UpdateUserVO;
import com.carrascolimited.springboot.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAssembler userAssembler;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAuthRepository userAuthRepository;

	public UserVO getUserById(Long id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new ResourceNotFoundException(id, "user not found");
		}
		return userAssembler.toUserVO(user);
	}

	public UserVO createUser(CreateUserVO createUser) {
		User user = userAssembler.toUser(createUser);
		return userAssembler.toUserVO(userRepository.save(user));
	}

	public UserVO updateUser(UpdateUserVO updateUser) {
		User user = userRepository.getOne(updateUser.getId());
		user.setFirstName(updateUser.getFirstName());
		user.setLastName(updateUser.getLastName());
		user.setUsername(updateUser.getUserName());
		user.setEmail(updateUser.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(updateUser.getPassword()));
		user.setUpdated(Date.valueOf(LocalDate.now()));
		return userAssembler.toUserVO(userRepository.save(user));
	}

	public void deleteUser(Long id) {
		UserVO user = getUserById(id);
		if (user != null) {
			userRepository.delete(id);
		}
	}
	
	public List<UserVO> findAll() {
		List<UserVO> result = new ArrayList<>();
		userRepository.findAll().forEach(u -> result.add(userAssembler.toUserVO(u)));
		return result;
	}
}