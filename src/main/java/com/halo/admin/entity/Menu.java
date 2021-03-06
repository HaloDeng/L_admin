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
public class Menu {
    private int id;
    private String url;
    private Integer parentId;
    private Integer level;
    private String name;
    private String pageUrl;
    private List<Role> roles;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
    @Column(name = "page_url")
    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
    @ManyToMany(mappedBy = "menus",fetch = FetchType.EAGER)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return id == menu.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", roles=" + roles.stream().map(Role::getName).collect(Collectors.joining(",")) +
                '}';
    }
}
