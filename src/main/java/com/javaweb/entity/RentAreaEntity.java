package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="rentarea")
public class RentAreaEntity extends AbstractEntity{
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="value")
	private Integer value;

	public Integer getValue() {
		return value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="buildingid")
	private BuildingEntity building;

	public RentAreaEntity() {
	}
	public RentAreaEntity(Integer value){
		this.value = value;
	}

	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public BuildingEntity getBuilding() {
		return building;
	}
}
