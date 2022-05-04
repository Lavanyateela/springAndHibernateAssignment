package com.assignment.springboot.service;

import com.assignment.springboot.dto.CollegeDTO;
import com.assignment.springboot.entity.College;
import com.assignment.springboot.repository.CollegeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CollegeServiceImpl implements CollegeService{

    @Autowired
    private ModelMapper modelMapper;

    private CollegeRepository collegeRepository;

    @Autowired
    public CollegeServiceImpl(CollegeRepository collegeRepository)
    {
        this.collegeRepository=collegeRepository;
    }

    @Override
    public List<CollegeDTO> findAll() {
        return collegeRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    public CollegeDTO convertEntityToDto(College college) {
        return modelMapper.map(college,CollegeDTO.class);
    }

    @Override
    public College findById(int id) {
        Optional<College> result=collegeRepository.findById(id);
        College college=null;
        if(result.isPresent())
        {
            college=result.get();
        }

        return college;
    }

    @Override
    public College save(College college) {

        collegeRepository.save(college);
        return college;
    }

    @Override
    public void deleteById(int id) {
        collegeRepository.deleteById(id);
    }
}
