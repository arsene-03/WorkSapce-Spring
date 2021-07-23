package spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import spring.vo.ListCommand;
import spring.vo.Member;

public class MemberDao { // 데이터베이스 연결과 쿼리 전송
	
//	private JdbcTemplate jdbcTemplate;
//	
//	//생성자 주입
//	public MemberDao(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
//	
//	private RowMapper<Member> rowMapper = new RowMapper<Member>() {
//						@Override
//						public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//							System.out.println("출력 여부 확인8");
//							Member m = new Member(
//									rs.getString("email"),
//									rs.getString("password"),
//									rs.getString("name"),
//									rs.getTimestamp("regDate")
//								);
//							m.setCode(rs.getLong("id"));
//							return m;
//						}
//		
//					};
//	
//	public Member selectByEmail(String email) {
//		String sql = "SELECT * FROM Member WHERE email=?";
//		System.out.println("출력 여부 확인7");
//		List<Member> results = jdbcTemplate.query(
//				sql,rowMapper,email);
//		System.out.println("출력 여부 확인9");
//		return results.isEmpty() ? null : results.get(0);
//	}
//
//	public List<Member> selectAll() {
//		List<Member> results = jdbcTemplate.query(
//				"SELECT * FROM Member ORDER BY id ASC",	rowMapper);
//		return results;
//	}
//
//	public int count() {// 하나의 결과만 필요한 경우
//		Integer cnt = jdbcTemplate.queryForObject(
//				"SELECT count(*) FROM Member",Integer.class);
//
//		return cnt;
//	}
//	
//	
//	public void insertMember(Member member) {
//		KeyHolder key = new GeneratedKeyHolder();
//		jdbcTemplate.update(
//				new PreparedStatementCreator() {
//					
//					@Override
//					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//						PreparedStatement psmt = con.prepareStatement(
//								"INSERT INTO Member VALUES(member_seq.nextval,?,?,?,?)",
//								new String[] {"id"});
//						
//						psmt.setString(1,member.getEmail());
//						psmt.setString(2,member.getPassword());
//						psmt.setString(3,member.getName());
//						psmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
//						
//						return psmt;
//					}
//				},key);
//		Number keyValue = key.getKey();
//		member.setCode(keyValue.longValue());
//	}
//	
//	public void updateMember(Member m) {
//		int changeNum = jdbcTemplate.update(
//			"UPDATE Member SET name=?, password=? WHERE email=? ",
//			m.getName(),m.getPassword(),m.getEmail());
//		
//		System.out.println(changeNum+"개의 행이 변경되었습니다.");
//	}
//	
//	public void deleteMember(String email) {
//		
//		Member m  = selectByEmail(email);
//		try {
//			if(m != null) {
//				jdbcTemplate.update(
//						"DELETE FROM member WHERE email=?",email);
//				
//			}else {
//				throw new MemberNotFoundException();
//			}
//		}catch(MemberNotFoundException e) {
//			System.out.println("해당 이메일이 존재하지 않습니다.");
//		}
//	}
//	
//	public List<Member> selectByRegDate(Date from, Date to) {
//		List<Member> result = jdbcTemplate.query(
//				"SELECT * FROM Member WHERE regDate BETWEEN ? AND ? ORDER BY regDate ASC", 
//				rowMapper,from,to);
//		return result;
//	}
//	
//	public Member selectByCode(Long code) {
//		String sql = "SELECT * FROM Member WHERE id=?";
//		List<Member> results = jdbcTemplate.query(sql, rowMapper,code);
//		
//		return results.isEmpty() ? null : results.get(0);
//	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public Member selectByEmail(String email) {
		Member member = sqlSession.selectOne("mybatis.mapper.member.selectByEmail",email);
		return member;
	}
	
	public List<Member> selectAll() {
		List<Member> lists = sqlSession.selectList("mybatis.mapper.member.selectAll");
		return lists;
	}
	
	public int count() {
		return sqlSession.selectOne("mybatis.mapper.member.selectCount");
	}
	
	public List<Member> selectByRegDate(ListCommand searchDate) {
		List<Member> lists = sqlSession.selectList("mybatis.mapper.member.selectByRegdate",searchDate);
		return lists;
	}
	
	public Member selectByCode(Long code) {
		Member m = sqlSession.selectOne("mybatis.mapper.member.selectbyId",code);
		return m;
	}
	
	public int insertMember(Member member) {
		int result = sqlSession.insert("mybatis.mapper.member.insertMember",member);
		sqlSession.commit();
		return result;  //삽입,변경, 삭제된 데이터의 개수
	}
	
	public int updateMember(Member m) {
		int result = sqlSession.update("mybatis.mapper.member.updateMember",m);
		sqlSession.commit();
		return result; //삽입,변경, 삭제된 데이터의 개수
	}
	
	public int deleteMember(String email) {
		int result = sqlSession.delete("mybatis.mapper.member.deleteMember",email);
		sqlSession.commit();
		return result; //삽입,변경, 삭제된 데이터의 개수
	}
	
	
	
	
	
	
	
	
	
	
	
}
