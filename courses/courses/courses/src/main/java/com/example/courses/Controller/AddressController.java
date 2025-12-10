package com.example.courses.Controller;
import com.example.courses.Entity.Address;
import com.example.courses.Entity.Student;
import com.example.courses.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class AddressController {
    @Autowired
    AddressService addressService;


    @PutMapping ("/studentsUpdate")
    public Address updateAddress(@RequestBody Address updateObjFromUser) throws Exception {
        return addressService.updateAddress(updateObjFromUser);
    }

}
