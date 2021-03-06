package com.halo.admin.vo;

import com.halo.admin.entity.Role;
import lombok.Data;

/**
 * @Auther: halo
 * @Date: 2019/7/14 15:31
 * @Description:
 */
@Data
public class RoleModel {
    private Integer id;
    private String name;
    private Boolean hasRole;

    public RoleModel(Role role,boolean hasRole) {
        this.id = role.getId();
        this.name = role.getNameZh();
        this.hasRole = hasRole;
    }
}
