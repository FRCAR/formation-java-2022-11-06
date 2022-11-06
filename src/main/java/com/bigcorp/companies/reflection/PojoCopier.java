package com.bigcorp.companies.reflection;

public class PojoCopier {

	public void read(Object source) {

	}

	public void copy(Object source, Object destination) {

	}

	public static void main(String[] args) {
		ClassA classeA = new ClassA();
		ClassB classeB = new ClassB();
		PojoCopier pojoCopier = new PojoCopier();
		System.out.println("Avant copie");
		pojoCopier.read(classeA);
		pojoCopier.read(classeB);
		pojoCopier.copy(classeA, classeB);
		System.out.println("Après copie");
		pojoCopier.read(classeA);
		pojoCopier.read(classeB);
	}

}
