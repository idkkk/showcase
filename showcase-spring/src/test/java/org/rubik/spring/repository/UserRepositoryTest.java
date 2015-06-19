package org.rubik.spring.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rubik.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/app-context.xml")
//@Transactional
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Test
	public void testInsert() {
		User user = new User();
		user.setName("kevin");
		user.setCity("北京");
		user.setAge(30);
		user.setBirthday("2015-06-08");

		user = repository.save(user);

		assertEquals(user, repository.findOne(user.getId()));
	}
}
