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
public class DepartmentRequestDTO {
    @NotEmpty(message = "Department name is required")
    private String name;
    private Integer id;
    private List<Integer> instructorIds;

    public static Department covertToDepartment(DepartmentRequestDTO request) {
        Department department = new Department();
        department.setName(request.getName());
        department.setId(request.getId());
        return department;
    }

    public static DepartmentRequestDTO convertToDto(Department department) {
        DepartmentRequestDTO dto = new DepartmentRequestDTO();
        dto.setName(department.getName());
        dto.setId(department.getId());
        return dto;
    }

    public static void validateDepartment(DepartmentRequestDTO request) throws Exception {

        if (HelperUtils.isBlank(request.getName()) || HelperUtils.isNull(request.getName()) || request.getName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        } else if (HelperUtils.isListEmpty(request.getInstructorIds()) || HelperUtils.isListNull(request.getInstructorIds())) {
            throw new Exception(Constants.BAD_REQUEST + ": instructorIds list cannot be null or empty");
        }
        else if (HelperUtils.isNull(request.getId()) || request.getId() <= 0) {
            throw new Exception(Constants.BAD_REQUEST + ": invalid department ID");
        }
    }

}

