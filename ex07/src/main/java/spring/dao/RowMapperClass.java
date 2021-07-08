package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import spring.vo.Member;

public class RowMapperClass implements RowMapper{

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

}
