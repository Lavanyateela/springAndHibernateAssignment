package com.assignment.springboot.dto;


import com.assignment.springboot.entity.Club;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String studentDept;
    private String studentEmail;
    private List<Club> clubs;
    private String collegeName;
}
