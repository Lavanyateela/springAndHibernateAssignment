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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;


    @Test
    public void testFindAllStudents() {
        when(studentRepository.findAll()).thenReturn(Stream.of(new Student("Test", "Test", "CSE", "test@test.com")).collect(Collectors.toList()));

        assertEquals(1, studentService.findAll().size());
    }

    @Test
    public void testFindById() {
        Student student = new Student(4,"Gopi", "Prajeev", "CSE", "test@gmail.com", null, null);

        when(studentRepository.findById(4)).thenReturn(Optional.of(student));
        assertEquals(student, studentService.findById(4));
    }


    @Test
    public void testFindByCollegeId() {
        List<Student> students = new ArrayList<>();
        College college = new College(1, "KMIT", "NRG", students);

        when(studentRepository.findByCollegeId(1))
                .thenReturn(Stream.of(new Student(139, "Gopi", "Prajeev", "CSE", "bgp2302@gmail.com", college, null)).collect(Collectors.toList()));

        assertEquals(1, studentService.findByCollegeId(1).size());
    }

    @Test
    public void testDeleteById() {
        Student s = new Student(4,"Gopi", "Prajeev", "CSE", "test@gmail.com", null, null);
        studentService.deleteById(4);
        verify(studentRepository, times(1)).deleteById(4);
    }

    @Test
    public void testSaveStudent() {
        List<Student> students = new ArrayList<>();
        College college = new College(1, "KMIT", "NRG", students);

        Set<Club> clubs = new HashSet<>();

        clubs.add(new Club(2, "Racing Club", null));
        clubs.add(new Club(2, "Sports Club", null));
        Student s = new Student(139, "Gopi", "Prajeev", "CSE", "bgp2302@gmail.com", college, clubs);

        when(studentRepository.save(s)).thenReturn(s);

        assertEquals(s, studentService.save(s, 1));
    }
}