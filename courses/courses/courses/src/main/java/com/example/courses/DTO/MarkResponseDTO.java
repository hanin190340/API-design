package com.example.courses.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarkResponseDTO {
    private Integer id;
    private String studentName;
    private Double score;
    private Integer courseId; // link to the course
}
