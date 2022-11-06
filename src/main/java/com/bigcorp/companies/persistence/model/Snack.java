package com.bigcorp.companies.persistence.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AttributeOverride(name = "name", column = @Column(name = "SNACK_NAME"))
public class Snack extends AbstractRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	protected String burgerName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBurgerName() {
		return burgerName;
	}

	public void setBurgerName(String burgerName) {
		this.burgerName = burgerName;
	}

}
