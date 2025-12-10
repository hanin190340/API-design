package com.example.courses.DTO;

import com.example.courses.Entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private Integer id;
    private String houseNumber;
    private String street;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;
    private Integer studentId;

    // Convert Entity → DTO
    public static AddressResponseDTO convertToDto(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .stateOrProvince(address.getStateOrProvince())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .studentId(address.getStudent() != null ? address.getStudent().getId() : null)
                .build();
    }
}
