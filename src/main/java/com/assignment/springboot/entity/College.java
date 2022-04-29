package com.assignment.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="college")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="college_id")
    private int id;

    @Column(name="college_name")
    @NotEmpty(message = "College name cannot be empty")
    private String collegeName;

    @Column(name="college_location")
    @NotEmpty(message = "College location cannot be empty")
    private String collegeLocation;

    @OneToMany(mappedBy = "college",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Student> students;

    public College(String collegeName, String collegeLocation) {
        this.collegeName = collegeName;
        this.collegeLocation = collegeLocation;
    }

    public void add(Student student)
    {
        if(students==null)
        {
            students=new ArrayList<>();
        }
        students.add(student);
        student.setCollege(this);
    }
}
