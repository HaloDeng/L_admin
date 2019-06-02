package com.halo.admin.service.impl;

import com.halo.admin.entity.Menu;
import com.halo.admin.repositroy.MenuRepository;
import com.halo.admin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
