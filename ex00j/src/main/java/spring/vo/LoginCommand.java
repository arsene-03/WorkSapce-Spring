package spring.vo;

public class LoginCommand { //입력받은 이메일 암호를 전달 시켜줄 클래스
	
	private String email;
	private String password;
	private boolean rememberEmail;  // 이메일일 쿠키에 저장할 정보
	
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
	public boolean isRememberEmail() {
		return rememberEmail;
	}
	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}

	
	
}
