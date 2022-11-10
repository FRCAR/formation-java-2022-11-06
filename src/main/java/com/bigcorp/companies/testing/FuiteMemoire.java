package com.bigcorp.companies.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FuiteMemoire {

	public static void main(String[] args) throws Exception {
		TimeUnit.SECONDS.sleep(10);
		List<ObjetLourdingue> liste = new ArrayList<>();
		while (true) {
			liste.add(new ObjetLourdingue());
		}
	}
}
