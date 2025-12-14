package com.example.courses.Controller;
import com.example.courses.Entity.Address;
import com.example.courses.RequestObjects.AddressRequestDTO;
import com.example.courses.ResponseObjects.AddressResponseDTO;
import com.example.courses.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")

public class AddressController {
    @Autowired
    AddressService addressService;


    @PutMapping ("/studentsUpdate")
    public AddressResponseDTO updateAddress(@RequestBody AddressRequestDTO updateObjFromUser) throws Exception {
        return addressService.updateAddress(updateObjFromUser);
    }

}
