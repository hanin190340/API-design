package com.example.courses.Service;

import java.util.List;

import com.example.courses.DTO.InstructorCreateRequestDTO;
import com.example.courses.DTO.InstructorSummaryDTO;
import com.example.courses.Entity.Course;
import com.example.courses.Entity.Department;
import com.example.courses.Entity.Instructor;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import com.example.courses.Repository.CoursesRepository;
import com.example.courses.Repository.DepartmentRepository;
import com.example.courses.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
@Autowired
    DepartmentRepository departmentRepository;
@Autowired
    CoursesRepository coursesRepository;
    public List<InstructorSummaryDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAllActiveInstructors();
        List <InstructorSummaryDTO> instructorDTOs = new java.util.ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorDTOs.add(InstructorSummaryDTO.convertToDto(instructor));
        }
        return instructorDTOs;
    }

    public InstructorSummaryDTO saveInstructor(InstructorCreateRequestDTO request)throws Exception {
        Instructor instructor = InstructorCreateRequestDTO.covertToInstructor(request);
        instructor.setCreateDate(new Date());
        instructor.setIsActive(Boolean.TRUE);
        Department department = departmentRepository.getDepartmentById(request.getDepartmentId());
        if (HelperUtils.isNotNull(department)) {
            instructor.setDepartment(department);
        } else {
            throw new Exception(Constants.BAD_DEPARTMENT);}
        Course courses = coursesRepository.getCoursesById(request.getCourseId());
      if (HelperUtils.isNotNull(courses)) {
          instructor.setCourse(courses);
      } else {
          throw new Exception(Constants.BAD_COURSE);
    }

        Instructor savedInstructor = instructorRepository.save(instructor);
        return InstructorSummaryDTO.convertToDto(savedInstructor);
    }

    public List<Instructor> getAllActiveInstructors() {
        List<Instructor> allInstructors = instructorRepository.findAll();
        List<Instructor> activeInstructors = new java.util.ArrayList<>();
        for (Instructor i : allInstructors) {
            if (Boolean.TRUE.equals(i.getIsActive())) {
                activeInstructors.add(i);
            }
        }

        return activeInstructors;
    }

    public Instructor updateInstructor(Instructor instructor) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(instructor.getId()).get();
        if (!Boolean.TRUE.equals(existingInstructor.getIsActive())) {
            throw new Exception("Instructor is not active");
        }

        if (existingInstructor.getIsActive()) {
            instructor.setUpdateDate(new Date());
            instructor.setCreateDate(existingInstructor.getCreateDate());
            instructor.setIsActive(existingInstructor.getIsActive());

            return instructorRepository.save(instructor);
        } else {
            throw new Exception("Instructor not found");
        }
    }

    public void deleteInstructor(Integer id) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(id).get();
        System.out.println(existingInstructor.getIsActive());
        if (existingInstructor != null && existingInstructor.getIsActive()) {
            existingInstructor.setUpdateDate(new Date());
            existingInstructor.setIsActive(false);
            instructorRepository.save(existingInstructor);
        } else {
            throw new Exception("Instructor not found");
    }
}
public Instructor getInstructorById(Integer id) throws Exception {
        Instructor existingInstructor = instructorRepository.findById(id).get();
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
