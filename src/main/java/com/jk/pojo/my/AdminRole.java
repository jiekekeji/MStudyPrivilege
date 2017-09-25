package com.jk.pojo.my;

import java.util.List;

import com.jk.pojo.tb.TRole;

public class AdminRole {

	private String id;
	private String name;
	private String remarks;
	private String phone;
	private String qq;
	private Integer state;

	private List<TRole> roles;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<TRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "AdminRole [id=" + id + ", name=" + name + ", remarks=" + remarks + ", phone=" + phone + ", qq=" + qq
				+ ", state=" + state + ", roles=" + roles + "]";
	}

}