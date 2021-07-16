package spring.vo;

import java.util.Date;

import spring.exception.IdPasswordNotMatchingException;

public class Member {  //실제 저장용 클래스
	
	private Long code;
	private String email;
	private String password;
	private String name;
	private Date registerDate;
	
	public Member (String email, String password, String name, Date registerDate) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if(!oldPassword.equals(password)) {
			throw new IdPasswordNotMatchingException();
		}
		this.password = newPassword;
	}
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	
	
	

}
