package org.jsp.batchbootapp.exception;

import org.jsp.batchbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class BatchBootAppExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleMNFE(BatchNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("Batch Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleSNFE(StudentNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("Student Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}

}
