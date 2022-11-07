package com.bigcorp.companies.reflection;

public class ClassA {

	private boolean ready = true;

	private String firstName = "Jean";

	private String lastName = "Dupont";

	private Long age = 3l;
	
	private ClassB classB = new ClassB();

	public ClassB getClassB() {
		return classB;
	}

	public void setClassB(ClassB classB) {
		this.classB = classB;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}
