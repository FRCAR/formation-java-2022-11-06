package com.bigcorp.companies.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Reflection {

	private String name = "salut";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws Exception {
		Class<Reflection> clazz = (Class<Reflection>) Class.forName("com.bigcorp.companies.reflection.Reflection");
		for (Method method : clazz.getMethods()) {
			System.out.println("Méthode : " + method.getName() + " trouvée ");
			if (method.getParameters().length == 0) {
				System.out.println("Cette méthode n'a pas d'argument");
			}
			for (Parameter parameter : method.getParameters()) {
				System.out.println("Cette méthode a l'argument : " + parameter.getName());
			}
		}
		Reflection newInstance = clazz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
		System.out.println(newInstance.getName());
		Method method = clazz.getMethod("getName", new Class<?>[0]);
		Object returnedValue = method.invoke(newInstance, new Object[0]);
		System.out.println(returnedValue);
	}

}
