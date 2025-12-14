package com.example.courses.RequestObjects;
import com.example.courses.Entity.Course;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRequestDTO {
    private Integer id;
    @NotEmpty(message = "Course name is required")
    private String name;
    private String language;
    private Integer instructorId;
    private Integer departmentId;
    private List<String> marks;

    public static Course covertToCourses(CourseRequestDTO request) {
        Course courses = new Course();
        courses.setName(request.getName());
        courses.setId(request.getId());
        courses.setLanguage(request.getLanguage());
        return courses;
    }

    public static CourseRequestDTO convertToDto(Course course) {
        return CourseRequestDTO.builder()
                .name(course.getName())
                .language(course.getLanguage())
                .id(course.getId())
                // instructorId, departmentId, marks are NOT in Course entity
                .build();
    }


    public static void validCreateCourseRequest(CourseRequestDTO request) throws Exception {
        if (HelperUtils.isNull(request.getName()) || request.getName().isBlank() || request.getName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        } else if (HelperUtils.isNull(request.getLanguage()) || request.getLanguage().isBlank() || request.getLanguage().isBlank()) {
            throw new Exception(Constants.BAD_LANGUAGE_VALID);
        } else if (HelperUtils.isNull(request.getDepartmentId()) || request.getDepartmentId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);

        } else if (HelperUtils.isNull(request.getInstructorId()) || request.getInstructorId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST);
        } else if (HelperUtils.isListEmpty(request.getMarks()) || HelperUtils.isListNull(request.getMarks())) {
            throw new Exception(Constants.BAD_REQUEST);
        } else if ((HelperUtils.isNull(request.getId()) || request.getId() <= 0)) {
            throw new Exception(Constants.BAD_REQUEST);
        }
    }
}