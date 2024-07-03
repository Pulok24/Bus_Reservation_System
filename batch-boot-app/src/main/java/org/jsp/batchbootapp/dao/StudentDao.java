package org.jsp.batchbootapp.dao;

import java.util.Optional;

import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.dto.Student;
import org.jsp.batchbootapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public Optional<Student> findById(int id) {
		return studentRepository.findById(id);
	}

	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}
}
