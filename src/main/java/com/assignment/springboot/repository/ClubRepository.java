package com.assignment.springboot.repository;

import com.assignment.springboot.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club,Integer> {
}
