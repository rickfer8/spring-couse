package com.springcouse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcouse.domain.Request;
import com.springcouse.domain.User;
import com.springcouse.domain.enuns.RequestState;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null, "Novo Laptop HP","Pretendo adquirir um laptop da HP",new Date(),RequestState.OPEN,owner,null);
		
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(1L, "Semi-Novo Laptop HP","Pretendo adquirir um laptop da HP",null,RequestState.OPEN,owner,null);
		
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getSubject()).isEqualTo("Semi-Novo Laptop HP");
	}
	
	@Test
	public void getByIdTest() {
		Optional<Request> result = requestRepository.findById(1L);
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Semi-Novo Laptop HP");
	}
	
	@Test
	public void listTest() {
		List<Request> requests = requestRepository.findAll();		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void listByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void updateStatusTest() {
		int affectedRows= requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);				
		assertThat(affectedRows).isEqualTo(1);
	}

}
