package com.halo.admin.repositroy;

import com.halo.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/6/2 13:34
 * @Description:
 */
public interface MenuRepository extends JpaRepository<Menu,Integer> {


}
