package spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.vo.RegisterRequest;

public class RegisterRequestValidator implements Validator{

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
			
		}
		
	}
	
}
