package spring.survey;

import java.util.List;

public class AnsweredData {//������������
	
	private List<String> response; // �������������� ���� ����Ʈ
	private Respondent res; //������ ������ ���� ��ü(�ְ���, ����)
	
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
