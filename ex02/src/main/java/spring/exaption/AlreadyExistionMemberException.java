package spring.exaption;

public class AlreadyExistionMemberException extends RuntimeException{
	public AlreadyExistionMemberException(String msg) {
		super(msg);
	}
}
