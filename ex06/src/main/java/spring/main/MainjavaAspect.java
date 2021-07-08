package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.calc.Calculator;
import spring.calc.ImpeCalculator;
import spring.config.JavaConfig;

public class MainjavaAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(JavaConfig.class);
		
		ImpeCalculator impeCal = ctx.getBean("impeCalc",ImpeCalculator.class);
		long result1 = impeCal.factorial(10);
		System.out.println("ImpeCalculator 실행결과 : "+result1);
		
		Calculator recCal = ctx.getBean("recCalc",Calculator.class);
		long result2 = recCal.factorial(10);
		System.out.println("RecCalculator 실행결과 : "+result2);
		
		

	}

}
