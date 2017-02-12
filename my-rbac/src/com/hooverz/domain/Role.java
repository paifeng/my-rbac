package com.hooverz.domain;

import java.util.List;

/**
 * 角色对象
 * 
 * @author love5
 * 
 */
public class Role {

	private int id;
	private String rolename;
	private String description;
	private List<Privilege> privileges;

	public Role() {
	}

	public Role(int id, String rolename, String description,
			List<Privilege> privileges) {
		this.id = id;
		this.rolename = rolename;
		this.description = description;
		this.privileges = privileges;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", description="
				+ description + ", privileges=" + privileges + "]";
	}

}
