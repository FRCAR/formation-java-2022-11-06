package com.bigcorp.companies.persistence.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHILD")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;
	
	private int age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Employee parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public Employee getParent() {
		return parent;
	}

	public void setParent(Employee parent) {
		this.parent = parent;
	}

	public String toString() {
		return "Id : " + this.id + " , Nom : " + this.lastName + " , prénom " + this.lastName;
	}

}
