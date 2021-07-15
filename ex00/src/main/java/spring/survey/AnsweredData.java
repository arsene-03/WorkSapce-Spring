package spring.survey;

import java.util.List;

public class AnsweredData { // 설문 응답 정보 => 커맨드 객체
	
	private List<String> responses;	// 설문 응답 정보를 담은 리스트
	private Respondent res;			// 응답자 정보를 담은 객체(주거지, 나이)
	
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	
}
