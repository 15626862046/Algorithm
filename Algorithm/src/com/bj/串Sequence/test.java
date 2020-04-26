package com.bj.串Sequence;

import com.bj.工具类tools.Asserts;

public class test {

	public static void main(String[] args) {
		//Asserts.test(BruteForce.indexOf("hello world", "or") == 7);
		//Asserts.test(BruteForce.indexOf("hello world", "abc") == -1);
		Asserts.test(KMP.indexOf("hello world", "or") == 7);
		Asserts.test(KMP.indexOf("hello world", "abc") == -1);
	}

}
