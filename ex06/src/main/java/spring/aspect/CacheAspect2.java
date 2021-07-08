package spring.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2) //숫자가 작을수록 우선순위 노팡짐
public class CacheAspect2 {

	private Map<Long,Object> cache = new HashMap<>();
	
	@Pointcut("execution(public * spring.calc..*(..))")
	public void cacheTarget() {
		
	}
	
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Long num = (Long)joinPoint.getArgs()[0];
		if(cache.containsKey(num)) {
			System.out.println("캐시에서 얻어옴 : "+num);
			return cache.get(num);
		}else {
			Object result = joinPoint.proceed();
			cache.put(num, result);
			System.out.println("캐시에 추가 : "+num);
			return result;
		}

}
