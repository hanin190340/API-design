package com.example.courses.Service;

import com.example.courses.RequestObjects.StudentCreateRequestDTO;
import com.example.courses.RequestObjects.StudentRequestDTO;
import com.example.courses.ResponseObjects.StudentResponseDTO;
import com.example.courses.Entity.Address;
import com.example.courses.Entity.PhoneNumber;
import com.example.courses.Entity.Student;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.AddressRepository;
import com.example.courses.Repository.PhoneNumberRepository;
import com.example.courses.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service

public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PhoneNumberRepository phoneNumberRepository;
    @Autowired
    AddressRepository addressRepository;

    public StudentResponseDTO saveStudent(StudentCreateRequestDTO request) throws Exception {
        Student student = StudentCreateRequestDTO.convertToStudent(request);
        student.setIsActive(Boolean.TRUE);
        //  validations student
        Address address = addressRepository.getAddressById(request.getAddressId());
        if (HelperUtils.isNotNull(address)) {
            student.setAddress(address);
        } else {
            throw new Exception("Invalid Address ID");
        }
        //validations phone number
        List<PhoneNumber> phoneNumber = phoneNumberRepository.findAllById(request.getPhoneNumberIds());
        if (HelperUtils.isNotNull(phoneNumber)) {
            student.setPhoneNumbers(phoneNumber);
        } else {
            throw new Exception("Invalid Phone Number ID");
        }

        Student savedStudent = studentRepository.save(student);
        return StudentResponseDTO.convertToDto(savedStudent);

    }

    public StudentResponseDTO getStudentById(Integer id) throws Exception{
        Student existingStudent = studentRepository.findById(id).get();
        if (existingStudent != null && existingStudent.getIsActive()) {
            return StudentResponseDTO.convertToDto(existingStudent);
        } else {
            throw new Exception("Student not found");
        }
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAllActiveStudents();
        List <StudentResponseDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(StudentResponseDTO.convertToDto(student));
        }
        return studentDTOs;
    }
    public StudentResponseDTO updateStudent(StudentRequestDTO request) throws Exception {

        Student existingStudent = studentRepository.findById(request.getId())
                .orElseThrow(() -> new Exception("Student not found"));

        if (!Boolean.TRUE.equals(existingStudent.getIsActive())) {
            throw new Exception("Student is not active");
        }

        existingStudent.setFirstName(request.getFirstName());
        existingStudent.setLastName(request.getLastName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setDateOfBirth(request.getDateOfBirth());
        existingStudent.setGender(request.getGender());
        existingStudent.setUpdatedDate(new Date());

        if (request.getAddressId() != null) {
            Address address = addressRepository.findById(request.getAddressId())
                    .orElseThrow(() -> new Exception("Address not found"));
            existingStudent.setAddress(address);
        }
        if (request.getPhoneNumberIds() != null && !request.getPhoneNumberIds().isEmpty()) {
            List<PhoneNumber> phoneNumbers =
                    phoneNumberRepository.findAllById(request.getPhoneNumberIds());
            existingStudent.setPhoneNumbers(phoneNumbers);
        }

        Student savedStudent = studentRepository.save(existingStudent);

        return StudentResponseDTO.convertToDto(savedStudent);
    }


    public void deleteStudent(int id) throws Exception{
        Student existingStudent = studentRepository.findById(id).get();
        if (existingStudent != null && existingStudent.getIsActive()) {
            existingStudent.setUpdatedDate(new java.util.Date());
            existingStudent.setIsActive(false);
            studentRepository.save(existingStudent);
        } else {
            throw new Exception("Student not found");
        }
    }
}
