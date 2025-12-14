package com.example.courses.RequestObjects;

import com.example.courses.Entity.Mark;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarkCreateRequestDTO {
    @NotEmpty(message = "Student name is required")
    private String studentName;

    @NotNull(message = "Score is required")
    private Integer score;

    @NotEmpty(message = "Grade is required")
    private String grade;

    @NotNull(message = "Course ID is required")
    private Integer courseId; // link to the course
    // Convert DTO → Entity
    public static Mark covertToMark(MarkCreateRequestDTO request) {
        Mark mark = new Mark();
        mark.setStudentName(request.getStudentName());
        mark.setScore(request.getScore());
        mark.setGrade(request.getGrade());
        return mark;
    }

    public static void validCreateMarkRequest(MarkCreateRequestDTO request) throws Exception {

        if (HelperUtils.isBlank(request.getStudentName()) || HelperUtils.isNull(request.getStudentName()) || request.getStudentName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        } else if (HelperUtils.isBlank(request.getGrade())) {
            throw new Exception(Constants.BAD_GRADE);
        } else if (HelperUtils.isNull(request.getScore()) || request.getScore() < 0 || request.getScore() > 100) {
            throw new Exception(Constants.BAD_SCORE);
        } else if (HelperUtils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);
        }
    }
}
