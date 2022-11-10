package com.bigcorp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bigcorp.companies.persistence.model.Child;
import com.bigcorp.util.CalculetteMain.Cube;

public class CalculetteMain {

	public static void main(String[] args) {
		Calculette calculette = new Calculette();

		// Java à la classique vjava <8
		int resultat = calculette.calcule(new OperationAddition());
		System.out.println("Le résultat de l'addition est : " + resultat);

		// Java avec définition de type en ligne
		resultat = calculette.calcule(new Operation() {

			@Override
			public int calcule(int i, int j) {
				return i + j * 2;
			}
		});
		System.out.println("Le résultat de l'addition est : " + resultat);

		// Java avec Lambda
		Operation operation = (a, b) -> a * b;

		resultat = calculette.calcule(operation);
		System.out.println("Le résultat de la multiplication avec lambda est : " + resultat);

		resultat = calculette.calcule((int a, int b) -> {
			return (int) Math.pow(a, b);
		});

		// Lambdas avec Streams
		List<Integer> entiers = new ArrayList<>();
		entiers.add(1);
		entiers.add(2);
		entiers.add(3);
		entiers.add(4);
		entiers.add(5);
		entiers.add(6);
		entiers.add(7);

		entiers.stream().forEach((Integer i) -> {
			System.out.println(i);
		});
		// équivaut à
		for (Integer integer : entiers) {
			System.out.println(integer);
		}

		//Filtres : version Java
		List<Integer> elementsFiltres = new ArrayList<>();
		for (Integer integer : entiers) {
			if (integer > 5) {
				elementsFiltres.add(integer);
			}
		}
		System.out.println("J'affiche les éléments filtrés");
		elementsFiltres.stream().forEach((Integer i) -> {
			System.out.println(i);
		});

		//Filtres : version Lambda
		System.out.println("J'affiche les éléments filtrés avec lambda");
		entiers.stream().filter(i -> i > 5)
			.forEach(i -> {System.out.println(i);});

		//Un collecteur (qui va remplir une collection)
		List<Integer> streamCollecteEtFiltre = entiers.stream().filter(i -> i > 2).collect(Collectors.toList());

		//Itération sur dix entiers, map sur un nouvel objet et affichage de l'objet
		Stream.iterate(1, i -> i <= 10, i -> i + 1)
			.map(i -> new Cube(i))
			.forEach(c -> c.afficheCube());
		
		
		List<Child> children = new ArrayList<>();
		Child c1 = new Child();
		c1.setAge(3);
		children.add(c1);
		Child c2 = new Child();
		c2.setAge(5);
		children.add(c2);
		Child c3 = new Child();
		c3.setAge(-3);
		children.add(c3);
		Child c4 = new Child();
		c4.setAge(2);
		children.add(c4);
		
		Optional<Child> max = children.stream()
				.max((Child cc1, Child cc2)->{return cc1.getAge() - cc2.getAge();});
		if(max.isPresent()) {
			System.out.println(max.get().getAge());			
		}else {
			System.out.println("Il n'y a pas d'enfant avec l'age maximal.");
		}

	}

	public static final class SuperInt {

		int val;

		public void affiche() {
			System.out.println("Je suis un super int et je vaux " + this.val);
		}

		public SuperInt(int val) {
			super();
			this.val = val;
		}

	}

	public static final class Cube {

		private int initVal;
		private int cubeVal;

		public Cube(int initVal) {
			super();
			this.initVal = initVal;
			this.cubeVal = initVal * initVal * initVal;
		}

		public void afficheCube() {
			System.out.println(
					"la valeur initiale vaut : " 
				+ this.initVal
				+ " et la valeur finale vaut " 
				+ this.cubeVal);
		}

		public int getInitVal() {
			return initVal;
		}

		public int getCubeVal() {
			return cubeVal;
		}

	}

}
