package ex01;

public class Main01 {

	public static void main(String[] args) {
		// 스프링을 이용하지 않는 자바 코드
		Greeter gt = new Greeter();
		gt.setFormat("%s, 안녕하세요");
		String msg = gt.greet("여러분");
		
		System.out.println(msg);
	}

}
