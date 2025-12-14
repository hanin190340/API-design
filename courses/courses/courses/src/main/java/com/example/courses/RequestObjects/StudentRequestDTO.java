package com.example.courses.RequestObjects;


import com.example.courses.Entity.Student;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
private Integer id;
    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Date of birth is required")
    private Date dateOfBirth;

    @NotEmpty(message = "Gender is required")
    private String gender;

    private List<Integer> phoneNumberIds;

    private Integer addressId;

    // Convert DTO → Entity
    public static Student convertToStudent(StudentRequestDTO request) {
        Student student = new Student();

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setId(request.getId());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        return student;
    }
    // Convert Entity → DTO
    public static StudentRequestDTO convertToDto(Student student) {
        return StudentRequestDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .id(student.getId())
                .addressId(student.getAddress() != null ? student.getAddress().getId() : null)
                .build();
    }
    // Validation
    public static void validCreateStudentRequest(StudentRequestDTO request) throws Exception {

        if (HelperUtils.isNull(request.getFirstName()) || request.getFirstName().isBlank()) {
            throw new Exception(Constants.BAD_REQUEST + ": First name is required");
        }

      else   if (HelperUtils.isNull(request.getLastName()) || request.getLastName().isBlank()) {
            throw new Exception(Constants.BAD_REQUEST + ": Last name is required");
        }

       else if (HelperUtils.isNull(request.getEmail()) || request.getEmail().isBlank()) {
            throw new Exception(Constants.BAD_REQUEST + ": Email is required");
        }

      else   if (HelperUtils.isNull(request.getDateOfBirth())) {
            throw new Exception(Constants.BAD_REQUEST + ": Date of birth is required");
        }

       else if (HelperUtils.isNull(request.getGender()) || request.getGender().isBlank()) {
            throw new Exception(Constants.BAD_REQUEST + ": Gender is required");
        }
       else if (HelperUtils.isNull(request.getId()) || request.getId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST + ": ID is required and must be > 0");
        }

    }

}