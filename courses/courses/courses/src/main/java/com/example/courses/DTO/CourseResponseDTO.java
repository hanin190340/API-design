package com.example.courses.DTO;


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
    private InstructorSummaryDTO instructor;
    private DepartmentSummaryDTO department;
    private List<MarkResponseDTO> marks;
}
