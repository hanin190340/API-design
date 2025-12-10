package com.example.courses.Service;

import com.example.courses.DTO.PhoneNumberCreateRequestDTO;
import com.example.courses.DTO.PhoneNumberResponseDTO;
import com.example.courses.Entity.PhoneNumber;
import com.example.courses.Entity.Student;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.PhoneNumberRepository;
import com.example.courses.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PhoneNumberService {
@Autowired
    PhoneNumberRepository phoneNumberRepository;
@Autowired
    StudentRepository studentRepository;

    public PhoneNumberResponseDTO savePhoneNumber(PhoneNumberCreateRequestDTO request) throws Exception {
        // Convert DTO → Entity
        PhoneNumber phoneNumber = PhoneNumberCreateRequestDTO.convertToPhoneNumber(request);
        phoneNumber.setCreatedDate(new Date());
        phoneNumber.setIsActive(Boolean.TRUE);
        //  validations student
        Student student = studentRepository.getStudentById(request.getStudentId());
        if (HelperUtils.isNotNull(student)) {
          phoneNumber.setStudent(student);
        } else {
            throw new Exception("Invalid student ID");
        }
        PhoneNumber savedPhoneNumber = phoneNumberRepository.save(phoneNumber);
        return PhoneNumberResponseDTO.convertToDto(savedPhoneNumber);

    }

    public PhoneNumber updatePhoneNumber(PhoneNumber request) throws Exception {
        PhoneNumber PhoneNumber = phoneNumberRepository.findById(request.getId()).get();
        if (PhoneNumber != null && PhoneNumber.getIsActive()) {
            request.setUpdatedDate(new Date());
            return phoneNumberRepository.save(request);
        } else {
            throw new Exception("Phone Number not found");
        }
    }
    public void deletePhoneNumber(Integer id) throws Exception {
        PhoneNumber existingPhoneNumber = phoneNumberRepository.findById(id).get();
        if (existingPhoneNumber != null && existingPhoneNumber.getIsActive()) {
            existingPhoneNumber.setUpdatedDate(new Date());
            existingPhoneNumber.setIsActive(false);
            phoneNumberRepository.save(existingPhoneNumber);
        } else {
            throw new Exception("Phone Number not found");
        }
    }
}
