package com.halo.admin.service;

import com.halo.admin.UserException;
import com.halo.admin.entity.User;
import com.halo.admin.vo.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Hailuo
 * @Date: 2019/4/3 10:40
 * @Description：
 */
public interface UserService extends UserDetailsService{
	/**
	 * 查询所有管理员用户
	 * @return List<User>
	 */
	List<User> allUser();


    /**
     * 查询所有管理员并封装数据
     * @return
     */
    List<UserModel> userList();

    /**
     * 添加管理员
     * @param userModel
     */
    public void addUser(UserModel userModel) throws UserException;
}
