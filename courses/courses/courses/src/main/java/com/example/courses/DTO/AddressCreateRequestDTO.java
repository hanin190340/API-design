package com.example.courses.DTO;

import com.example.courses.Entity.Address;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressCreateRequestDTO {

    @NotEmpty(message = "House number is required")
    private String houseNumber;

    @NotEmpty(message = "Street is required")
    private String street;

    @NotEmpty(message = "City is required")
    private String city;

    private String stateOrProvince;
    @NotEmpty(message = "Country is required")
    private String country;
    private String postalCode;
    private Integer studentId; // links this address to a Student


    // Convert DTO → Entity
    public static Address convertToAddress(AddressCreateRequestDTO request) {
        Address address = new Address();
        address.setHouseNumber(request.getHouseNumber());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setStateOrProvince(request.getStateOrProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());
        return address;
    }

    public static void validCreateAddressRequest(AddressCreateRequestDTO request) throws Exception {
        if (HelperUtils.isNull(request.getHouseNumber()) || request.getHouseNumber().isBlank() || request.getHouseNumber().isEmpty()) {
            throw new Exception(Constants.BAD_REQUEST + ": House number is required");
        } else if (HelperUtils.isNull(request.getStreet()) || request.getStreet().isBlank() || request.getStreet().isEmpty()) {
            throw new Exception(Constants.BAD_REQUEST + ": Street is required");
        } else if (HelperUtils.isNull(request.getCity()) || request.getCity().isBlank() || request.getCity().isEmpty()) {
            throw new Exception(Constants.BAD_REQUEST + ": City is required");
        } else if (HelperUtils.isNull(request.getCountry()) || request.getCountry().isBlank() || request.getCountry().isEmpty()) {
            throw new Exception(Constants.BAD_REQUEST + ": Country is required");
        } else if (HelperUtils.isNull(request.getPostalCode()) || request.getPostalCode().isBlank() || request.getPostalCode().isEmpty()) {
            throw new Exception(Constants.BAD_REQUEST + ": Postal code is required");
        } else if (HelperUtils.isNull(request.getStudentId()) || request.getStudentId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST + ": studentId is required and must be > 0");
        }
    }
}

