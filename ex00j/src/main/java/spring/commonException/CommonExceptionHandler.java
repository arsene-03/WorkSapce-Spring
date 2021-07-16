package spring.commonException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.exception.MemberNotFoundException;

@ControllerAdvice("spring")
public class CommonExceptionHandler {// 컨트롤러의 역할이 아니라 다수의 컨트롤러에서 발생 가능한 예외를 처리하기 위한 컨트롤러

	@ExceptionHandler(MemberNotFoundException.class)
	public String handlerMemberNotFoundException(MemberNotFoundException e) {
		return "member/noMember";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handlerRuntimeException(RuntimeException e) {
		return "error/commonException";
	}
}
