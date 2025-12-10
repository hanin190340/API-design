package com.example.courses.Service;

import com.example.courses.DTO.CourseCreateRequestDTO;
import com.example.courses.DTO.CourseResponseDTO;
import com.example.courses.Entity.Course;
import com.example.courses.Entity.Instructor;
import com.example.courses.Entity.Mark;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.CoursesRepository;
import com.example.courses.Repository.DepartmentRepository;
import com.example.courses.Repository.InstructorRepository;
import com.example.courses.Repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CoursesRepository coursesRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    MarkRepository markRepository;

    public List<CourseResponseDTO> getAllCourses() {
        List <Course> courses = coursesRepository.findAllActiveCourses();
        List<CourseResponseDTO> courseResponseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseResponseDTOS.add(CourseResponseDTO.convertToDto(course));
        }
        return courseResponseDTOS;

    }

    public CourseResponseDTO saveCourse(CourseCreateRequestDTO request) throws Exception {
        // Convert DTO → Entity
        Course course = CourseCreateRequestDTO.covertToCourses(request);
        course.setCreateDate(new Date());
        course.setIsActive(Boolean.TRUE);

        // Validate Instructor
        Instructor instructor = instructorRepository.getInstructorById(request.getInstructorId());
        if (HelperUtils.isNotNull(instructor)) {
            course.setInstructor(instructor);
        } else {
            throw new Exception(Constants.BAD_INSTRUCTOR);
        }

        // Validate Marks
        List<Mark> mark = markRepository.getMarkByIds(request.getMarks());
        if (HelperUtils.isListNotEmpty(mark)) {
            course.setMarks(mark);
        } else {
            throw new Exception(Constants.BAD_MARK);
        }
        Course savedCourse = coursesRepository.save(course);

        // Convert Course to CourseResponseDTO
        return CourseResponseDTO.convertToDto(savedCourse); }



    public Course updateCourse(Course courses) throws Exception {
        Course course = coursesRepository.findById(courses.getId()).get();

        if (course != null && course.getIsActive()) {
            courses.setUpdatedDate(new Date());
            return coursesRepository.save(courses);
        } else {
            throw new Exception("Course not found");

        }
    }

    public void deleteCourse(Integer id) throws Exception {
        Course existingCourse = coursesRepository.findById(id).get();
        if (existingCourse != null && existingCourse.getIsActive()) {
            existingCourse.setUpdatedDate(new Date());
            existingCourse.setIsActive(false);
            coursesRepository.save(existingCourse);
        } else {
            throw new Exception("Course not found");
        }
    }


    public Course getCourseById(Integer id) throws Exception {
        Course existingCourses = coursesRepository.findById(id).get();
        if (existingCourses != null && existingCourses.getIsActive()) {
            return existingCourses;
        } else {
            throw new Exception("Course not found");
        }
    }

}



