package com.example.courses.Service;

import java.util.List;

import com.example.courses.DTO.InstructorCreateRequestDTO;
import com.example.courses.Entity.Courses;
import com.example.courses.Entity.Department;
import com.example.courses.Entity.Instructor;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.CoursesRepository;
import com.example.courses.Repository.DepartmentRepository;
import com.example.courses.Repository.InstuctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class InstuctorService {
    @Autowired
    InstuctorRepository instuctorRepository;
@Autowired
    DepartmentRepository departmentRepository;
@Autowired
    CoursesRepository coursesRepository;
    public List<Instructor> getAllInstructors() {
        return instuctorRepository.findAll();
    }

    public Instructor saveInstructor(InstructorCreateRequestDTO request)throws Exception {
        Instructor instructor = InstructorCreateRequestDTO.covertToInstructor(request);
        instructor.setCreateDate(new Date());
        instructor.setIsActive(Boolean.TRUE);
        Department department = departmentRepository.getDepartmentById(request.getDepartmentId());
        if (HelperUtils.isNotNull(department)) {
            instructor.setDepartment(department);
        } else {
            throw new Exception(Constants.BAD_DEPARTMENT);}
        Courses courses = coursesRepository.getCoursesById(request.getCourseId());
        if (HelperUtils.isNotNull(courses)) {
            instructor.setCourses(courses);
        } else {
            throw new Exception(Constants.BAD_COURSE);
        }
        return instuctorRepository.save(instructor);
    }

    public List<Instructor> getAllActiveInstructors() {
        List<Instructor> allInstructors = instuctorRepository.findAll();
        List<Instructor> activeInstructors = new java.util.ArrayList<>();
        for (Instructor i : allInstructors) {
            if (Boolean.TRUE.equals(i.getIsActive())) {
                activeInstructors.add(i);
            }
        }

        return activeInstructors;
    }

    public Instructor updateInstructor(Instructor instructor) throws Exception {
        Instructor existingInstructor = instuctorRepository.findById(instructor.getId()).get();
        if (!Boolean.TRUE.equals(existingInstructor.getIsActive())) {
            throw new Exception("Instructor is not active");
        }

        if (existingInstructor.getIsActive()) {
            instructor.setUpdateDate(new Date());
            instructor.setCreateDate(existingInstructor.getCreateDate());
            instructor.setIsActive(existingInstructor.getIsActive());

            return instuctorRepository.save(instructor);
        } else {
            throw new Exception("Instructor not found");
        }
    }

    public void deleteInstructor(Integer id) throws Exception {
        Instructor existingInstructor = instuctorRepository.findById(id).get();
        System.out.println(existingInstructor.getIsActive());
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            existingInstructor.setUpdateDate(new Date());
            existingInstructor.setIsActive(false);
            instuctorRepository.save(existingInstructor);
        } else {
            throw new Exception("Instructor not found");
    }
}
public Instructor getInstructorById(Integer id) throws Exception {
        Instructor existingInstructor = instuctorRepository.findById(id).get();
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            return existingInstructor;
        } else {
            throw new Exception("Instructor not found");
        }
    }


  /*/  public static InstructorCreateRequestDTO fromEntity(Instructor entity) {
        if (entity == null) return null;

        return InstructorCreateRequestDTO.builder()
                .Name(entity.getName())
                .Subject(entity.getSubject())
                .build();
    }
    /*/
}
