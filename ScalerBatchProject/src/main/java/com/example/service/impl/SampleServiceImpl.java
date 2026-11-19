package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cfg.SampleException;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.model.StudentDTO;
import com.example.repo.CourseRepo;
import com.example.repo.StudentRepo;
import com.example.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	StudentRepo stdntRepo;

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	StudentMapper stdntMppr;

	@Override
	public Student addStudent(StudentDTO providedStudent) {
		Student stdnt = stdntMppr.mapStudentEntityFromStudentDto(providedStudent);
		return stdnt;
	}

	@Override
	public void deleteStudent(long studentId) throws Exception {
		Optional<Student> studentRecordToDelete = stdntRepo.findById(studentId);
		if (studentRecordToDelete.isPresent()) {
			studentRecordToDelete.get().getCourses().forEach(e -> {
				courseRepo.delete(e);
			});
			stdntRepo.delete(studentRecordToDelete.get());
		} else {
			throw new SampleException("Student with Id { " + studentId + " } not found");
		}
	}

	@Override
	public StudentDTO getStudent(long studentId) throws Exception {
		Optional<Student> studentRecordToDelete = stdntRepo.findById(studentId);
		if (studentRecordToDelete.isPresent()) {
			StudentDTO student = stdntMppr.mapStudentDtoFromStudentEntity(studentRecordToDelete.get());
			return student;
		} else {
			throw new SampleException("Student with Id { " + studentId + " } not found");
		}
	}

	@Override
	public StudentDTO updateStudent(Long studentId, StudentDTO providedStudentForUpdate) throws Exception {
		StudentDTO responsedto = null;
		Optional<Student> studentRecordToUpdate = stdntRepo.findById(studentId);
		if (studentRecordToUpdate.isPresent()) {
			Student studentToUpdate = studentRecordToUpdate.get();
			studentToUpdate.setName(providedStudentForUpdate.getName());
			responsedto = stdntMppr.mapStudentDtoFromStudentEntity(stdntRepo.save(studentToUpdate));
		} else {
			throw new SampleException("Student with Id { " + studentId + " } not found");
		}
		return responsedto;
	}

	@Override
	public ResponseEntity<String> updateCourse(Long Studentid, Long courseId, String updatedName)throws Exception {
		String responseText = "";
		if (courseRepo.updateCourse(Studentid, courseId, updatedName) == 1) {
			responseText = "RecordUpdated";
			return new ResponseEntity<>(responseText, HttpStatus.OK);
		} else {
			responseText = "Record with provided course Id :{" + courseId + "} not found";
			return new ResponseEntity<>(responseText, HttpStatus.NOT_FOUND);
		}

	}

}
