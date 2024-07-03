package org.jsp.hospitalbootapp.dao;

import java.util.Optional;

import org.jsp.hospitalbootapp.dto.Patient;
import org.jsp.hospitalbootapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDao {
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	public Optional<Patient> findById(int id){
		return patientRepository.findById(id);
	}
	public void deletePatient(int id){
		 patientRepository.deleteById(id);
	}
	public Optional<Patient> findByPhone(long phone){
		return patientRepository.findByPhone(phone);
	}
}
