package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="building")
public class BuildingEntity{
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="numberofbasement")
	private Long numberOfBasement;

	@Column(name="buildingarea")
	private Long buildingArea;
	
	@Column(name="district")
	private String district;

	@Column(name="ward")
	private String ward;

	@Column(name="street")
	private String street;
	
	@Column(name="structure")
	private String structure;

	@Column(name="costrent")
	private Long rentCost;
	
	@Column(name="costdescription")
	private String costDescription;

	@Column(name="servicecost")
	private String serviceCost;
	
	@Column(name="carcost")
	private String carCost;

	@Column(name="motorbikecost")
	private String motorbikeCost;

	@Column(name="overtimecost")
	private String overtimeCost;

	@Column(name="electricitycost")
	private String electricityCost;

	@Column(name="deposit")
	private String deposit;

	@Column(name="payment")
	private String payment;

	@Column(name="timerent")
	private String rentTime;

	@Column(name="timedecorator")
	private String decoratorTime;

	@Column(name="managername")
	private String managerName;

	@Column(name="managerphone")
	private String managerPhone;
	
	@Column(name="type")
	private String type;

	@OneToMany(mappedBy="building",fetch = FetchType.EAGER)
	private List<RentArea> rentAreaList;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "assignmentstaff",
			  joinColumns = @JoinColumn(name = "buildingid"),
			  inverseJoinColumns = @JoinColumn(name = "staffid"))
	Set<StaffEntity> staffList;

	public Set<StaffEntity> getStaffs(){
		return staffList;
	}


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public Long getBuildingArea() {
		return buildingArea;
	}

	public String getDistrict() {
		return district;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public String getStructure() {
		return structure;
	}

	public Long getRentCost() {
		return rentCost;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public String getMotorbikeCost() {
		return motorbikeCost;
	}

	public String getOvertimeCost() {
		return overtimeCost;
	}

	public String getElectricityCost() {
		return electricityCost;
	}

	public String getDeposit() {
		return deposit;
	}

	public String getPayment() {
		return payment;
	}

	public String getRentTime() {
		return rentTime;
	}

	public String getDecoratorTime() {
		return decoratorTime;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public String getType() {
		return type;
	}

	public List<RentArea> getRentAreaList() {
		return rentAreaList;
	}

	public Set<StaffEntity> getStaffList() {
		return staffList;
	}
}
