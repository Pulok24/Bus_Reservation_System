package org.jsp.batchbootapp.service;

import java.util.List;


import java.util.Optional;

import org.jsp.batchbootapp.dao.BatchDao;
import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.dto.ResponseStructure;
import org.jsp.batchbootapp.exception.BatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BatchService {
	@Autowired
	private BatchDao batchDao;
	
	public ResponseEntity<ResponseStructure<Batch>> saveBatch(@RequestBody Batch batch) {
		ResponseStructure<Batch> structure = new ResponseStructure<>();
		structure.setMessage("Batch Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(batchDao.saveBatch(batch));
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	public ResponseEntity<ResponseStructure<Batch>> findById(int id){
		ResponseStructure<Batch> structure=new ResponseStructure<>();
		Optional<Batch> recBatch=batchDao.findById(id);
		if(recBatch.isPresent()) {
			structure.setMessage("Batch Found");
			structure.setData(recBatch.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BatchNotFoundException("Invalid Batch Id");
	}
	public ResponseStructure<List<Batch>> findByFaculty( String faculty) {
		ResponseStructure<List<Batch>> structure = new ResponseStructure<>();
		structure.setMessage("These are the Batches which are matches with faculty");
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setData(batchDao.findByFaculty(faculty));
		return structure;
	}
	public ResponseEntity<ResponseStructure<Batch>> findByCode(String code){
		ResponseStructure<Batch> structure=new ResponseStructure<>();
		Optional<Batch> recBatch=batchDao.findByCode(code);
		if(recBatch.isPresent()) {
			structure.setData(recBatch.get());
			structure.setMessage("Batch Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BatchNotFoundException("Invalid Batch Code");
	}
	public ResponseEntity<ResponseStructure<Batch>> verifyByIdAndCode(int id,String code){
		ResponseStructure<Batch> structure=new ResponseStructure<>();
		Optional<Batch> recBatch=batchDao.verifyByIdAndCode(id, code);
		if(recBatch.isPresent()) {
			structure.setData(recBatch.get());
			structure.setMessage("Batch Verified Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BatchNotFoundException("Invalid Batch Id or Code");
	}
//	public ResponseEntity<ResponseStructure<Batch>> deleteBatch(int id) {
//		ResponseStructure<Batch> structure = new ResponseStructure<>();
//		Optional<Batch> recBatch = batchDao.findById(id);
//		if (recBatch.isPresent()) {
//			structure.setMessage("Batch Deleted");
//			structure.setStatusCode(HttpStatus.OK.value());
//			structure.setData(recBatch.get());
//			batchDao.deleteBatch(id);
//			return ResponseEntity.status(HttpStatus.OK).body(structure);
//		}
//		throw new BatchNotFoundException("Batch not found as Invalid id");
//	}
	public ResponseEntity<ResponseStructure<List<Batch>>> findAllBatches(){
		ResponseStructure<List<Batch>> structure=new ResponseStructure<>();
//		List<Batch> dbBatch=batchDao.findAll();
		structure.setData(batchDao.findAll());
		structure.setMessage("These are all Batches");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	public ResponseEntity<ResponseStructure<Batch>> updateBatch(@RequestBody Batch batch){
		ResponseStructure<Batch> structure=new ResponseStructure<>();
		Optional<Batch> recBatch=batchDao.findById(batch.getId());
		if(recBatch.isPresent()) {
			Batch dbBatch=recBatch.get();
			dbBatch.setCode(batch.getCode());
			dbBatch.setFaculty(batch.getFaculty());
			structure.setData(batchDao.saveBatch(dbBatch));
			structure.setMessage("Batch Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new BatchNotFoundException("Unable to update as invalid id");
	}
}
