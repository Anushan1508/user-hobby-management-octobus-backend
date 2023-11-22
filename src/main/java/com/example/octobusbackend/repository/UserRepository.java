package com.example.octobusbackend.repository;

import com.example.octobusbackend.entity.ContactNumber;
import com.example.octobusbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<Object> findByEmail(String email);
}
