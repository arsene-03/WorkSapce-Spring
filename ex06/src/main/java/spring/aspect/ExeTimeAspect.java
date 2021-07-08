package spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class ExeTimeAspect {
	
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		//공통의 기능
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();// 대상 객체의 핵심 기능 메서드로 연결
			return result;
		}finally {
			long end = System.nanoTime();
			System.out.println("실행 시간 : "+(end-start));
		}
		
	}
}
