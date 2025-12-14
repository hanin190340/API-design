package com.example.courses.RequestObjects;
import com.example.courses.Entity.Department;
import com.example.courses.Helper.Constants;
import com.example.courses.Helper.HelperUtils;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentCreateRequestDTO {

    @NotEmpty(message = "Department name is required")
    private String name;
    private List<Integer> instructorIds;

    public static Department covertToDepartment(DepartmentCreateRequestDTO request) {
        Department department = new Department();
        department.setName(request.getName());
        return department;
    }

    public static DepartmentCreateRequestDTO convertToDto(Department department) {
        DepartmentCreateRequestDTO dto = new DepartmentCreateRequestDTO();
        dto.setName(department.getName());
        return dto;
    }

    public static void validateDepartment(DepartmentCreateRequestDTO request) throws Exception {

        if (HelperUtils.isBlank(request.getName()) || HelperUtils.isNull(request.getName()) || request.getName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        } else if (HelperUtils.isListEmpty(request.getInstructorIds()) || HelperUtils.isListNull(request.getInstructorIds())) {
            throw new Exception(Constants.BAD_REQUEST + ": instructorIds list cannot be null or empty");
        }
    }

}

