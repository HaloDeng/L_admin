package com.halo.admin.service.impl;

import com.halo.admin.entity.Menu;
import com.halo.admin.entity.Role;
import com.halo.admin.entity.User;
import com.halo.admin.repositroy.MenuRepository;
import com.halo.admin.service.MenuService;
import com.halo.admin.util.SecurityUtil;
import com.halo.admin.vo.MenuModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: halo
 * @Date: 2019/5/27 22:44
 * @Description:
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public List<MenuModel> currentUserMenu() {
        User user = SecurityUtil.currentUser();
        List<MenuModel> models = new ArrayList<>();
        if (user == null) {
            log.error("当前用户未登录！");
            return models;
        }
        if (user.getRoles().size() < 1) {
            log.warn("当前用户无角色信息");
            return models;
        }
        Set<Menu> menus = new HashSet<>();
        for (Role role : user.getRoles()) {
            menus.addAll(role.getMenus());
        }
        for (Menu menu : menus) {
            
        }




        return null;
    }
}
