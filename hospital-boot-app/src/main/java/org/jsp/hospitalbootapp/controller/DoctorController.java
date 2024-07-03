package org.jsp.hospitalbootapp.controller;

import java.util.List;

import org.jsp.hospitalbootapp.dto.Doctor;
import org.jsp.hospitalbootapp.dto.ResponseStructure;
import org.jsp.hospitalbootapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Doctor>> saveDoctor(@RequestBody Doctor doctor){
		return doctorService.saveDoctor(doctor);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Doctor>> findById(@PathVariable(name = "id") int id){
		return doctorService.findById(id);
	}
	@PostMapping("/verifyDoctor")
	public ResponseEntity<ResponseStructure<Doctor>> verifyByEmailAndPassword(@RequestParam(name = "email") String email,@RequestParam(name = "password") String password){
		return doctorService.verifyByEmailAndPassword(email, password);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Doctor>>> findAllDoctor(){
		return doctorService.findAllDoctor();
	}
}
