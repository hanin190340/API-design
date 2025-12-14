package com.example.courses.Service;

import com.example.courses.RequestObjects.AddressCreateRequestDTO;
import com.example.courses.RequestObjects.AddressRequestDTO;
import com.example.courses.ResponseObjects.AddressResponseDTO;
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
    public AddressResponseDTO updateAddress(AddressRequestDTO address)throws Exception {
        Address existingAddress = addressRepository.getAddressById(address.getId());

        if (existingAddress != null && existingAddress.getIsActive()) {
            existingAddress =AddressRequestDTO.convertToAddress(address);
            existingAddress.setUpdatedDate(new Date());
            return AddressResponseDTO.convertToDto(addressRepository.save(existingAddress));
        } else {
            throw new Exception("Address not found");

        }
    }
}
