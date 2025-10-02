package com.dacs.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacs.backend.model.entity.User;
import com.dacs.backend.model.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		Optional<User> user = getById(id);
		userRepository.delete(user.get());
	}

	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> find(Map<String, Object> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public User getBy(Map<String, Object> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean existById(Long id) {
		return userRepository.existsById(id);
	}
}
