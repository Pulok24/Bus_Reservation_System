package org.jsp.batchbootapp.dao;

import java.util.List;


import java.util.Optional;

import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BatchDao {
	@Autowired
	private BatchRepository batchRepository;
	
	public Batch saveBatch(Batch batch) {
		return batchRepository.save(batch);
	}
	public Optional<Batch> findById(int id){
		return batchRepository.findById(id);
	}
	public List<Batch> findByFaculty(String faculty) {
		return batchRepository.findByFaculty(faculty);
	}
	public Optional<Batch> findByCode(String code){
		return batchRepository.findByCode(code);
	}
	public Optional<Batch> verifyByIdAndCode(int id,String code){
		return batchRepository.findByIdAndCode(id, code);
	}
//	public void deleteBatch(int id){
//		 batchRepository.deleteById(id);;
//	}
	public List<Batch> findAll(){
		return batchRepository.findAll();
	}
}
