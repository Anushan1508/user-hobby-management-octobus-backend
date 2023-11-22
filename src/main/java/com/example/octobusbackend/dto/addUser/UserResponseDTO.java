package com.example.octobusbackend.dto.addUser;

import com.example.octobusbackend.dto.getUser.Contact;
import com.example.octobusbackend.entity.Hobby;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String name;
    private String email;
    @Nullable
    private String password;
    private List<ContactNumberDTO> contactNumbers;

    @Data
    public static class ContactNumberDTO {
        private String contactNo;
        private String contactType;
    }
}