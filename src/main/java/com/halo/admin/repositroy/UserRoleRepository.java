package com.halo.admin.repositroy;

import com.halo.admin.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: halo
 * @Date: 2019/7/15 20:42
 * @Description:
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
}
