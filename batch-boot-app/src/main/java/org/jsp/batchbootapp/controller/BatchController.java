package org.jsp.batchbootapp.controller;

import java.util.List;



import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.dto.ResponseStructure;
import org.jsp.batchbootapp.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batches")
public class BatchController {
	@Autowired
	private BatchService batchService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Batch>> saveMerchant(@RequestBody Batch batch) {
		return batchService.saveBatch(batch);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Batch>> findById(@PathVariable(name = "id") int id){
		return batchService.findById(id);
	}
	@GetMapping("/find-by-faculty/{faculty}")
	public  ResponseStructure<List<Batch>> findByFaculty(@PathVariable(name = "faculty") String faculty) {
		return batchService.findByFaculty(faculty);
	}
	@GetMapping("/find-by-code/{code}")
	public ResponseEntity<ResponseStructure<Batch>> findByCode(@PathVariable(name = "code") String code){
		return batchService.findByCode(code);
	}
	@PostMapping("/find-by-idandcode")
	public ResponseEntity<ResponseStructure<Batch>> verifyByIdAndCode(@RequestParam(name = "id") int id,@RequestParam(name = "code") String code){
		return batchService.verifyByIdAndCode(id, code);
	}
//	@DeleteMapping("/{id}")
//	public ResponseEntity<ResponseStructure<Batch>> deleteBatch(@PathVariable(name = "id") int id){
//		return batchService.deleteBatch(id);
//	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Batch>>> findAllBatches(){
		return batchService.findAllBatches();
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Batch>> updateBatch(@RequestBody Batch batch){
		return batchService.updateBatch(batch);
	}

}
