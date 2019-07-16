package com.halo.admin.service.impl;

import com.halo.admin.entity.Role;
import com.halo.admin.repositroy.RoleRepository;
import com.halo.admin.service.RoleService;
import com.halo.admin.vo.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/7/14 15:29
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RoleModel> allRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<>();
        for (Role role : roles) {
            roleModels.add(new RoleModel(role));
        }
        return roleModels;
    }
}
