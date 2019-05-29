package com.halo.admin.service;

import com.halo.admin.entity.Menu;

import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/5/27 22:44
 * @Description:
 */
public interface MenuService {
    List<Menu> findAllMenus();
}
