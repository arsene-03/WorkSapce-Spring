package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.calc.Calculator;

public class MainXmlPojo {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:aopPojo01.xml");
		
		Calculator impeCal = ctx.getBean("impeCal",Calculator.class);
		long result1 = impeCal.factorial(10);
		System.out.println("ImpeCalculator 실행결과 : "+result1);
		
		Calculator recCal = ctx.getBean("recCal",Calculator.class);
		long result2 = impeCal.factorial(10);
		System.out.println("RecCalculator 실행결과 : "+result2);

	}

}
