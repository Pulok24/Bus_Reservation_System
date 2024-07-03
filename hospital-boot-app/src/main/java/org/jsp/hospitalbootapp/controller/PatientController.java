package org.jsp.hospitalbootapp.controller;

import org.jsp.hospitalbootapp.dto.Patient;
import org.jsp.hospitalbootapp.dto.ResponseStructure;
import org.jsp.hospitalbootapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/{doctor_id}")
	public ResponseEntity<ResponseStructure<Patient>> savePatient(@RequestBody Patient patient,@PathVariable(name = "doctor_id") int doctor_id){
		return patientService.savePatient(patient, doctor_id);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(@RequestBody Patient patient){
		return patientService.updatePatient(patient);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Patient>> deletePatient(@PathVariable(name = "id") int id){
		return patientService.deletePatient(id);
	}
	@GetMapping("/find-by-phone/{phone}")
	public ResponseEntity<ResponseStructure<Patient>> findByPhone(@PathVariable(name = "phone") long phone){
		return patientService.findByPhone(phone);
	}
}
