package com.halo.admin.service.impl;

import com.halo.admin.entity.Role;
import com.halo.admin.entity.User;
import com.halo.admin.repositroy.RoleRepository;
import com.halo.admin.repositroy.UserRepository;
import com.halo.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hailuo
 * @Date: 2019/4/3 10:41
 * @Description：
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;


	@Override
	public List<User> allUser() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户["+username+"]不存在");
		}
		return user;
	}
}
