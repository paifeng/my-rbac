package com.hooverz.domain;

/**
 * 权限对象
 * 
 * @author love5
 * 
 */
public class Privilege {

	private int id;
	private String privilegename;
	private String nologin; // 未登录可以访问
	private String privilegeUrl;
	private String requestaction;
	private String description;

	public Privilege() {
	}

	public Privilege(int id, String privilegename, String nologin,
			String privilegeUrl, String requestaction, String description) {
		this.id = id;
		this.privilegename = privilegename;
		this.nologin = nologin;
		this.privilegeUrl = privilegeUrl;
		this.requestaction = requestaction;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrivilegename() {
		return privilegename;
	}

	public void setPrivilegename(String privilegename) {
		this.privilegename = privilegename;
	}

	public String getNologin() {
		return nologin;
	}

	public void setNologin(String nologin) {
		this.nologin = nologin;
	}

	public String getPrivilegeUrl() {
		return privilegeUrl;
	}

	public void setPrivilegeUrl(String privilegeUrl) {
		this.privilegeUrl = privilegeUrl;
	}

	public String getRequestaction() {
		return requestaction;
	}

	public void setRequestaction(String requestaction) {
		this.requestaction = requestaction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", privilegename=" + privilegename
				+ ", nologin=" + nologin + ", privilegeUrl=" + privilegeUrl
				+ ", requestaction=" + requestaction + ", description="
				+ description + "]";
	}

}
