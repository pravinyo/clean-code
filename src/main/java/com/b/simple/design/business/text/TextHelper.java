package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		if (str.length() < 2) return str;

		char lastChar = str.charAt(str.length()-1);
		char secondLastChar = str.charAt(str.length()-2);
		String temp = str.substring(0, str.length()-2);

		return temp + lastChar + secondLastChar;
	}

	public String truncateAInFirst2Positions(String str) {

		if (str.length() < 2) return str.replaceAll("A", "");

		String first2Chars = str.substring(0,2);
		String first2CharsUpdated = first2Chars.replaceAll("A","");
		String rest = str.substring(2);

		return first2CharsUpdated + rest;
	}
}
