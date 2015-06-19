package org.rubik.datasource.repository;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rubik.datasource.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/app-context.xml")
//@Transactional
public class CompanyRepositoryTest {

	@Autowired
	CompanyRepository repository;

	@Test
	public void testInsert() {
		
		for(int i = 0; i < 5000; i++) {
			Company company = new Company();
			company.setId(100 + i);
			company.setName("kevin");
			company.setAge(30);
			repository.save(company);
			assertEquals(company.getAddress(), repository.findOne(company.getName()).getAddress());
		}

		assertEquals(5000, repository.findAll().size());
	}
}
