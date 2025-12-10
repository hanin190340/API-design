package com.example.courses.Service;
import com.example.courses.DTO.MarkCreateRequestDTO;
import com.example.courses.DTO.MarkResponseDTO;
import com.example.courses.Entity.Course;
import com.example.courses.Entity.Mark;
import com.example.courses.Helper.Constants;
import com.example.courses.Repository.CoursesRepository;
import com.example.courses.Repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarkService {
    @Autowired
    MarkRepository markRepository ;
    @Autowired
    private CoursesRepository coursesRepository;

    public List<MarkResponseDTO> getAllMark() {
        List<Mark> marks = markRepository.findAllActiveMarks();
        List <MarkResponseDTO> markResponseDTOS = new ArrayList<>();
        for (Mark mark : marks) {
            markResponseDTOS.add(MarkResponseDTO.convertToDto(mark));
        }
        return markResponseDTOS;
    }

    public MarkResponseDTO saveMark(MarkCreateRequestDTO request) throws Exception{
        Mark mark = MarkCreateRequestDTO.covertToMark(request);
        mark.setCreateDate(new Date());
        mark.setIsActive(Boolean.TRUE);
        Course courses = coursesRepository.getCoursesById(request.getCourseId());
        if (courses != null) {
            mark.setCourse(courses);
        } else {
            throw new Exception(Constants.BAD_COURSE);
        }
        Mark savedMark = markRepository.save(mark);
        return MarkResponseDTO.convertToDto(savedMark);
    }

    public Mark updateMark(Mark mark) throws Exception {
        Mark existingCourses = markRepository.findById(mark.getId()).get();

        if (existingCourses != null && existingCourses.getIsActive()) {
            mark.setUpdateDate(new Date());
            return markRepository.save(mark);
        } else {
            throw new Exception("Course not found");

        }
    }

    public void deleteMark(Integer id) throws Exception {
        Mark existingCourse = markRepository.findById(id).get();
        System.out.println(existingCourse.getIsActive());
        if (existingCourse != null && existingCourse.getIsActive()) {
            existingCourse.setUpdateDate(new Date());
            existingCourse.setIsActive(false);
            markRepository.save(existingCourse);
        } else {
            throw new Exception("Course not found");
        }
    }


    public Mark getMarkById(Integer id) throws Exception {
        Mark existingMark = markRepository.findById(id).get();
        if (existingMark != null && existingMark.getIsActive()) {
            return existingMark;
        } else {
            throw new Exception("Mark not found");
        }
    }

    // Entity → ResponseDTO
  /*  public MarkResponseDTO fromEntity(Mark entity) {
        if (entity == null) return null;

        return MarkResponseDTO.builder()
                .id(entity.getId())
                .studentName(entity.getStudentName())
                .score(entity.getScore())
                .build();
    }
/*/

}
