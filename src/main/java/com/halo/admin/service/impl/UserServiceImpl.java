package com.halo.admin.service.impl;

import com.google.common.base.Strings;
import com.halo.admin.exception.UserException;
import com.halo.admin.entity.User;
import com.halo.admin.entity.UserRole;
import com.halo.admin.repositroy.UserRepository;
import com.halo.admin.repositroy.UserRoleRepository;
import com.halo.admin.service.UserService;
import com.halo.admin.util.Md5Util;
import com.halo.admin.vo.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hailuo
 * @Date: 2019/4/3 10:41
 * @Description：
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {
    private static final String PASS_SALT = "pass";
    @Autowired
	private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;


	@Override
	public List<User> allUser() {
		return userRepository.findAll();
	}

    @Override
    public List<UserModel> userList() {
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = new ArrayList<>();
        for (User user : users) {
            userModels.add(new UserModel(user));
        }
        return userModels;
    }

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findFirstByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户["+username+"]不存在");
		}
		return user;
	}

	@Transactional
    @Override
    public void addUser(UserModel userModel) throws UserException{
        if (Strings.isNullOrEmpty(userModel.getName()) || Strings.isNullOrEmpty(userModel.getPass())) {
            throw new UserException("参数不合法");
        }
        if (userRepository.findFirstByName(userModel.getName()) != null) {
            throw new UserException("用户名已被使用!");
        }
        User user = new User();
        user.setName(userModel.getName());
        user.setPass(Md5Util.encode(userModel.getPass() + PASS_SALT));
        user.setAddress(userModel.getAddress());
        user.setCreateTime(LocalDateTime.now());
        user.setPhone(userModel.getPhone());
        user.setRemark(userModel.getRemark());
        user.setStatus(User.STATUS_ENABLE);
        userRepository.save(user);
        List<UserRole> userRoles = new ArrayList<>();
        for (Integer roleId : userModel.getRoleIds()) {
            userRoles.add(new UserRole(user.getId(), roleId));
        }
        userRoleRepository.saveAll(userRoles);
    }

    @Transactional
    @Override
    public void editUser(UserModel userModel) throws UserException {
        User user = userRepository.findById(userModel.getId()).get();
        user.setPhone(userModel.getPhone());
        user.setAddress(userModel.getAddress());
        user.setRemark(userModel.getRemark());
        int i = userRepository.update(user);
        if (i < 0) {
            throw new UserException("更新用户失败！");
        }
        userRoleRepository.deleteAllByUserId(user.getId());
        List<UserRole> userRoles = new ArrayList<>();
        for (Integer roleId : userModel.getRoleIds()) {
            userRoles.add(new UserRole(user.getId(), roleId));
        }
        userRoleRepository.saveAll(userRoles);
    }


    @Override
    public UserModel findById(Integer userId) {
        return new UserModel(userRepository.findById(userId).get());
    }

    @Override
    public Integer disableUser(Integer userId) {
        User user = userRepository.findById(userId).get();
        byte status = 0;
        if (user.getStatus() == 1) {
            status = 0;
        }else {
            status = 1;
        }
        return userRepository.disableUser(userId,status);
    }

}
