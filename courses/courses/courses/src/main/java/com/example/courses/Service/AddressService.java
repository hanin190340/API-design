package com.example.courses.Service;

import com.example.courses.DTO.AddressCreateRequestDTO;
import com.example.courses.DTO.AddressResponseDTO;
import com.example.courses.Entity.Address;
import com.example.courses.Entity.Student;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.AddressRepository;
import com.example.courses.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    StudentRepository studentRepository;
    public AddressResponseDTO saveAddress(AddressCreateRequestDTO request) throws Exception {
        // Convert DTO → Entity
        Address address = AddressCreateRequestDTO.convertToAddress(request);
        address.setCreatedDate(new Date());
        address.setIsActive(Boolean.TRUE);
        //  validations student
        Student student = studentRepository.getStudentById(request.getStudentId());
        if (HelperUtils.isNotNull(student)) {
            address.setStudent(student);
        } else {
            throw new Exception("Invalid student ID");
        }
        Address savedAddress = addressRepository.save(address);
        return AddressResponseDTO.convertToDto(savedAddress);
    }
    public Address updateAddress(Address address)throws Exception {
        Address existingAddress = addressRepository.findById(address.getId()).get();

        if (existingAddress != null && existingAddress.getIsActive()) {
            address.setUpdatedDate(new Date());
            return addressRepository.save(address);
        } else {
            throw new Exception("Address not found");

        }
    }
}
