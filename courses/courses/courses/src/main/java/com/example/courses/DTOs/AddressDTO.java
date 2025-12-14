package com.example.courses.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {
    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postalCode;

}
