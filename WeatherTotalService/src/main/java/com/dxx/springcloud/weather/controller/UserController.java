package com.dxx.springcloud.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxx.springcloud.weather.domain.User;
import com.dxx.springcloud.weather.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getUsers(){
		return userRepository.listUser();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return userRepository.getUserById(id);
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
	
		return userRepository.saveOrUpdateUser(user);
	}
	
	@PostMapping("/{id}")
	public void updateUser(@PathVariable("id") Long id,@RequestBody User user) {
		User oldUser = userRepository.getUserById(id);
		if(oldUser!=null) {
			user.setId(id);
			userRepository.saveOrUpdateUser(user);
		}
		 
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		 userRepository.deleteUser(id);;
	}
	
	
}
