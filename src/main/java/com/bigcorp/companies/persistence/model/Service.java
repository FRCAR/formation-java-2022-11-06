package com.bigcorp.companies.persistence.model;

import java.time.LocalDate;
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
@Table(name = "SERVICE")
public class Service {
 
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COMPANY_ID")
	private Company company;
	
	@OneToMany(mappedBy = "serviceAsEmployee")
	private Set<Employee> employees = new HashSet<>();
	
	@OneToMany(mappedBy = "serviceAsManager")
	private Set<Employee> managers = new HashSet<>();
	
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
	
	public String toString() {
		return "id = " + this.id + " , name = " + this.name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Employee> getManagers() {
		return managers;
	}

	public void setManagers(Set<Employee> managers) {
		this.managers = managers;
	}

	


}
