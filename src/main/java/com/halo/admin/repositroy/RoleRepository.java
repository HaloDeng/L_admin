package com.halo.admin.repositroy;

import com.halo.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/5/19 15:28
 * @Description:
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("SELECT r FROM Role r INNER JOIN UserRole ur ON r.id = ur.roleId INNER JOIN User u ON ur.userId = u.id WHERE u.id = ?1")
    List<Role> findByUser(Integer userId);

}
