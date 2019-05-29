package com.halo.admin.entity;

import javax.persistence.*;
import java.util.List;

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
	@Transient
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
