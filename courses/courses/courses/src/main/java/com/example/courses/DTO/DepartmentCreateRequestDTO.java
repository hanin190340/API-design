package com.example.courses.DTO;

import com.example.courses.Entity.Courses;
import com.example.courses.Entity.Department;
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
public class DepartmentCreateRequestDTO {

    @NotEmpty(message = "Department name is required")
    private String name;

    public static Department covertToDepartment(DepartmentCreateRequestDTO request) {
        Department department = new Department();
        department.setName(request.getName());
        return department;
    }

    public static void validateDepartment(DepartmentCreateRequestDTO request) throws Exception {

        if (HelperUtils.isBlank(request.getName()) || HelperUtils.isNull(request.getName()) || request.getName().isEmpty()) {
            throw new Exception(Constants.BAD_NAME_VALID);
        }
    }

}

