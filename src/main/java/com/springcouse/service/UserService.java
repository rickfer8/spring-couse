package com.springcouse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcouse.domain.User;
import com.springcouse.exception.NotFoundException;
import com.springcouse.model.PageModel;
import com.springcouse.model.PageRequestModel;
import com.springcouse.repository.UserRepository;
import com.springcouse.service.util.HashUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		User updateUser = userRepository.save(user);
		return updateUser;
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);		
		return result.orElseThrow(() -> new NotFoundException("There are not User with id " + id ));				
	}
	
	public List<User> listAll(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public PageModel<User> lsitAllOnLazyMode(PageRequestModel pageRequestModel){
		Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
		Page<User> page = userRepository.findAll(pageable);
		
		PageModel<User> pm = new PageModel<User>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;		
	}
	
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<User> result = userRepository.login(email, password);
		return result.get();
	}

}
