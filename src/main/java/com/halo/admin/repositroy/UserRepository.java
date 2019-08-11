package com.halo.admin.repositroy;

import com.halo.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	User findFirstByName(String name);

	@Modifying
    @Query("update User u set u.status = ?2 where u.id = ?1")
    int disableUser(Integer userId,byte status);


	@Modifying
    @Query("update User user set user.phone = :#{#u.phone},user.address=:#{#u.address},user.remark=:#{#u.remark} where user.id = :#{#u.id}")
	int update(@Param("u") User u);


}
