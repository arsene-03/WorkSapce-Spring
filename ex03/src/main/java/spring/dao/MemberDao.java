package spring.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import spring.vo.Member;

public class MemberDao {
	
	private static long nextCode = 0; //시퀀스

	private Map<String,Member> map = new HashMap<>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insertMember(Member member) {
		member.setCode(++nextCode);
		map.put(member.getEmail(), member);
	}
	
	public Collection<Member> selectAll() {
		return map.values();
	}

	public void updateMember(Member m) {
		map.put(m.getEmail(), m);
	}
	
	
}
