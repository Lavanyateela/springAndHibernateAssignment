package com.assignment.springboot.repository;

import com.assignment.springboot.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Integer> {
}
