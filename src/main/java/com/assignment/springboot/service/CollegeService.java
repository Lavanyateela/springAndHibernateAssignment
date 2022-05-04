package com.assignment.springboot.service;

import com.assignment.springboot.dto.CollegeDTO;
import com.assignment.springboot.entity.College;

import java.util.List;

public interface CollegeService {
    public List<CollegeDTO> findAll();
    public College findById(int id);
    public College save(College college);
    public void deleteById(int id);
}
