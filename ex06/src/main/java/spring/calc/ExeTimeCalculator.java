package spring.calc;

public class ExeTimeCalculator implements Calculator{
	
	private Calculator calc;
	
	public ExeTimeCalculator(Calculator calc) {// new Rec~~();
		this.calc = calc;
	}

	@Override
	public long factorial(long num) {

		//long start = System.currentTimeMillis();
		long start = System.nanoTime();
		long result = calc.factorial(num);
		//long end = System.currentTimeMillis();
		long end = System.nanoTime();
//		
		System.out.println("factorial메서드 실행 시간 : "+(end-start)+"ms");
		return result;
	}
	
	
}
