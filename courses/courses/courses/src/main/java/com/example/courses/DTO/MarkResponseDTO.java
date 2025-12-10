package com.example.courses.DTO;

import com.example.courses.Entity.Mark;
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
    public static MarkResponseDTO convertToDto(Mark mark) {
        return MarkResponseDTO.builder()
                .id(mark.getId())
                .studentName(mark.getStudentName())
                .score(mark.getScore())
                .courseId(mark.getCourse() != null ? mark.getCourse().getId() : null)
                .build();
    }
}
