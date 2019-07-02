package com.springcouse.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcouse.domain.RequestStage;
import com.springcouse.dto.RequestStageSaveDto;
import com.springcouse.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {
	
	@Autowired
	private RequestStageService stageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDto stageDto){
		RequestStage stage = stageDto.transformToRequestStage();
		RequestStage stageCreated = stageService.save(stage);
		return ResponseEntity.status(HttpStatus.CREATED).body(stageCreated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		RequestStage stage = stageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
	@GetMapping
	public ResponseEntity<List<RequestStage>> listAll(){
		List<RequestStage> stages = stageService.listAll();
		return ResponseEntity.ok(stages);
	}

}
