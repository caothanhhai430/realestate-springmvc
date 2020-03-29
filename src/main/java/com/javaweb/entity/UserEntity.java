package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class UserEntity extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String fullname;
	@Column
	private Integer status;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBuildingList(List<BuildingEntity> buildingList) {
		this.buildingList = buildingList;
	}

	public Set<CustomerEntity> getCustomerList() {
		return customerList;
	}


	@ManyToMany(mappedBy = "staffList",fetch = FetchType.LAZY	)
	List<BuildingEntity> buildingList;

	@ManyToMany(mappedBy = "staffList",fetch = FetchType.LAZY)
	Set<CustomerEntity> customerList;

	@ManyToMany(mappedBy = "userList",fetch = FetchType.LAZY)
	Set<RoleEntity> roleList;


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

	public List<BuildingEntity> getBuildingList() {
		return buildingList;
	}

	public void setCustomerList(Set<CustomerEntity> customerList) {
		this.customerList = customerList;
	}

	public Set<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<RoleEntity> roleList) {
		this.roleList = roleList;
	}
}
