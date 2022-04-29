package com.assignment.springboot.service;


import com.assignment.springboot.entity.Club;
import com.assignment.springboot.entity.College;
import com.assignment.springboot.entity.Student;
import com.assignment.springboot.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestOfStudentService {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testFindAllStudents()
    {
        when(studentRepository.findAll()).thenReturn(Stream.of(new Student("Test","Test","CSE","test@gmail.com")).collect(Collectors.toList()));
        assertEquals(1,studentService.findAll().size());
    }

    @Test
    public void testFindById()
    {
        Student student=new Student(553,"Lavanya","Teela","CSE","lavanya@gmail.com",null,null);

        when(studentRepository.findById(553)).thenReturn(Optional.of(student));
        assertEquals(student,studentService.findById(553));
    }

    @Test
    public void yestFindByCollegeId()
    {
        List<Student> students=new ArrayList<>();
        College college=new College(1,"MLRIT","Dundigal",students);
        when(studentRepository.findByCollegeId(1))
                .thenReturn(Stream.of(new Student(53,"Lavanya","Teela","CSE","lav@gmail.com",college,null)).collect(Collectors.toList()));
        assertEquals(1,studentService.findByCollegeId(1).size());
    }

    @Test
    public void testSaveStudent()
    {
        List<Student> students=new ArrayList<>();
        College college=new College(1,"MLRIT","Dundigal",students);

        Set<Club> clubs=new HashSet<>();

        clubs.add(new Club(2,"Sports Club",null));
        clubs.add(new Club(2,"Joy Club",null));

        Student stu=new Student(53,"Lavanya","Teela","CSE","lava@gmail.com",college,clubs);
        when(studentRepository.save(stu)).thenReturn(stu);
        assertEquals(stu,studentService.save(stu,1));
    }
}
