package spring.main;

import spring.calc.ExeTimeCalculator;

import spring.calc.ImpeCalculator;
import spring.calc.RecCalculator;

public class Main02 {

	public static void main(String[] args) {
		ExeTimeCalculator impe = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println("impe 결과 : "+impe.factorial(10));

	
		ExeTimeCalculator rec = new ExeTimeCalculator(new RecCalculator());
		System.out.println("rec 결과 : "+rec.factorial(10));
	
	}

}
