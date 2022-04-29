package com.assignment.springboot.service;

import com.assignment.springboot.dto.CollegeDTO;
import com.assignment.springboot.entity.College;

import java.util.List;
import java.util.Optional;

public interface CollegeService {
    public List<CollegeDTO> findAll();
    public College findById(int id);
    public College save(College college);
    public void deleteById(int id);
}
