package com.example.courses.Controller;

import com.example.courses.Entity.Instructor;
import com.example.courses.Service.InstuctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
    public class InstructorController {
        @Autowired
        InstuctorService instructorService;

        @PostMapping("/createInstructor")
        public Instructor createInstructor(@RequestBody Instructor requestObj) {
            Instructor instructor = instructorService.saveInstructor(requestObj);
            return instructor;

        }
        @GetMapping("/getInstructorById")
        public Instructor getInstructor(@RequestParam int id) throws Exception {
            return instructorService.getInstructorById(id);
    }
@GetMapping("/getAllInstructors")
        public List<Instructor> getAllInstructors() {
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


