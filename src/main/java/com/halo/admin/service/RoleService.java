package com.halo.admin.service;

import com.halo.admin.vo.RoleModel;

import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/7/14 15:28
 * @Description:
 */
public interface RoleService {
    /**
     * 所有角色
     * @return
     */
    List<RoleModel> allRoles();

    /**
     * 查询用户所属角色
     * @param userId
     * @return
     */
    List<RoleModel> userRoles(Integer userId);
}
