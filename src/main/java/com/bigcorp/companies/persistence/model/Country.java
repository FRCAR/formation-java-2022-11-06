package com.bigcorp.companies.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Country {
	
	@Id
	private Long id;

	@ManyToMany(mappedBy="countries")
	private Set<Company> companies = new HashSet<>();

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}
	
}
