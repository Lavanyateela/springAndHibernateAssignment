package com.assignment.springboot.service;

import com.assignment.springboot.dto.StudentDTO;
import com.assignment.springboot.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
   List<StudentDTO> findAll();
   Student findById(int id);
   Student save(Student student,int collegeId);
   void deleteById(int id);
   List<Student> findByCollegeId(int collegeId);
   StudentDTO convertEntityToDto(Student student);
}
