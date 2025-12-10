package com.example.courses.DTO;

import com.example.courses.Entity.Instructor;
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

    // Convert Entity → DTO
    public static InstructorSummaryDTO convertToDto(Instructor instructor) {
        return InstructorSummaryDTO.builder()
                .id(instructor.getId())
                .Name(instructor.getName())
                .subject(instructor.getSubject())
                .departmentId(
                        instructor.getDepartment() != null
                                ? instructor.getDepartment().getId()
                                : null
                )

                .build();
    }



}
