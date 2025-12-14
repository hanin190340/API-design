package com.example.courses.ResponseObjects;

import com.example.courses.Entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentSummaryDTO {
    private int id;
    private String name;
    private List<Integer> instructorIds;

    public static DepartmentSummaryDTO convertToDTO(Department department) {
        return DepartmentSummaryDTO.builder()
                .id(department.getId())
                .name(department.getName())

                .build();
    }

}

