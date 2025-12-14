package com.example.courses.RequestObjects;

import com.example.courses.Entity.Instructor;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstructorRequestDTO {
    @NotEmpty(message = "Instructor name is required")
    String Name;
    @NotEmpty(message = "Subject is required")
    String Subject;
    private Integer id;
    private Integer departmentId;
    private Integer courseId;

    public static Instructor covertToInstructor(InstructorRequestDTO request) {
        Instructor instructor = new Instructor();
        instructor.setName(request.getName());
        instructor.setId(request.getId());
        instructor.setSubject(request.getSubject());
        return instructor;
    }

    public static void validateInstructor(InstructorRequestDTO request) throws Exception {

        if (HelperUtils.isBlank(request.getName()) || HelperUtils.isNull(request.getName()) || request.getName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        } else if (HelperUtils.isBlank(request.getSubject()) || HelperUtils.isNull(request.getSubject()) || request.getSubject().isEmpty()) {
            throw new Exception(Constants.BAD_SUBJECT);
        } else if (HelperUtils.isNull(request.getDepartmentId()) || request.getDepartmentId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);

        } else if (HelperUtils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);

        }
        else if (HelperUtils.isNull(request.getId())|| request.getId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);

        }
    }

}
