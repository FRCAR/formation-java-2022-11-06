package com.bigcorp.companies.rest.bean;

public class CompanyRestBean {

	private Long id;

	private String name;

	public CompanyRestBean() {
		super();
	}

	public CompanyRestBean(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
