package spring.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ExeTimeAspect2 {

	@Pointcut("execution(public * spring.calc..*(..))")
	private void publicTarget() {
		
	}

	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		//공통의 기능
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();// 대상 객체의 핵심 기능 메서드로 연결
			return result;
		}finally {
			long end = System.nanoTime();
			System.out.println("실행 시간 : "+(end-start));
			Signature sig = joinPoint.getSignature();
			
			System.out.println("대상 객체 정보 : "+joinPoint.getTarget().getClass().getSimpleName());
			System.out.println("핵심 메서드 이름 : "+sig.getName());
			System.out.println("핵심 메서드의 매개값 : "+Arrays.toString(joinPoint.getArgs()));
//			System.out.println("핵심 메서드 정보 : "+sig.get);
		
		}
		
	}
}
