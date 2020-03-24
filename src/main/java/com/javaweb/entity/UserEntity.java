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

	@ManyToMany(mappedBy = "staffList",fetch = FetchType.LAZY	)
	List<BuildingEntity> buildingList;

	@ManyToMany(mappedBy = "staffList",fetch = FetchType.LAZY)
	Set<CustomerEntity> customerList;

	public List<BuildingEntity> getBuildingList() {
		return buildingList;
	}

	public void setCustomerList(Set<CustomerEntity> customerList) {
		this.customerList = customerList;
	}
}
