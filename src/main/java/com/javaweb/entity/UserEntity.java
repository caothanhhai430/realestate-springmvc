package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class UserEntity extends AbstractEntity{

	@Id
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


}
