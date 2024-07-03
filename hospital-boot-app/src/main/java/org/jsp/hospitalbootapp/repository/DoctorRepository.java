package org.jsp.hospitalbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalbootapp.dto.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	@Query("select d from Doctor d where d.email=?1 and d.password=?2")
	public Optional<Doctor> verifyByEmailAndPassword(String email,String password);

	
}
