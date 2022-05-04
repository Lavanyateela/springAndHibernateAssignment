package com.assignment.springboot.service;

import com.assignment.springboot.dto.ClubDTO;
import com.assignment.springboot.entity.Club;
import com.assignment.springboot.repository.ClubRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public List<ClubDTO> findAll() {
        return clubRepository.findAll() .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public Club findById(int id) {
        Optional<Club> result=clubRepository.findById(id);
        Club club=null;
        if(result.isPresent())
        {
            club=result.get();
        }

        return club;
    }

    @Override
    public Club save(Club club) {
        clubRepository.save(club);
        return club;
    }

    @Override
    public void deleteById(int id) {
        clubRepository.deleteById(id);
    }

    public ClubDTO convertEntityToDto(Club club)
    {
        return modelMapper.map(club,ClubDTO.class);
    }
}
