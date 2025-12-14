package com.example.courses.Service;

import com.example.courses.RequestObjects.CourseCreateRequestDTO;
import com.example.courses.RequestObjects.CourseRequestDTO;
import com.example.courses.ResponseObjects.CourseResponseDTO;
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

//    public AddressResponseDTO updateAddress(AddressRequestDTO address)throws Exception {
//        Address existingAddress = addressRepository.getAddressById(address.getId());
//
//        if (existingAddress != null && existingAddress.getIsActive()) {
//            existingAddress =AddressRequestDTO.convertToAddress(address);
//            existingAddress.setUpdatedDate(new Date());
//            return AddressResponseDTO.convertToDto(addressRepository.save(existingAddress));
//        } else {
//            throw new Exception("Address not found");
//
//        }
//    }


    public CourseResponseDTO updateCourse(CourseRequestDTO request) throws Exception {

        Course existingCourse = coursesRepository.findById(request.getId())
                .orElseThrow(() -> new Exception("Course not found"));

        if (!existingCourse.getIsActive()) {
            throw new Exception("Course is not active");
        }
        existingCourse.setName(request.getName());
        existingCourse.setLanguage(request.getLanguage());

        // Instructor
        Instructor instructor =
                instructorRepository.getInstructorById(request.getInstructorId());
        if (HelperUtils.isNull(instructor)) {
            throw new Exception(Constants.BAD_INSTRUCTOR);
        }
        existingCourse.setInstructor(instructor);

        // Mark
        /*/Mark mark =
                markRepository.getMarkById(request.getMarkId());
        if (HelperUtils.isNull(mark)) {
            throw new Exception(Constants.BAD_MARK);
        }

        List<Mark> marks = new ArrayList<>();
        marks.add(mark);
        existingCourse.setMarks(marks);
        /*/

        // Updated date
        existingCourse.setUpdatedDate(new Date());

        return CourseResponseDTO.convertToDto(
                coursesRepository.save(existingCourse)
        );
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


    public CourseResponseDTO getCourseById(Integer id) throws Exception {
        Course existingCourses = coursesRepository.findById(id).get();
        if (existingCourses != null && existingCourses.getIsActive()) {
            return CourseResponseDTO.convertToDto(existingCourses);
        } else {
            throw new Exception("Course not found");
        }
    }

}



