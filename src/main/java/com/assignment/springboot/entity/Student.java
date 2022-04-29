package com.assignment.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student")
public class Student {

    private static final String EMAIL_REGEX = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "Student first name cannot be empty!")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Student last name cannot be empty!")
    private String lastName;

    @Column(name = "student_dept")
    @Pattern(regexp = "^[a-zA-Z]{2,3}$", message = "Length must be 2-3 characters")
    private String studentDept;


    @Column(name = "student_email")
    @Pattern(regexp = EMAIL_REGEX, message = "Invalid format.")
    private String studentEmail;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "college_id")
    @ToString.Exclude
    private College college;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "student_club",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id"))
    @NotEmpty(message = "Please select atleast one club!")
    private Set<Club> clubs = new HashSet<>();


    public Student(String firstName, String lastName, String studentEmail, String studentDept) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentEmail = studentEmail;
        this.studentDept= studentDept;
    }

    public Student(String firstName, String lastName, String studentEmail, String studentDept, College college) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentEmail = studentEmail;
        this.studentDept = studentDept;
        this.college = college;
    }



    public void addClub(Club club) {
        clubs.add(club);
    }

    public void removeClub(Club club) {
        this.clubs.remove(club);
    }
}