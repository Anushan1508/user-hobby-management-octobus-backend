package com.example.octobusbackend.dto.getUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private Long id;
    private String name;
    private String email;
    public ArrayList<Contact> contacts;
}
