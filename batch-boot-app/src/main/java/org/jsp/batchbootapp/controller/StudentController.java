package org.jsp.batchbootapp.controller;

import org.jsp.batchbootapp.dto.Batch;
import org.jsp.batchbootapp.dto.ResponseStructure;
import org.jsp.batchbootapp.dto.Student;
import org.jsp.batchbootapp.service.BatchService;
import org.jsp.batchbootapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student,@PathVariable(name = "id") int id) {
		return studentService.saveStudent(student,id);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> findById(@PathVariable(name = "id") int id){
		return studentService.findById(id);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(@PathVariable(name = "id") int id){
		return studentService.deleteStudent(id);
	}
}
