package org.jsp.hospitalbootapp.service;

import java.util.List;
import java.util.Optional;

import javax.print.Doc;

import org.jsp.hospitalbootapp.dao.DoctorDao;
import org.jsp.hospitalbootapp.dto.Doctor;
import org.jsp.hospitalbootapp.dto.ResponseStructure;
import org.jsp.hospitalbootapp.exception.DoctorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao doctorDao;
	
	public ResponseEntity<ResponseStructure<Doctor>> saveDoctor(@RequestBody Doctor doctor){
		ResponseStructure<Doctor> structure=new ResponseStructure<>();
		structure.setMessage("Doctor Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(doctorDao.saveDoctor(doctor));
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	public ResponseEntity<ResponseStructure<Doctor>> findById(int id){
		ResponseStructure<Doctor> structure=new ResponseStructure<>();
		Optional<Doctor> recDoctor=doctorDao.findById(id);
		if(recDoctor.isPresent()) {
			 structure.setMessage("Doctor Found");
			 structure.setData(recDoctor.get());
			 structure.setStatusCode(HttpStatus.OK.value());
			 return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new DoctorNotFoundException("Invalid Doctor Id");
	}
	public ResponseEntity<ResponseStructure<Doctor>> verifyByEmailAndPassword(String email,String password){
		ResponseStructure<Doctor> structure=new ResponseStructure<>();
		Optional<Doctor> recDoctor=doctorDao.verifyByEmailAndPassword(email, password);
		if(recDoctor.isPresent()) {
			structure.setMessage("Doctor verified successfuly");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recDoctor.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new DoctorNotFoundException("Invalid email or password");
	}
	public ResponseEntity<ResponseStructure<List<Doctor>>> findAllDoctor(){
		ResponseStructure<List<Doctor>> structure=new ResponseStructure<>();
		List<Doctor> dbDoctor=doctorDao.findAllDoctor();
		if(dbDoctor.isEmpty()) {
			throw new DoctorNotFoundException("Doctor not found");
		}
		structure.setMessage("These are all Doctors");
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setData(dbDoctor);
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
}
