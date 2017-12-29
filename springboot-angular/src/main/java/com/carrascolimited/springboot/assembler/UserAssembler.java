package com.carrascolimited.springboot.assembler;

import org.springframework.stereotype.Component;

import com.carrascolimited.springboot.domain.User;
import com.carrascolimited.springboot.util.UserUtil;
import com.carrascolimited.springboot.vo.CreateUserVO;
import com.carrascolimited.springboot.vo.UpdateUserVO;
import com.carrascolimited.springboot.vo.UserVO;

@Component
public class UserAssembler {

	/**
	 * CreateUserVO convert to User.
	 *
	 * @param createUserVO
	 * @return
	 */
	public User toUser(CreateUserVO createUserVO) {
		User user = new User();
		user.setFirstName(createUserVO.getFirstName());
		user.setLastName(createUserVO.getLastName());
		user.setUsername(createUserVO.getUserName());
		user.setEmail(createUserVO.getEmail());
		return user;
	}

	/**
	 * User to UserVO.
	 *
	 * @param user
	 * @return
	 */
	public UserVO toUserVO(User user) {
		UserVO userVO = new UserVO();
		userVO.setId(user.getId());
		userVO.setFullName(UserUtil.convertToFullName(user.getFirstName(), user.getLastName()));
		userVO.setFirstName(user.getFirstName());
		userVO.setLastName(user.getLastName());
		userVO.setUserName(user.getUsername());
		userVO.setEmail(user.getEmail());
		return userVO;

	}

	/**
	 * UpdateUserVO to user.
	 *
	 * @param updateUserVO
	 * @return
	 */
	public User toUser(UpdateUserVO updateUserVO) {
		User user = new User();
		user.setId(updateUserVO.getId());
		user.setFirstName(updateUserVO.getFirstName());
		user.setLastName(updateUserVO.getLastName());
		user.setUsername(updateUserVO.getUserName());
		user.setEmail(updateUserVO.getEmail());
		return user;
	}
}