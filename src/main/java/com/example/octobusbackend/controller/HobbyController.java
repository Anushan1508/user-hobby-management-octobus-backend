package com.example.octobusbackend.controller;

import com.example.octobusbackend.dto.addHobby.HobbyRequestDTO;
import com.example.octobusbackend.dto.responseDTO.ResponseDTO;
import com.example.octobusbackend.entity.Hobby;
import com.example.octobusbackend.services.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hobby")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class HobbyController {
    @Autowired
    private HobbyService hobbyService;
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addHobby(@RequestBody HobbyRequestDTO hobbyRequestDTO) {
        try {
            hobbyService.addHobby(hobbyRequestDTO);
            ResponseDTO responseDTO = new ResponseDTO("success", "Hobby added successfully.");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., duplicate hobby name)
            ResponseDTO responseDTO = new ResponseDTO("error", "Failed to add hobby: " + e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Hobby> getAllHobbies() {
        return hobbyService.getAllHobbies();
    }

    //delete hobby
    @DeleteMapping("/delete/{hobbyId}")
    public ResponseEntity<ResponseDTO> deleteHobby(@PathVariable Long hobbyId) {
        try {
            hobbyService.deleteHobby(hobbyId);
            ResponseDTO responseDTO = new ResponseDTO("success", "Hobby deleted successfully.");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., duplicate hobby name)
            ResponseDTO responseDTO = new ResponseDTO("error", "Failed to delete hobby: " + e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

}
