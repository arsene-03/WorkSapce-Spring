package spring.main;

import spring.calc.ImpeCalculator;
import spring.calc.RecCalculator;

public class Main01 {

	public static void main(String[] args) {
		ImpeCalculator impe = new ImpeCalculator();
		long result1 = impe.factorial(5);
		
		RecCalculator rec = new RecCalculator();
		long result2 = rec.factorial(5);
		
		System.out.println("ImpeCalculator 계산 결과 : "+result1);
		System.out.println("RecCalculator 계산 결과 : "+result2);

	}

}
