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


    //这个方法用sql代替会更方便
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
        return menuRelation(menus);
    }


    /**
     * 计算父菜单和子菜单
     * @param menus
     * @return
     */
    private List<MenuModel> menuRelation(Set<Menu> menus) {
        List<MenuModel> models = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getLevel() < 1) {
                MenuModel model = new MenuModel();
                models.add(model);
                model.setName(menu.getName());
                model.setUrl(menu.getPageUrl());
                model.setId(menu.getId());
                model.setIcon("&#xe6b8;");
                for (Menu menu1 : menus) {
                    if (menu1.getParentId() == menu.getId()) {
                        if (model.getChildren() == null) {
                            model.setChildren(new ArrayList<>());
                        }
                        MenuModel child = new MenuModel();
                        child.setIcon("");
                        child.setId(menu1.getId());
                        child.setName(menu1.getName());
                        child.setUrl(menu1.getPageUrl());
                        model.getChildren().add(child);
                    }
                }
            }
        }
        models.removeIf(menuModel -> menuModel.getChildren().size()<1);
        return models;
    }
}
