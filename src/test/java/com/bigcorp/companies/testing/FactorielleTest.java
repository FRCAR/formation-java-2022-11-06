package com.bigcorp.companies.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.bigcorp.util.Factorielle;

public class FactorielleTest {

	@Test
	public void test0() {
		Assertions.assertEquals(1, Factorielle.factorielle(0));
	}

	@Test
	public void test1() {
		Assertions.assertEquals(1, Factorielle.factorielle(1));
	}

	@Test
	public void test2() {
		Assertions.assertEquals(2, Factorielle.factorielle(2));
	}

	@Test
	public void test3() {
		Assertions.assertEquals(6, Factorielle.factorielle(3));
	}

	@RepeatedTest(value = 12)
	public void test4() {
		Assertions.assertEquals(24, Factorielle.factorielle(4));
	}

}
