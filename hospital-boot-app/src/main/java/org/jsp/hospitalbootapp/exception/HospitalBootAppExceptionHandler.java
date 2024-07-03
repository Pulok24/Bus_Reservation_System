package org.jsp.hospitalbootapp.exception;

import org.jsp.hospitalbootapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class HospitalBootAppExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(DoctorNotFoundException.class)
public ResponseEntity<ResponseStructure<String>> HandleDNFE(DoctorNotFoundException exception){
	ResponseStructure<String> structure=new ResponseStructure<>();
	structure.setData("Doctor not found");
	structure.setMessage(exception.getMessage());
	structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
}
@ExceptionHandler(PatientNotFoundException.class)
public ResponseEntity<ResponseStructure<String>> HandlePNFE(PatientNotFoundException exception){
	ResponseStructure<String> structure=new ResponseStructure<>();
	structure.setMessage("Patient Not Found");
	structure.setData(exception.getMessage());
	structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
}
}
