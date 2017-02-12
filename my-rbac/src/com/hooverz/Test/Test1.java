package com.hooverz.Test;

import java.util.ArrayList;

import org.junit.Test;

public class Test1 {

	@Test
	public void test1() {

		ArrayList<String> l1 = new ArrayList<String>();
		ArrayList<String> l2 = new ArrayList<String>();

		l1.add("a");
		l1.add("b");
		l1.add("c");
		l1.add("d");

		l2.add("g");
		l2.add("g");
		l2.add("g");
		l2.add("g");

		boolean retainAll = l1.retainAll(l2);

		System.out.println(retainAll);

		System.out.println(l1);
	}

}
