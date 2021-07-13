package spring.survey;

import java.util.List;

public class Question {
	private String title; //����
	private List<String> option; //����
	
	public Question(String title, List<String> option) {
		this.title = title;
		this.option = option;
	}
	
	

	public Question(String title) {
		this.title = title;
	}
	
	public boolean isChoice() { // ���� ��ư�� üũ �ߴ°� üũ
		return option != null && option.isEmpty();
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getOption() {
		return option;
	}

	public void setOption(List<String> option) {
		this.option = option;
	}
	
	
	
}