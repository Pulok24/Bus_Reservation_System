package org.jsp.hospitalbootapp.repository;

import java.util.Optional;

import org.jsp.hospitalbootapp.dto.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	public Optional<Patient> findByPhone(long phone);

}
