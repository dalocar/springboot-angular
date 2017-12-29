package com.carrascolimited.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrascolimited.springboot.service.UserService;
import com.carrascolimited.springboot.vo.CreateUserVO;
import com.carrascolimited.springboot.vo.UpdateUserVO;
import com.carrascolimited.springboot.vo.UserVO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserVO getUser(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserVO> getUsers() {
		return userService.findAll();
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserVO createUser(@Valid @RequestBody CreateUserVO userVO) {
		return  userService.createUser(userVO);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public UserVO updateUser(@RequestBody UpdateUserVO updateUserVO) {
		return userService.updateUser(updateUserVO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "OK";
	}
}