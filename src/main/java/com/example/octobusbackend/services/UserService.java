package com.example.octobusbackend.services;

import com.example.octobusbackend.dto.addUser.UserResponseDTO;
import com.example.octobusbackend.entity.ContactNumber;
import com.example.octobusbackend.entity.User;
import com.example.octobusbackend.repository.ContactNumberRepository;
import com.example.octobusbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ContactNumberRepository contactNumberRepository;
    private ContactNumber contactNumber;

    public UserService(UserRepository userRepository, ContactNumberRepository contactNumberRepository) {
        this.userRepository = userRepository;
        this.contactNumberRepository = contactNumberRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(UserResponseDTO userResponseDto) {
        User user = User.builder()
                .name(userResponseDto.getName())
                .email(userResponseDto.getEmail())
                .password(userResponseDto.getPassword())
                .contactNumbers(mapContactNumbers(userResponseDto.getContactNumbers()))
                .build();
        user.getContactNumbers().forEach(contactNumber -> contactNumber.setUser(user));

        return userRepository.save(user);
    }

    private List<ContactNumber> mapContactNumbers(List<UserResponseDTO.ContactNumberDTO> contactNumberDTOList) {
        // Assuming you want to save multiple contact numbers
        List<ContactNumber> contactNumbers = contactNumberDTOList.stream()
                .map(contactNumberDTO -> ContactNumber.builder()
                        .contactNo(contactNumberDTO.getContactNo())
                        .contactType(contactNumberDTO.getContactType())

                        .build())
                .collect(Collectors.toList());

        contactNumberRepository.saveAll(contactNumbers);

        return contactNumbers;
    }

    public User editUser(Long userId, UserResponseDTO userResponseDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userResponseDto.getName());
        user.setEmail(userResponseDto.getEmail());
        user.setPassword(userResponseDto.getPassword());
        user.setContactNumbers(mapContactNumbers(userResponseDto.getContactNumbers()));

        return userRepository.save(user);
    }

    public User loginUser(UserResponseDTO userResponseDto) {
        User user = (User) userRepository.findByEmail(userResponseDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPassword().equals(userResponseDto.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
