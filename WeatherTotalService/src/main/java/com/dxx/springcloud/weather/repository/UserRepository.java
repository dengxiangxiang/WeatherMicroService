package com.dxx.springcloud.weather.repository;

import java.util.List;

import com.dxx.springcloud.weather.domain.User;

public interface UserRepository {
	
	User saveOrUpdateUser(User user);
	
	void deleteUser(Long id);
	
	User getUserById(Long id);
	
	List<User> listUser();

}
