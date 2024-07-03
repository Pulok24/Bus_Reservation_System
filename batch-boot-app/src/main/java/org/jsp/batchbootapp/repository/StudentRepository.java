package org.jsp.batchbootapp.repository;

import java.util.Optional;

import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student, Integer>{

//	Optional<Student> findById(int id);
	
}
