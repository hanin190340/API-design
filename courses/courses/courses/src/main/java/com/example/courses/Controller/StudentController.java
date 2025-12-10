package com.example.courses.Controller;

import com.example.courses.DTO.StudentCreateRequestDTO;
import com.example.courses.DTO.StudentResponseDTO;
import com.example.courses.Entity.Student;
import com.example.courses.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/createStudents")
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentCreateRequestDTO requestObj)throws Exception {
        StudentCreateRequestDTO.validCreateStudentRequest(requestObj);
        StudentResponseDTO student= studentService.saveStudent(requestObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
    @GetMapping("/getStudentsById")
    public Student getStudent(@RequestParam int id) throws Exception {
        return studentService.getStudentById(id);
    }
@GetMapping("/getAllStudents")
    public List<StudentResponseDTO> getAllStudents() {
        List<StudentResponseDTO> responseList = studentService.getAllStudents();
        return studentService.getAllStudents();
    }
@PutMapping("/updateStudents")
    public Student updateStudent(@RequestBody Student updateObjFromUser) throws Exception {
        return studentService.updateStudent(updateObjFromUser);
    }
@DeleteMapping("/deleteStudents")
    public String deleteStudent(@RequestParam int id) throws Exception {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
//DELETE
}
