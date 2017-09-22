package com.jk.pojo.my;

import java.util.List;

import com.jk.pojo.tb.TResources;

public class RoleRes {

	private String id;
	private String name;
	private String remarks;
	private List<TResources> resources;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<TResources> getResources() {
		return resources;
	}

	public void setResources(List<TResources> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "RoleRes [id=" + id + ", name=" + name + ", remarks=" + remarks + ", resources=" + resources + "]";
	}

}
