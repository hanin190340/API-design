package com.example.courses.RequestObjects;

import com.example.courses.Entity.PhoneNumber;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberRequestDTO {
    private Integer id;
    @NotEmpty(message = "Phone number is required")
    private String number;

    @NotEmpty(message = "Country code is required")
    private String countryCode;

    private Boolean isLandLine;
    private Integer studentId; // Link to Student

    // Convert DTO → Entity
    public static PhoneNumber convertToPhoneNumber(PhoneNumberRequestDTO request) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(request.getNumber());
        phoneNumber.setId(request.getId());
        phoneNumber.setCountryCode(request.getCountryCode());
        phoneNumber.setIsLandLine(request.getIsLandLine());

        return phoneNumber;
    }

    // Convert Entity → DTO
    public static PhoneNumberRequestDTO convertToDto(PhoneNumber phoneNumber) {
        return PhoneNumberRequestDTO.builder()
                .number(phoneNumber.getNumber())
                .countryCode(phoneNumber.getCountryCode())
                .isLandLine(phoneNumber.getIsLandLine())
                .id(phoneNumber.getId())
                .studentId(phoneNumber.getStudent() != null ? phoneNumber.getStudent().getId() : null)
                .build();
    }

    // Validation
    public static void validCreatePhoneNumberRequest(PhoneNumberRequestDTO request) throws Exception {
        if (HelperUtils.isNull(request.getNumber()) || request.getNumber().isBlank()) {
            throw new Exception(Constants.BAD_REQUEST + ": Phone number is required");
        } else if (HelperUtils.isNull(request.getCountryCode()) || request.getCountryCode().isBlank()) {
            throw new Exception(Constants.BAD_REQUEST + ": Country code is required");
        } else if (HelperUtils.isNull(request.getStudentId()) || request.getStudentId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST + ": studentId is required and must be > 0");
        } else if (HelperUtils.isNull(request.getId()) || request.getId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST + ": id is required and must be > 0");

        }
    }
}
