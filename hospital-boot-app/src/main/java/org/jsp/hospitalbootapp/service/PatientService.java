package org.jsp.hospitalbootapp.service;

import java.util.Optional;

import org.jsp.hospitalbootapp.dao.DoctorDao;
import org.jsp.hospitalbootapp.dao.PatientDao;
import org.jsp.hospitalbootapp.dto.Doctor;
import org.jsp.hospitalbootapp.dto.Patient;
import org.jsp.hospitalbootapp.dto.ResponseStructure;
import org.jsp.hospitalbootapp.exception.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PatientService {
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private DoctorDao doctorDao;
	
	public ResponseEntity<ResponseStructure<Patient>> savePatient(@RequestBody Patient patient,int doctor_id){
		ResponseStructure<Patient> structure=new ResponseStructure<>();
		Optional<Doctor> recDoctor=doctorDao.findById(doctor_id);
		if(recDoctor.isPresent()) {
			Doctor dbdoctor=recDoctor.get();
			patient.setDoctor(dbdoctor);
			dbdoctor.getPatients().add(patient);
			structure.setMessage("Patient Saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setData(patientDao.savePatient(patient));
			doctorDao.saveDoctor(dbdoctor);
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new PatientNotFoundException("Cannot Update as Invalid Doctor Id");
	}
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(@RequestBody Patient patient){
		ResponseStructure<Patient> structure=new ResponseStructure<>();
		Optional<Patient> recPatient=patientDao.findById(patient.getId());
		if(recPatient.isPresent()) {
			Patient dbPatient=recPatient.get();
			dbPatient.setName(patient.getName());
			dbPatient.setPhone(patient.getPhone());
			structure.setMessage("Patient Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setData(patientDao.savePatient(dbPatient));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new PatientNotFoundException("Invalid id");
	}
	public ResponseEntity<ResponseStructure<Patient>> deletePatient(int id){
		ResponseStructure<Patient> structure=new ResponseStructure<>();
		Optional<Patient> recPatient=patientDao.findById(id);
		if(recPatient.isPresent()) {
			structure.setMessage("Patient Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recPatient.get());
			patientDao.deletePatient(id);
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new PatientNotFoundException("Invalid Id");
	}
	public ResponseEntity<ResponseStructure<Patient>> findByPhone(long phone){
		ResponseStructure<Patient> structure=new ResponseStructure<>();
		Optional<Patient> recPatient=patientDao.findByPhone(phone);
		if(recPatient.isPresent()) {
			structure.setMessage("Patient found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recPatient.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new PatientNotFoundException("Patient not found as invalid phone number");
		
	}
}
