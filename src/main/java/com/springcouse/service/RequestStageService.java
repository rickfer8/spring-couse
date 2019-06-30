package com.springcouse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcouse.domain.Request;
import com.springcouse.domain.RequestStage;
import com.springcouse.domain.enuns.RequestState;
import com.springcouse.repository.RequestRepository;
import com.springcouse.repository.RequestStageRepository;

@Service
public class RequestStageService {
	
	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {		
		stage.setRealizationDate(new Date());	
		RequestStage createdRequest = requestStageRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		requestRepository.updateStatus(requestId, state);
		
		return createdRequest;
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.get();
	}
	
	public List<RequestStage> listAllByRequestId(Long requestId){
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;
	}

}
