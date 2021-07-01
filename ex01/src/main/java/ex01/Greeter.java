package ex01;

public class Greeter {
	
	private String format;
	
	public String greet(String name) {
		return String.format(format, name);
	}
	
	// name = 홍길동
	// format = %s님 환영합니다. 당신은 %d세 입니다.      홍길동,25
	// => 홍길동님 환영합니다. 당신은 25세입니다.
	
	public void setFormat(String format) {
		this.format=format;
	}
}
