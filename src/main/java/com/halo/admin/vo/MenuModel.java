package com.halo.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @Auther: halo
 * @Date: 2019/6/23 17:26
 * @Description:
 */
@Data
public class MenuModel {
    private int id;
    private String name;
    private String icon;
    private String url;
    private List<MenuModel> children;
}
