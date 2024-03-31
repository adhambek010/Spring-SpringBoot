package org.javacoders.uzb.practisesecurity.service;

import org.javacoders.uzb.practisesecurity.payloads.UserDto;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
	
	UserDto saveUser(UserDto user);
	
	UserDto updateUser(int userId, UserDto user);
	
	void deleteUser(int userId);
	
}
