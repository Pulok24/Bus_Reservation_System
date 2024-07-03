package org.jsp.hospitalbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalbootapp.dto.Doctor;
import org.jsp.hospitalbootapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class DoctorDao {
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	public Optional<Doctor> findById(int id){
		return doctorRepository.findById(id);
	}
	public Optional<Doctor> verifyByEmailAndPassword(String email,String password){
		return doctorRepository.verifyByEmailAndPassword(email, password);
	}
	public List<Doctor> findAllDoctor(){
		return doctorRepository.findAll();
	}
}
