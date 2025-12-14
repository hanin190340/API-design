package com.example.courses.Controller;

import com.example.courses.RequestObjects.CourseCreateRequestDTO;
import com.example.courses.RequestObjects.CourseRequestDTO;
import com.example.courses.ResponseObjects.CourseResponseDTO;
import com.example.courses.Entity.Course;
import com.example.courses.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {
    @Autowired
    CourseService coursesService;
    @PostMapping("/create")
    public ResponseEntity<CourseResponseDTO> createCourses(@RequestBody CourseCreateRequestDTO requestObj)throws Exception {
        CourseCreateRequestDTO.validCreateCourseRequest(requestObj);
        CourseResponseDTO createdCourse = coursesService.saveCourse(requestObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping("/getAll")
    public List<CourseResponseDTO> getAllCourses() {
        List<CourseResponseDTO> responseList = coursesService.getAllCourses();
        System.out.println(responseList);
        return responseList;
    }

    @GetMapping("/getById")
    public CourseResponseDTO getCourses(@RequestParam int id) throws Exception {

        return coursesService.getCourseById(id);
    }

    @PutMapping("/Update")
    public CourseResponseDTO updateCourse(@RequestBody CourseRequestDTO updateObjFromUser) throws Exception {

        return coursesService.updateCourse(updateObjFromUser);
    }

    @DeleteMapping("/delete")
    public String deleteCourse(@RequestParam int id) throws Exception {
        coursesService.deleteCourse(id);
        return "Course deleted successfully";
    }


}



