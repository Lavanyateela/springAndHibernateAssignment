package com.assignment.springboot.repository;

import com.assignment.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByCollegeId(int collegeId);
}
