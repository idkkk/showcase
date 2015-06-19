package org.rubik.datasource.repository;


import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rubik.datasource.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/app-context.xml")
//@Transactional
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Test
	public void testInsert() {
		
		for(int i = 0; i < 5000; i++) {
			User user = new User();
			user.setName("kevin");
			user.setCity("北京");
			user.setAge(30);
			user.setBirthday(new Date());
			
			repository.saveOrUpdate(user);
		}

		assertEquals(5000, repository.findAll().size());
	}
}
