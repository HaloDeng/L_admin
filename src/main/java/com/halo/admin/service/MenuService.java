package com.halo.admin.service;

import com.halo.admin.entity.Menu;
import com.halo.admin.vo.MenuModel;

import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/5/27 22:44
 * @Description:
 */
public interface MenuService {
    /**
     * 获取所有菜单信息
     * @return
     */
    List<Menu> findAllMenus();

    /**
     * 计算当前角色菜单
     * @return
     */
    List<MenuModel> currentUserMenu();
}
