package com.example.courses.Service;
import java.util.Date;
import java.util.List;

import com.example.courses.RequestObjects.DepartmentCreateRequestDTO;
import com.example.courses.RequestObjects.DepartmentRequestDTO;
import com.example.courses.ResponseObjects.DepartmentSummaryDTO;
import com.example.courses.Entity.Department;
import com.example.courses.Repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentSummaryDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAllActiveDepartments();
        List<DepartmentSummaryDTO> departmentDTOs = new java.util.ArrayList<>();
        for (Department dept : departments) {
            departmentDTOs.add(DepartmentSummaryDTO.convertToDTO(dept));
        }
        return departmentDTOs;
    }

    public DepartmentSummaryDTO saveDepartment(DepartmentCreateRequestDTO request)  {
        Department department = DepartmentCreateRequestDTO.covertToDepartment(request);
        department.setIsActive(Boolean.TRUE);
        department.setCreateDate(new Date());
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentSummaryDTO.convertToDTO(savedDepartment);
    }

    public List<Department> getAllActiveDepartments() {
        List<Department> allDepartments = departmentRepository.findAll();
        List<Department> activeDepartments = new java.util.ArrayList<>();
        for (Department d : allDepartments) {
            if (Boolean.TRUE.equals(d.getIsActive())) {
                activeDepartments.add(d);
            }
        }

        return activeDepartments;
    }

    public DepartmentSummaryDTO updateDepartment(DepartmentRequestDTO request) throws Exception {

        Department existingDepartment = departmentRepository.findById(request.getId())
                .orElseThrow(() -> new Exception("Department not found"));

        if (!Boolean.TRUE.equals(existingDepartment.getIsActive())) {
            throw new Exception("Department is not active");
        }
        existingDepartment.setName(request.getName());
        existingDepartment.setUpdatedDate(new Date());

        Department savedDepartment = departmentRepository.save(existingDepartment);

        return DepartmentSummaryDTO.convertToDTO(savedDepartment);
    }


    public void deleteDepartment(Integer id) throws Exception {
        Department existingDepartment = departmentRepository.findById(id).get();
        System.out.println(existingDepartment.getIsActive());
        if (existingDepartment != null && existingDepartment.getIsActive()) {
            existingDepartment.setUpdatedDate(new Date());
            existingDepartment.setIsActive(false);
            departmentRepository.save(existingDepartment);
        } else {
            throw new Exception("Department not found");
        }

    }

    public DepartmentSummaryDTO getDepartmentById(Integer id) throws Exception {
        Department existingDepartment = departmentRepository.getDepartmentById(id);
        if (existingDepartment == null || !existingDepartment.getIsActive()) {
            throw new Exception("Department not found");
        }
        return DepartmentSummaryDTO.convertToDTO(existingDepartment);

    }


}




