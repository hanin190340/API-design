package com.example.courses.Controller;

import com.example.courses.RequestObjects.InstructorCreateRequestDTO;
import com.example.courses.ResponseObjects.InstructorSummaryDTO;
import com.example.courses.Entity.Instructor;
import com.example.courses.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
    public class InstructorController {
        @Autowired
        InstructorService instructorService;

        @PostMapping("/createInstructor")
        public ResponseEntity< InstructorSummaryDTO> createInstructor(@RequestBody InstructorCreateRequestDTO requestObj)throws Exception {
            InstructorCreateRequestDTO.validateInstructor(requestObj);
            InstructorSummaryDTO instructor= instructorService.saveInstructor(requestObj);
            return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
        }
        @GetMapping("/getInstructorById")
        public InstructorSummaryDTO getInstructor(@RequestParam int id) throws Exception {
            return instructorService.getInstructorById(id);
    }
@GetMapping("/getAllInstructors")
        public List<InstructorSummaryDTO> getAllInstructors() {
            return instructorService.getAllInstructors();
        }
        @PutMapping("/updateInstructor")
        public Instructor updateInstructor(@RequestBody Instructor updateObjFromUser) throws Exception {
            return instructorService.updateInstructor(updateObjFromUser);
        }
        @DeleteMapping("/deleteInstructor")
        public String deleteInstructor(@RequestParam int id) throws Exception {
            instructorService.deleteInstructor(id);
            return "Instructor deleted successfully";
    }
}


