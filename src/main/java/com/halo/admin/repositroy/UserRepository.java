package com.halo.admin.repositroy;

import com.halo.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hailuo
 * @Date: 2019/4/3 10:37
 * @Description：
 */
public interface UserRepository extends JpaRepository<User,Integer> {

	/**
	 * 根据用户名查询用户
	 * @param name 用户名
	 * @return 查询到的用户，如果不存在则返回null
	 */
	User findByName(String name);
}
