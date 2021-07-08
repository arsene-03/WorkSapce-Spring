package spring.bean;

public class Client2 {
	
	private String host;
	
	public void setHost(String host) {
		this.host = host;
		System.out.println("Client.setHost()메서드 동작");
	}
	
	public void send() {
		System.out.println("Client.send()메서드 동작 : "+host+"에게 보냄");
	}
	
	public void connect() throws Exception{
		System.out.println("빈 객체 초기화시 실행할 메서드 : connect() !");
	}
	
	 public void close() throws Exception{
		 System.out.println("빈 객체 소멸시 실행할 메서드 : close() !");
	 }
}
