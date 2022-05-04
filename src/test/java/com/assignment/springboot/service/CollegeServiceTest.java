package com.assignment.springboot.service;

import com.assignment.springboot.entity.College;
import com.assignment.springboot.repository.CollegeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegeServiceTest {
    @Autowired
    private CollegeService collegeService;

    @MockBean
    private CollegeRepository collegeRepository;

    @Test
    public void testGetColleges() {
        when(collegeRepository.findAll())
                .thenReturn(Stream.of(new College("KMIT", "Narayanaguda"), new College("GRIET", "Nizampet")).collect(Collectors.toList()));

        assertEquals(2, collegeService.findAll().size());
    }

    @Test
    public void testFindById() {
        College college = new College(0, "KMIT", "Narayanaguda", null);

        when(collegeRepository.findById(0)).thenReturn(Optional.of(college));

        assertEquals(college, collegeService.findById(0));
    }

    @Test
    public void testSaveCollege() {
        College college = new College("KMIT", "Narayanaguda");
        when(collegeRepository.save(college)).thenReturn(college);
        College returnedCollege = collegeService.save(college);
        assertEquals(college, returnedCollege);
    }

    @Test
    public void testDeleteCollege() {
        College college = new College(0,"KMIT", "Narayanaguda", null);
        collegeService.deleteById(0);
        verify(collegeRepository, times(1)).deleteById(0);
    }
}