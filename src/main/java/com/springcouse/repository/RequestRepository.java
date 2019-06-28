package com.springcouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcouse.domain.Request;
import com.springcouse.domain.enuns.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
	
	public List<Request> findAllByOwnerId(Long id);
	
	@Query("UPDATE Request SET state = ?2 WHERE id = ?1")
	public Request update(Long id, RequestState state);

}
