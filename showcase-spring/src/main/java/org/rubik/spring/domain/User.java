package org.rubik.spring.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "t_user")
public class User extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -2952735933715107252L;

	private String name;

	private String city;

	private Integer age;

	private String birthday;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
