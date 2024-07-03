package org.jsp.batchbootapp.service;

import java.util.Optional;

import javax.management.ServiceNotFoundException;

import org.jsp.batchbootapp.dao.BatchDao;
import org.jsp.batchbootapp.dao.StudentDao;
import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.dto.ResponseStructure;
import org.jsp.batchbootapp.dto.Student;
import org.jsp.batchbootapp.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private BatchDao batchDao;
	
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student,int id) {
		ResponseStructure<Student> structure = new ResponseStructure<>();
		Optional<Batch> recBatch=batchDao.findById(id);
		if(recBatch.isPresent()) {
			Batch batch=recBatch.get();
			student.setBatch(batch);
			batch.getStudents().add(student);
			structure.setMessage("Student Saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setData(studentDao.saveStudent(student));
			batchDao.saveBatch(batch);
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
			throw new StudentNotFoundException("Cannot add Student as Batch Id is invalid");
	}
	public ResponseEntity<ResponseStructure<Student>> findById(int id){
		ResponseStructure<Student> structure=new ResponseStructure<>();
		Optional<Student> recStudent=studentDao.findById(id);
		if(recStudent.isPresent()) {
			structure.setData(recStudent.get());
			structure.setMessage("Student Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new StudentNotFoundException("Invalid Student Id");
	}
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int id){
		ResponseStructure<Student> structure=new ResponseStructure<>();
		Optional<Student> recStudent=studentDao.findById(id);
		if(recStudent.isPresent()) {
			Student student=recStudent.get();
			structure.setMessage("Student Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(student);
			studentDao.deleteStudent(id);
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new StudentNotFoundException("Cannot delete Student as Invalid student id");
	}
}
