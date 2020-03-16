package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import javax.persistence.Column;

@Getter
@Setter
public class AbstractEntity {
	
	@Column(name="createddate")
	protected Timestamp createdDate;
	
	@Column(name="createdby")
	protected String createdBy;
	
	@Column(name="modifieddate")
	protected Timestamp modifiedDate; 
	
	@Column(name="modifiedby")
	protected String modifiedBy;

}
