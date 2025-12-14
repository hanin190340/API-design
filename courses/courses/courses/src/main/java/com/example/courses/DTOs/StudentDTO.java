package com.example.courses.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String gender;
}
