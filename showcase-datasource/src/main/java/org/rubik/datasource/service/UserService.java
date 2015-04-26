package org.rubik.datasource.service;

import java.util.List;

import org.rubik.datasource.domain.User;
import org.rubik.datasource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "userCache", key="#name")
	public User findByName(String name) {
		return userRepository.findOne(name);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.saveOrUpdate(user);
	}
}
