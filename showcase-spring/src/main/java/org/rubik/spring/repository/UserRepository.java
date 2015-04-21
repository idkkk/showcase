package org.rubik.spring.repository;

import java.util.Date;

import org.rubik.spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByName(String name);

	Page<User> findByBirthdayBefore(Date date, Pageable pageable);

//	@Query("select u from User u where u.firstname = ?1")
//	List<User> findByFirstname(String firstname);
}
