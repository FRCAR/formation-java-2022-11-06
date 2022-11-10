package com.bigcorp.util;

public class Calculette {

	
	public int calcule(Operation operation) {
		int i = (int)(100 *Math.random());
		int j = (int)(100 *Math.random());
		return operation.calcule(i,j);
	}
	
}
