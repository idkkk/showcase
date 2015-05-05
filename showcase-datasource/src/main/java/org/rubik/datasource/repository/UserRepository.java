package org.rubik.datasource.repository;

import java.util.List;

import org.rubik.datasource.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findOne(int age) {
		String sql = "select * from t_user where age=?";
		Object[] args = new Object[]{ age };
		List<User> users = jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(User.class));

		User user = null;
		if (users.size() != 0) {
			user = users.get(0);
		}
		return user;
	}
	
	public User findOne(String name) {
		String sql = "select * from t_user where name=?";
		Object[] args = new Object[]{ name };
		List<User> users = jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(User.class));

		User user = null;
		if (users.size() != 0) {
			user = users.get(0);
		}
		return user;
	}

	public List<User> findAll() {
		String sql = "select * from t_user";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
	}

	public void saveOrUpdate(User user) {
		if (user.getId() != null) {
			String sql = "update user set name=?, age=?, birthday=?, city=?, name=? where id=?";
			Object[] args = new Object[]{ user.getName(), user.getAge(), user.getBirthday(), user.getCity(), user.getName(), user.getId() };
			jdbcTemplate.update(sql, args);
		} else {
			String sql = "insert into user(name, age, birthday, city, name) values(?, ?, ?, ?, ?)";
			Object[] args = new Object[]{ user.getName(), user.getAge(), user.getBirthday(), user.getCity(), user.getName() };
			jdbcTemplate.update(sql, args);
		}
		
	}
}
