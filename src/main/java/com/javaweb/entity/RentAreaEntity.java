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
	private Integer id;
	
	@Column(name="value")
	private Integer value;

	public Integer getValue() {
		return value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="buildingid")
	private BuildingEntity building;
	
}
