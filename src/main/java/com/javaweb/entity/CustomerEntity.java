package com.javaweb.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="customer")
public class CustomerEntity extends AbstractEntity{
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="phone")
	private String phone;


	@Column(name="email")
	private String email;
	
	@Column(name="company")
	private String company;

	@Column(name="demand")
	private String demand;

	@Column(name="status")
	private Byte status;
	
	@Column(name="note")
	private String note;

	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinTable(
			name = "staff_customer",
			joinColumns = @JoinColumn(name = "customerid"),
			inverseJoinColumns = @JoinColumn(name = "staffid"))
	Set<UserEntity> staffList;

	@OneToMany(mappedBy="customer",
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<TransactionEntity> transactionList;

	public void setTransactionList(Set<TransactionEntity> transactionList) {
		this.transactionList = transactionList;
	}

	public Set<TransactionEntity> getTransactionList() {
		return transactionList;
	}

	public Set<UserEntity> getStaffList() {
		return staffList;
	}

	public void setStaffList(Set<UserEntity> staffList) {
		this.staffList = staffList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}


	
}
