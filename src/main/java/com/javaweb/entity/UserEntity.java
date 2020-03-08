package com.javaweb.entity;

import com.javaweb.annotation.Column;
import com.javaweb.annotation.Entity;
import com.javaweb.annotation.Table;

@Entity
@Table(name="user")
public class UserEntity extends AbstractEntity{

	@Column(name="id")
	private Long id;

	@Column(name="fullname")
	private String fullname;

	@Column(name="status")
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
	
	
}
