package com.bigcorp.companies.reflection;

import java.lang.reflect.Method;

public class PojoCopier {


	public void read(Object source) {
		Class<?> clazz = source.getClass();
		for (Method method : clazz.getMethods()) {
			if (method.getParameters().length == 0 && method.getName().startsWith("get")) {
				try {
					Object value = method.invoke(source, new Object[0]);
					System.out.println(method.getName() + " renvoie " + value);
				} catch (Exception e) {
					throw new RuntimeException("Erreur lors de l'invocation du getter : " + method.getName(), e);
				}
			}
		}
	}

	public void copy(Object source, Object destination) {
		Class<?> sourceClass = source.getClass();
		Class<?> destinationClass = destination.getClass();
		for (Method sourceMethod : sourceClass.getMethods()) {
			if (sourceMethod.getParameters().length == 0 && sourceMethod.getName().startsWith("get")
					&& sourceMethod.getName().length() > 3) {
				try {
					Object value = sourceMethod.invoke(source, new Object[0]);
					Class<?> returnType = sourceMethod.getReturnType();
					try {
						Method destinationMethod = destinationClass
								.getMethod("set" + sourceMethod.getName().substring(3), new Class[] { returnType });
						destinationMethod.invoke(destination, new Object[] { value });
					} catch (Exception e) {

					}
				} catch (Exception e) {
					throw new RuntimeException("Erreur lors de l'invocation du getter : " + sourceMethod.getName(), e);
				}
			}
		}
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
