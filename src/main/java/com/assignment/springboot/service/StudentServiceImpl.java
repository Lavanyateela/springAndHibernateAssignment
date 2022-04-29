package com.assignment.springboot.service;

import com.assignment.springboot.dto.StudentDTO;
import com.assignment.springboot.entity.College;
import com.assignment.springboot.entity.Student;
import com.assignment.springboot.repository.CollegeRepository;
import com.assignment.springboot.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<StudentDTO> findAll() {

        return studentRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Student findById(int id) {
        Optional<Student> result=studentRepository.findById(id);
        Student theStudent=null;
        if(result.isPresent())
        {
            theStudent=result.get();
        }
        return theStudent;
    }



    @Override
    public Student save(Student student,int collegeId) {
        College college=collegeRepository.findById(collegeId).get();
        college.add(student);
        studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByCollegeId(int collegeId) {
        return studentRepository.findByCollegeId(collegeId);
    }


    @Override
    public StudentDTO convertEntityToDto(Student student) {
        return modelMapper.map(student,StudentDTO.class);
    }
}
