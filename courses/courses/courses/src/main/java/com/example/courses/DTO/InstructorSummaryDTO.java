package com.example.courses.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructorSummaryDTO {
    private Integer id;
    private String Name;
    private String subject;
    private Integer departmentId;
    private Integer courseId;
}
