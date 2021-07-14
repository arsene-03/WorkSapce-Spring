package spring.vo;

public class LoginCommand {// 입력받은 이메일, 암호를 전달시켜줄 클래스
	
	private String email;
	private String password;
	private String rememberEmail;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRememberEmail() {
		return rememberEmail;
	}
	public void setRememberEmail(String rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
	
	
}
