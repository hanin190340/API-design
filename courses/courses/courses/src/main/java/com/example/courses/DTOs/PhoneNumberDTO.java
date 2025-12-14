package com.example.courses.DTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public class PhoneNumberDTO {
        private String number;
        private String countryCode;
        private Boolean isLandLine;
    }

