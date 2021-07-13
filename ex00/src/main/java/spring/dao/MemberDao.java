package spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import spring.exception.MemberNotFoundException;
import spring.vo.Member;

public class MemberDao { // 데이터베이스 연결과 쿼리 전송
	
	private JdbcTemplate jdbcTemplate;
	
	//생성자 주입
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM Member WHERE email=?";
		
		List<Member> results = jdbcTemplate.query(
				sql,
				new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member m = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regDate")
							);
						m.setCode(rs.getLong("id"));
						return m;
					}
					
				},email);
		return results.isEmpty() ? null : results.get(0);
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query(
				"SELECT * FROM Member ORDER BY id ASC",
				new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member m = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regDate")
							);
						m.setCode(rs.getLong("id"));
						return m;
					}			
				});
		return results;
	}

	public int count() {// 하나의 결과만 필요한 경우
		Integer cnt = jdbcTemplate.queryForObject(
				"SELECT count(*) FROM Member",Integer.class);

		return cnt;
	}
	
	
	public void insertMember(Member member) {
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement psmt = con.prepareStatement(
								"INSERT INTO Member VALUES(member_seq.nextval,?,?,?,?)",
								new String[] {"id"});
						
						psmt.setString(1,member.getEmail());
						psmt.setString(2,member.getPassword());
						psmt.setString(3,member.getName());
						psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
						
						return psmt;
					}
				},key);
		Number keyValue = key.getKey();
		member.setCode(keyValue.longValue());
	}
	
	public void updateMember(Member m) {
		int changeNum = jdbcTemplate.update(
			"UPDATE Member SET name=?, password=? WHERE email=? ",
			m.getName(),m.getPassword(),m.getEmail());
		
		System.out.println(changeNum+"개의 행이 변경되었습니다.");
	}
	
	public void deleteMember(String email) {
		
		Member m  = selectByEmail(email);
		try {
			if(m != null) {
				jdbcTemplate.update(
						"DELETE FROM member WHERE email=?",email);
				
			}else {
				throw new MemberNotFoundException();
			}
		}catch(MemberNotFoundException e) {
			System.out.println("해당 이메일이 존재하지 않습니다.");
		}
	}
	
}
