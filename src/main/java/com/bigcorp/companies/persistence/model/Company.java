package com.bigcorp.companies.persistence.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@ManyToMany()
	@JoinTable(name = "COUNTRY_COMPANY", 
	joinColumns = @JoinColumn(name = "COMPANY_ID"), 
	inverseJoinColumns = @JoinColumn(name = "COUNTRY_ID")
	)
	private Set<Country> countries = new HashSet<>();

	private String name;

	private LocalDate date = LocalDate.now();

	@Enumerated(EnumType.STRING)
	private CompanyType companyType = CompanyType.SARL;

	@OneToMany(mappedBy = "company")
	private Set<Service> services = new HashSet<>();

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}
	
	public void attach(Country country) {
		this.countries.add(country);
		country.getCompanies().add(this);
	}

}
