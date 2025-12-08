package com.example.courses.Controller;

import com.example.courses.DTO.CourseCreateRequestDTO;
import com.example.courses.DTO.CourseResponseDTO;
import com.example.courses.Entity.Courses;
import com.example.courses.Service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {
    @Autowired
    CoursesService coursesService;
//public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseCreateRequested requestObj) throws Exception{
//    CourseCreateRequested.validateCourseCreateRequested(requestObj);
//    CourseResponseDTO createdCourse = courseService.saveCourse(requestObj);
//    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
//}
    @PostMapping("/create")
    public ResponseEntity<CourseCreateRequestDTO> createCourses(@RequestBody CourseC requestObj) {
        Courses courses = coursesService.saveCourse(requestObj);
        CourseResponseDTO
        return courses;

    }

    @GetMapping("/getAll")
    public List<Courses> getAllCourses() {
        List<Courses> responseList = coursesService.getAllCourses();
        System.out.println(responseList);
        return responseList;
    }

    @GetMapping("/getById")
    public Courses getCourses(@RequestParam int id) throws Exception {

        return coursesService.getCourseById(id);
    }

    @PutMapping("/Update")
    public Courses updateCourse(@RequestBody Courses updateObjFromUser) throws Exception {

        return coursesService.updateCourse(updateObjFromUser);
    }

    @DeleteMapping("/delete")
    public String deleteCourse(@RequestParam int id) throws Exception {
        coursesService.deleteCourse(id);
        return "Course deleted successfully";
    }


}



