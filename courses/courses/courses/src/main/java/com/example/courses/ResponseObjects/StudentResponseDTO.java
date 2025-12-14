package com.example.courses.ResponseObjects;

import com.example.courses.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String gender;;
    private List<Integer> phoneNumberIds;
    private Integer addressId;
    // Convert Entity → DTO
    public static StudentResponseDTO convertToDto(Student student) {

        return StudentResponseDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())

                .addressId(student.getAddress() != null ? student.getAddress().getId() : null)

                .phoneNumberIds(
                        student.getPhoneNumbers() != null ?
                                student.getPhoneNumbers().stream()
                                        .map(phone -> phone.getId())
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }
}
