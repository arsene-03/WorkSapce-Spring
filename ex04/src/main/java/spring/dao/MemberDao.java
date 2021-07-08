package spring.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import spring.vo.Member;

public class MemberDao {
	
	private static long nextId = 0;  //DB에서 시퀀스
	
	private Map<String,Member> map = new HashMap<>();
	
	// 이메일로 데이터 조회
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	// 전체 데이터 조회
	public Collection<Member> selectAll(){
		return map.values();
	}
	
	
	// 데이터 입력
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
		
	}
	
	// 데이터 변경
	public void updatge(Member member) {
		map.put(member.getEmail(), member);
		
	}
}
