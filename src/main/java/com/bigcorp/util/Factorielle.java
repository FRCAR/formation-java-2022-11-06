package com.bigcorp.util;
/**
 * OPération mathématique qui pour n renvoie n * n-1 * n-2 * n-3 .... *1
 * @author francois
 *
 */
public class Factorielle {
	
	public static int factorielle(int n) {
		if (n < 2){
			return 1;
		}
		return n * factorielle(n-1);
	}

}
