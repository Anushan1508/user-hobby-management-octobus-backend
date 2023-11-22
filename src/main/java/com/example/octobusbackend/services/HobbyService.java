package com.example.octobusbackend.services;

import com.example.octobusbackend.dto.addHobby.HobbyRequestDTO;
import com.example.octobusbackend.dto.responseDTO.ResponseDTO;
import com.example.octobusbackend.entity.Hobby;
import com.example.octobusbackend.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HobbyService {
    @Autowired
    private HobbyRepository hobbyRepository;

    public ResponseDTO addHobby(HobbyRequestDTO hobbyRequestDTO) {
        if (hobbyRepository.existsByHobbyName(hobbyRequestDTO.getName())) {
            throw new RuntimeException("Hobby name already exists.");
        } else {
            Hobby hobby = Hobby.builder()
                    .hobbyName(hobbyRequestDTO.getName())
                    .build();
            hobbyRepository.save(hobby);
            ResponseDTO responseDTO = new ResponseDTO("success", "Hobby added successfully.");

        return responseDTO;
    }
    }

    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    public void deleteHobby(Long hobbyId) {
        //throw exception if hobby not found
        hobbyRepository.findById(hobbyId).orElseThrow(() -> new RuntimeException("Hobby not found"));
        hobbyRepository.deleteById(hobbyId);
    }
}
