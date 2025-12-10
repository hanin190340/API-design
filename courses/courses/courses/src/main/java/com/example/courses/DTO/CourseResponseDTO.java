package com.example.courses.DTO;


import com.example.courses.Entity.Course;
import com.example.courses.Entity.Mark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private Integer id;
    private String name;
    private String language;
    private Boolean isActive;
    private Integer instructorId;
    private Integer departmentId;
    private List<String> marks;
    public static CourseResponseDTO convertToDto(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .language(course.getLanguage())
                .isActive(course.getIsActive())
                .instructorId(
                        course.getInstructor() != null
                                ? course.getInstructor().getId()
                                : null
                )
                .departmentId(
                        course.getInstructor() != null &&
                                course.getInstructor().getDepartment() != null
                                ? course.getInstructor().getDepartment().getId()
                                : null
                )
                .marks(
                        course.getMarks() != null
                                ? course.getMarks().stream()
                                .map(mark -> String.valueOf(mark.getScore()))
                                .toList()
                                : null
                )
                .build();
    }

}
