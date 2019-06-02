package com.halo.admin.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hailuo
 * @Date: 2019/4/3 10:47
 * @Description：
 */
@Entity
public class Role {
    private int id;
    private String name;
    private String nameZh;
    private Integer createId;

    private List<Menu> menus;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name_zh")
    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    @Basic
    @Column(name = "create_id")
    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", createId=" + createId +
                ", menus=" + menus.stream().map(Menu::getPageUrl).collect(Collectors.joining(",")) +
                '}';
    }
}
