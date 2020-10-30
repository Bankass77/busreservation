package com.busreseravtionsystem.busreservation.util;

public class RandomStringUtil {

	public static String getAlphaNumericString(int n, String input) {

		// chosir un string au hasard
		String inputStringUcase = input.trim().toUpperCase().replaceAll(" ", " ").concat("123456789");

		// Creation de StringBuffer size of input

		StringBuffer sb = new StringBuffer(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between 0 to input variable length

			int index = (int) (input.length() * Math.random());

			// add character one by one in end of sb

			sb.append(inputStringUcase.charAt(index));
		}
		return sb.toString();

	}

}
