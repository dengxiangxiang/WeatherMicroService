package com.dxx.springcloud.weather.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.dxx.springcloud.weather.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	private AtomicLong counter = new AtomicLong();
	private ConcurrentMap<Long,User> userMap = new ConcurrentHashMap<Long, User>();

	@Override
	public User saveOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		Long id = user.getId();
		if(id==null||id<=0) {
			id= counter.incrementAndGet();
			user.setId(id);
		}
		userMap.put(id, user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
			userMap.remove(id);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userMap.get(id);
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
//		return (List<User>) userMap.values();
		return new ArrayList<User>(userMap.values());
	}

}
