package com.assignment.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="club")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="club_name")
    @NotEmpty(message = "Club name cannot be empty")
    private String clubName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name="student_club",
               joinColumns = @JoinColumn(name="club_id"),
                inverseJoinColumns = @JoinColumn(name="student_id"))
    private Set<Student> students=new HashSet<>();

    public Club(String clubName)
    {
        this.clubName=clubName;
    }

    @Override
    public String toString() {
        return this.clubName;
    }


}
