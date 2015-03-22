package com.springMVC.hello.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.springMVC.hello.model.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void saveUser(User user) {
		String sql = "INSERT INTO User"
				+ "(NAME,EMAIL,USERNAME,PASSWORD) VALUES (?, ?, ?,?)";
		jdbcTemplate.update(sql, new Object[] { user.getName(),
				user.getEmail(), user.getUsername(), user.getPassword() });
	}

	public User checkLogin(String username, String password) {
		String sql = "SELECT * FROM User where username=? and password=?";
		try {
			return jdbcTemplate.queryForObject(sql,
					new ParameterizedRowMapper<User>() {
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							User spittler = new User();
							spittler.setName(rs.getString("name"));
							spittler.setEmail(rs.getString("email"));
							spittler.setUsername(rs.getString("username"));
							spittler.setPassword(rs.getString("password"));
							return spittler;
						}
					}, username, password);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<User> serachAllUser() {
		String sql = "SELECT * FROM User";
		return jdbcTemplate.query(sql, new UserRowMapper());
	}

	public class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
			User spittler = new User();
			spittler.setId(rs.getString("id"));
			spittler.setName(rs.getString("name"));
			spittler.setEmail(rs.getString("email"));
			spittler.setUsername(rs.getString("username"));
			spittler.setPassword(rs.getString("password"));
			return spittler;
		}
	}

	public User getUser(String userId) {
		String sql = "SELECT * FROM User where id= ?";
		User user = (User) jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, new BeanPropertyRowMapper<User>(
						User.class));

		return user;
	}

	public void deleteUser(String userId) {
		String sql = "DELETE FROM User where id=" + userId;
		jdbcTemplate.update(sql);
	}

	public void updateUser(User user) {
		String sql =  "update User set name = ?,email=?,username=?,password=? where id = ?";
		jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getUsername(),user.getPassword(),user.getId());
	}
}