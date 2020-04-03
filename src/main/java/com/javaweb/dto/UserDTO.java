package com.javaweb.dto;

public class UserDTO extends AbstractDTO{

	private Long id;
	private String fullname;
	private Integer status;
	private Long role;
	private Integer buildingId;
	private String avatar;
	private String phone;
	private String username;
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}


	@Override
	public Integer getPage() {
		return super.getPage();
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Object role)
	{
		if(role instanceof Long)
		this.role = (Long)role;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {

		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {

		this.status = status;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

}
