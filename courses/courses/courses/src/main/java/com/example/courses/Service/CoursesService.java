package com.example.courses.Service;

import com.example.courses.DTO.CourseCreateRequestDTO;
import com.example.courses.Entity.Courses;
import com.example.courses.Entity.Department;
import com.example.courses.Entity.Instructor;
import com.example.courses.Entity.Mark;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.CoursesRepository;
import com.example.courses.Repository.DepartmentRepository;
import com.example.courses.Repository.InstuctorRepository;
import com.example.courses.Repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CoursesService {
    @Autowired
    CoursesRepository coursesRepository;
    @Autowired
    InstuctorRepository instuctorRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    MarkRepository markRepository;

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    public Courses saveCourse(CourseCreateRequestDTO request)throws Exception {
        Courses courses = CourseCreateRequestDTO.covertToCourses(request);
        courses.setCreateDate(new Date());
        courses.setIsActive(Boolean.TRUE);
        Instructor instructor = instuctorRepository.getInstructorById(request.getInstructorId());
        if (HelperUtils.isNotNull(instructor)) {
            courses.setInstructor(instructor);
        } else {
            throw new Exception(Constants.BAD_INSTRUCTOR);
        }
 /*/  Department department = departmentRepository.getDepartmentById(request.getDepartmentId());
       if (HelperUtils.isNotNull(department)) {
           courses.setDepartment(department);
       } else {
            throw new RuntimeException("Department not found with id: " + request.getDepartmentId());
       }
       /*/
        List<Mark> mark = markRepository.getMarkByIds(request.getMarks());
        if (HelperUtils.isListNotEmpty(mark)){
            courses.setMarks(mark);
        }
        else {
            throw new Exception(Constants.BAD_MARK);
        }
        return coursesRepository.save(courses);
    }

    public Courses updateCourse(Courses courses) throws Exception {
        Courses existingCourses = coursesRepository.findById(courses.getId()).get();

        if (existingCourses != null && existingCourses.getIsActive()) {
            courses.setUpdatedDate(new Date());
            return coursesRepository.save(courses);
        } else {
            throw new Exception("Course not found");

        }
    }

    public void deleteCourse(Integer id) throws Exception {
        Courses existingCourse = coursesRepository.findById(id).get();
        System.out.println(existingCourse.getIsActive());
        if (existingCourse != null && existingCourse.getIsActive()) {
            existingCourse.setUpdatedDate(new Date());
            existingCourse.setIsActive(false);
            coursesRepository.save(existingCourse);
        } else {
            throw new Exception("Course not found");
        }
    }


    public Courses getCourseById(Integer id) throws Exception {
        Courses existingCourses = coursesRepository.findById(id).get();
        if (existingCourses != null && existingCourses.getIsActive()) {
            return existingCourses;
        } else {
            throw new Exception("Course not found");
        }
    }

}



