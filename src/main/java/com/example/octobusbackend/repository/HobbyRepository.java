package com.example.octobusbackend.repository;

import com.example.octobusbackend.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface HobbyRepository extends JpaRepository<Hobby, Long>
{
    //check the hobby name is unique
    Boolean existsByHobbyName(String hobbyName);



}
