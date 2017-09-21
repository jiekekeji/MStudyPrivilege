package com.jk.bean;

import java.util.List;

import com.jk.tb.pojo.TResources;

public class RoleRes {

	private String id;
	private String roleId;
	private List<TResources> resources;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public List<TResources> getResources() {
		return resources;
	}

	public void setResources(List<TResources> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "RoleRes [id=" + id + ", roleId=" + roleId + ", resources=" + resources + "]";
	}

}
