package com.example.courses.Controller;

import com.example.courses.RequestObjects.PhoneNumberCreateRequestDTO;
import com.example.courses.RequestObjects.PhoneNumberRequestDTO;
import com.example.courses.ResponseObjects.PhoneNumberResponseDTO;
import com.example.courses.Entity.PhoneNumber;
import com.example.courses.Service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")

public class PhoneNumberController {
    @Autowired
    PhoneNumberService phoneNumberService;

    @PutMapping("/UpdatePhoneNumber")
    public PhoneNumberResponseDTO updatePhoneNumber(@RequestBody PhoneNumberRequestDTO updateObjFromUser ) throws Exception {

        return phoneNumberService.updatePhoneNumber(updateObjFromUser);
    }
    @PostMapping("/AddPhoneNumber")
    public ResponseEntity<PhoneNumberResponseDTO> addPhoneNumber(@RequestBody PhoneNumberCreateRequestDTO requestObj) throws Exception {
       PhoneNumberCreateRequestDTO.validCreatePhoneNumberRequest(requestObj);
       PhoneNumberResponseDTO phoneNumber = phoneNumberService.savePhoneNumber(requestObj);
       return ResponseEntity.status(HttpStatus.CREATED).body(phoneNumber);
    }

    @DeleteMapping("deletePhoneNumbers")
    public String deletePhoneNumber(@RequestParam int id) throws Exception {
        phoneNumberService.deletePhoneNumber(id);
        return "Phone Number deleted successfully";
    }


}
