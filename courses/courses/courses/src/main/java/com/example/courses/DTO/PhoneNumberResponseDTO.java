package com.example.courses.DTO;

import com.example.courses.Entity.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberResponseDTO {

    private Integer id;
    private String number;
    private String countryCode;
    private Boolean isLandLine;
    private Boolean isActive;
    private Date createdDate;
    private Date updatedDate;
    private Integer studentId;

    // Convert Entity → DTO
    public static PhoneNumberResponseDTO convertToDto(PhoneNumber phoneNumber) {
        return PhoneNumberResponseDTO.builder()
                .id(phoneNumber.getId())
                .number(phoneNumber.getNumber())
                .countryCode(phoneNumber.getCountryCode())
                .isLandLine(phoneNumber.getIsLandLine())
                .isActive(phoneNumber.getIsActive())
                .createdDate(phoneNumber.getCreatedDate())
                .updatedDate(phoneNumber.getUpdatedDate())
                .studentId(phoneNumber.getStudent() != null ? phoneNumber.getStudent().getId() : null)
                .build();
    }
}
