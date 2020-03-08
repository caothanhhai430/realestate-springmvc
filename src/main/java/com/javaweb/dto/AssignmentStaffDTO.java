package com.javaweb.dto;

import java.util.List;

public class AssignmentStaffDTO {
	private Integer id;
	private Integer[] buildingId;
	private Integer[] staffId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer[] getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer[] buildingId) {
		this.buildingId = buildingId;
	}
	public Integer[] getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer[] staffId) {
		this.staffId = staffId;
	}
	
	
	
}
