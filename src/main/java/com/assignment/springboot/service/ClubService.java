package com.assignment.springboot.service;

import com.assignment.springboot.dto.ClubDTO;
import com.assignment.springboot.entity.Club;

import java.util.List;
public interface ClubService {
    public List<ClubDTO> findAll();
    public Club findById(int id);
    public Club save(Club club);
    public void deleteById(int id);
}
