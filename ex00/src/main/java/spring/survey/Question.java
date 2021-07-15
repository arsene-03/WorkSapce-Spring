package spring.survey;

import java.util.List;

public class Question {
	private String title;			// 질문
	private List<String> option;	// 응답
	
	public Question(String title, List<String> option) {
		this.title = title;
		this.option = option;
	}

	public Question(String title) {
		this.title = title;
	}
	
	public boolean isChoice() { // 라디오 버튼을 체크했는지 알기 위한 메서드
		return option!=null && !option.isEmpty();
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
