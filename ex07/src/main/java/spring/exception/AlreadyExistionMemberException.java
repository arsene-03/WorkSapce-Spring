package spring.exception;

public class AlreadyExistionMemberException extends RuntimeException{

	public AlreadyExistionMemberException(String msg) {
		super(msg);
	}
}
