package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.calc.Calculator;

public class MainXmlOrder02 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:aopOrder02.xml");
		
		Calculator impeCalc = ctx.getBean("impeCalc",Calculator.class);
		
		impeCalc.factorial(5);
		impeCalc.factorial(5);

	}

}
