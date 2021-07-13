package spring.survey;

import java.util.List;

public class AnsweredData {//설문응답정보
	
	private List<String> response; // 설문응답정보를 담은 리스트
	private Respondent res; //응답자 정보를 담은 객체(주거지, 나이)
	
	public List<String> getResponse() {
		return response;
	}
	public void setResponse(List<String> response) {
		this.response = response;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	
	
}
