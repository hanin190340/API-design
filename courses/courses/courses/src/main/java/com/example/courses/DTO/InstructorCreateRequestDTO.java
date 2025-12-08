package com.example.courses.DTO;

import com.example.courses.Entity.Department;
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
public class InstructorCreateRequestDTO {
    @NotEmpty(message = "Instructor name is required")
    String Name;
    @NotEmpty(message = "Subject is required")
    String Subject;
    private Integer departmentId;
    private Integer courseId;

    public static Instructor covertToInstructor(InstructorCreateRequestDTO request) {
        Instructor instructor = new Instructor();
        instructor.setName(request.getName());
        instructor.setSubject(request.getSubject());
        return instructor;
    }

    /*  public static InstructorSummaryDTO convertToInstructorSummaryDTO(Instructor instructor) {
          return InstructorSummaryDTO.builder()
                  .id(instructor.getId())
                  .Name(instructor.getName())
                  .subject(instructor.getSubject())
                  .build();
      }
  //*/
    public static void validateInstructor(InstructorCreateRequestDTO request) throws Exception {

        if (HelperUtils.isBlank(request.getName()) || HelperUtils.isNull(request.getName()) || request.getName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        } else if (HelperUtils.isBlank(request.getSubject()) || HelperUtils.isNull(request.getSubject()) || request.getSubject().isEmpty()) {
            throw new Exception(Constants.BAD_SUBJECT);
        } else if (HelperUtils.isNull(request.getDepartmentId()) || request.getDepartmentId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);

        } else if (HelperUtils.isNull(request.getCourseId()) || request.getCourseId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);

        }
    }

}
