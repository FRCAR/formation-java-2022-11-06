package com.bigcorp.companies.persistence.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	public enum Contract {
		CDI, CDD
	};

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String firstName;

	private String lastName;

	private LocalDateTime arrivalDate;

	private Boolean active;

	private String phone;

	private Integer score;

	@Enumerated(EnumType.STRING)
	private Contract contract;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_AS_MANAGER_ID")
	private Service serviceAsManager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVICE_AS_EMPLOYEE_ID")
	private Service serviceAsEmployee;
	
	@OneToMany(mappedBy = "parent")
	private Set<Child> children = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	

	

	public Service getServiceAsManager() {
		return serviceAsManager;
	}

	public void setServiceAsManager(Service serviceAsManager) {
		this.serviceAsManager = serviceAsManager;
	}

	public Service getServiceAsEmployee() {
		return serviceAsEmployee;
	}

	public void setServiceAsEmployee(Service serviceAsEmployee) {
		this.serviceAsEmployee = serviceAsEmployee;
	}

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}

	public String toString() {
		return "Id : " + this.id + " , Nom : " + this.lastName + " , prénom " + this.lastName;
	}

}
