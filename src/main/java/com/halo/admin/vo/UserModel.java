package com.halo.admin.vo;

import com.halo.admin.entity.User;
import com.halo.admin.util.TimeUtil;
import lombok.Data;

/**
 * @Auther: halo
 * @Date: 2019/7/14 13:19
 * @Description:
 */
@Data
public class UserModel {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Byte status;
    private String remark;
    private String createTime;
    private String pass;
    private Integer[] roleIds;

    public UserModel() {

    }

    public UserModel(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.status = user.getStatus();
        this.remark = user.getRemark();
        this.createTime = TimeUtil.formatToYMDT(user.getCreateTime());
    }
}
