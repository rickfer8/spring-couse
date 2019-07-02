package com.springcouse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcouse.domain.Request;
import com.springcouse.domain.enuns.RequestState;
import com.springcouse.exception.NotFoundException;
import com.springcouse.model.PageModel;
import com.springcouse.model.PageRequestModel;
import com.springcouse.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	public Request save(Request request) {
		request.setCriationDate(new Date());
		request.setState(RequestState.OPEN);
		
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}
	
	public Request update(Request request) {	
		Request updateRequest = requestRepository.save(request);
		return updateRequest;
	}
	
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There are not Request with id " + id ));
	}
	
	public List<Request> listAll(){
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public PageModel<Request> lsitAllOnLazyMode(PageRequestModel pageRequestModel){
		Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
		Page<Request> page = requestRepository.findAll(pageable);
		
		PageModel<Request> pm = new PageModel<Request>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;		
	}
	
	public List<Request> listAllByOwnerId(Long ownerId){
		List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
		return requests;
	}
	
	public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(),page.getSize(),page.getTotalPages(),page.getContent());
		return pm;
	}

}
