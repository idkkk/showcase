package org.rubik.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.rubik.spring.domain.User;
import org.rubik.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "userCache", key = "#id")
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public List<User> findAll() {
		return Lists.newArrayList(userRepository.findAll());
	}

    
}
