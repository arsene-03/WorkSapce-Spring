package spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.vo.RegisterRequest;

public class RegisterRequestValidator implements Validator{
	
	private static final String EMAIL_EXP = 
			"^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; 
	
	private Pattern pattern;
	
	public RegisterRequestValidator(){
		pattern = Pattern.compile(EMAIL_EXP);
	}

	@Override
	public boolean supports(Class<?> clazz) { // 검증할 객체가 맞는지 확인
		return RegisterRequest.class.isAssignableFrom(clazz);
// 파라미터로 전달받은 객체가 RegisterRequest로 변환이 가능한지 확인
// 확인 시, 스프링에서 자동 검증 처리
	}

	@Override
	public void validate(Object target, Errors errors) { // 실제 검증 코드
// 검증 기준 작성			    검사 대상 객체	   검사 결과를 담을 에러객체(반환객체)
		RegisterRequest regreq = (RegisterRequest)target;
		System.out.println("출력 여부 확인1");
		if(regreq.getEmail()==null || regreq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}else {
			Matcher matcher = pattern.matcher(regreq.getEmail());
			if(!matcher.matches()) {// 일치하지 않는 다면 false
				errors.rejectValue("email", "bad");
			}
		}
		System.out.println("출력 여부 확인2");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	// 자주 벌어지는 에러 검증 방법이 미리 정의 되어 있다. 
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		
		if(!regreq.getPassword().isEmpty()) {
			if(!regreq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
		System.out.println("출력 여부 확인3");
	}
	
}
