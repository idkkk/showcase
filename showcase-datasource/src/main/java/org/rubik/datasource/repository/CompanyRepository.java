package org.rubik.datasource.repository;

import java.util.List;

import org.rubik.datasource.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Company findOne(String name) {
		String sql = "SELECT * FROM company WHERE name=?";
		Object[] args = new Object[]{ name };
		List<Company> companys = jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(Company.class));

		Company company = null;
		if (companys.size() != 0) {
			company = companys.get(0);
		}
		return company;
	}

	public List<Company> findAll() {
		String sql = "SELECT * FROM company";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Company.class));
	}

	public void save(Company company) {
		String sql = "INSERT INTO company(id, name, age) VALUES(?, ?, ?)";
		Object[] args = new Object[]{ company.getId(), company.getName(), company.getAge()};
		jdbcTemplate.update(sql, args);
	}
}
