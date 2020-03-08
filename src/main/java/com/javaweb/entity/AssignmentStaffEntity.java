package com.javaweb.entity;

import com.javaweb.annotation.Column;
import com.javaweb.annotation.Entity;
import com.javaweb.annotation.Table;

@Entity
@Table(name="assignmentstaff")
public class AssignmentStaffEntity {
	@Column(name="id")
	private Integer id;
	
	@Column(name="buildingid")
	private Integer buildingId;
	
	@Column(name="staffid")
	private Integer staffId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	
	
	
}
