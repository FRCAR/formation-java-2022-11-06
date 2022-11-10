package com.bigcorp.companies.rest.bean;

import java.time.LocalDate;

import com.bigcorp.companies.persistence.model.Child;

public class ChildRestBean {

	private Long id;

	private String firstName;

	private String lastName;
	
	private LocalDate birthDate;

	public ChildRestBean() {
		super();
	}

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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public static Child toChild(ChildRestBean childRestBean) {
		if(childRestBean == null) {
			return null;
		}
		Child child = new Child();
		child.setId(childRestBean.getId());
		child.setFirstName(childRestBean.getFirstName());
		child.setLastName(childRestBean.getLastName());
		child.setBirthDate(childRestBean.getBirthDate());
		return child;
	}
	
	public static ChildRestBean toChildRestBean(Child child) {
		ChildRestBean childRestBean = new ChildRestBean();
		childRestBean.setId(child.getId());
		childRestBean.setFirstName(child.getFirstName());
		childRestBean.setLastName(child.getLastName());
		childRestBean.setBirthDate(child.getBirthDate());
		return childRestBean;
	}


}
