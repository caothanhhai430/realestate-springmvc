package com.javaweb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="building")
public class BuildingEntity{
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public RentArea[] getRentAreaArr() {
		return rentAreaArr;
	}


	public void setRentAreaArr(RentArea[] rentAreaArr) {
		this.rentAreaArr = rentAreaArr;
	}


	public void setId(Long id) {
		this.id = id;
	}

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
	private Long costRent;
	
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
	private String timeRent;

	@Column(name="timedecorator")
	private String timeDecorator;

	@Column(name="managername")
	private String managerName;

	@Column(name="managerphone")
	private String managerPhone;
	
	@Column(name="type")
	private String type;
	
	private RentArea[] rentAreaArr;

	public Long getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public Long getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Long buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}



	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Long getCostRent() {
		return costRent;
	}

	public void setCostRent(Long costRent) {
		this.costRent = costRent;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getCarCost() {
		return carCost;
	}

	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}

	
	public String getMotorbikeCost() {
		return motorbikeCost;
	}

	public void setMotorbikeCost(String motorbikeCost) {
		this.motorbikeCost = motorbikeCost;
	}

	public String getOvertimeCost() {
		return overtimeCost;
	}

	public void setOvertimeCost(String overtimeCost) {
		this.overtimeCost = overtimeCost;
	}

	public String getElectricityCost() {
		return electricityCost;
	}

	public void setElectricityCost(String electricityCost) {
		this.electricityCost = electricityCost;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTimeRent() {
		return timeRent;
	}

	public void setTimeRent(String timeRent) {
		this.timeRent = timeRent;
	}

	public String getTimeDecorator() {
		return timeDecorator;
	}

	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getType() {
		return type;
	}

	public void setType(String buildingType) {
		this.type = buildingType;
	}

	public RentArea[] getRentArea() {
		return rentAreaArr;
	}

	public void setRentArea(RentArea[] rentArea) {
		this.rentAreaArr = rentArea;
	}

//	@ManyToMany
//	@JoinTable(
//			  name = "assignment_staffs", 
//			  joinColumns = @JoinColumn(name = "buildingids"), 
//			  inverseJoinColumns = @JoinColumn(name = "staffid"))
//	Set<StaffEntity> staffList;
//	
//	public Set<StaffEntity> getStaffs(){
//		return staffList;
//	}
//	
}