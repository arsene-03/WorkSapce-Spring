package spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.vo.RegisterRequest;

public class RegisterRequestValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) { // 검증할 객체가 맞는지 확인
		return RegisterRequest.class.isAssignableFrom(clazz);
//	파라미터로 전달받은 객체가 RegisterRequest로 변환이 가능한지를 확인
//	확인이 되면 스프링에서 자동 검증을 처리67
	}

	@Override
	public void validate(Object target, Errors errors) { // 실제 검증 코드
// 검증의 기준을 작성                        검사 대상 객체        검사 결과를 담을 에러객체
		RegisterRequest regreq = (RegisterRequest)target;    
		
		if(regreq.getEmail()==null|| regreq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
			
		}
		
	}
	
}
