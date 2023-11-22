package com.example.octobusbackend.repository;

import com.example.octobusbackend.entity.ContactNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactNumberRepository extends JpaRepository<ContactNumber, Long> {
}
