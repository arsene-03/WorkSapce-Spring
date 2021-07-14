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
	public boolean supports(Class<?> clazz) { // ������ ��ü�� �´��� Ȯ��
		return RegisterRequest.class.isAssignableFrom(clazz);
//	�Ķ���ͷ� ���޹��� ��ü�� RegisterRequest�� ��ȯ�� ���������� Ȯ��
//	Ȯ���� �Ǹ� ���������� �ڵ� ������ ó��67
	}

	@Override
	public void validate(Object target, Errors errors) { // ���� ���� �ڵ�
// ������ ������ �ۼ�                        �˻� ��� ��ü        �˻� ����� ���� ������ü
		RegisterRequest regreq = (RegisterRequest)target;    
		
		if(regreq.getEmail()==null|| regreq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}else {
			Matcher matcher = pattern.matcher(regreq.getEmail());
			if(!matcher.matches()) { // ��ġ���� �ʴ´ٸ� false
				errors.rejectValue("email", "bad");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
// ���� �������� ���� ���� ����� �̸� ���ǵǾ� �ִ�.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		
		if(!regreq.getPassword().isEmpty()) {
			if(regreq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword","nomatch");
			}
		}
	}
	
}
