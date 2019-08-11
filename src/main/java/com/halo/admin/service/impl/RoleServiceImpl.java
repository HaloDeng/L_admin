package com.halo.admin.service.impl;

import com.halo.admin.entity.Role;
import com.halo.admin.entity.User;
import com.halo.admin.entity.UserRole;
import com.halo.admin.repositroy.RoleRepository;
import com.halo.admin.repositroy.UserRoleRepository;
import com.halo.admin.service.RoleService;
import com.halo.admin.util.SecurityUtil;
import com.halo.admin.vo.RoleModel;
import org.checkerframework.checker.units.qual.A;
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
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<RoleModel> allRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<>();
        for (Role role : roles) {

            roleModels.add(new RoleModel(role,false));
        }
        return roleModels;
    }

    @Override
    public List<RoleModel> userRoles(Integer userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        List<Role> roles = roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<>();
        for (Role role : roles) {
            boolean hasRole = false;
            for (UserRole userRole : userRoles) {
                if (role.getId() == userRole.getRoleId()) {
                    hasRole = true;
                    break;
                }
            }
            roleModels.add(new RoleModel(role, hasRole));
        }
        return roleModels;
    }
}
