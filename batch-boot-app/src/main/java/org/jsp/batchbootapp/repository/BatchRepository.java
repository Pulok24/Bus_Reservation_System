package org.jsp.batchbootapp.repository;

import java.util.List;

import java.util.Optional;

import org.jsp.batchbootapp.dto.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface BatchRepository extends JpaRepository<Batch, Integer>{

//	Optional<Batch> findById(int id);
	List<Batch> findByFaculty(String faculty);
	
	Optional<Batch> findByCode(String code);
	
//	@Query("select b from Batch b where b.id=?1 and b.code=?2")
	Optional<Batch> findByIdAndCode(int id,String code);
}