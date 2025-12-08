package com.example.courses.Service;
import java.util.Date;
import java.util.List;

import com.example.courses.DTO.DepartmentCreateRequestDTO;
import com.example.courses.Entity.Department;
import com.example.courses.Repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(DepartmentCreateRequestDTO request)  {
        Department department = DepartmentCreateRequestDTO.covertToDepartment(request);
        department.setIsActive(Boolean.TRUE);
        department.setCreateDate(new Date());
        return departmentRepository.save(department);
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

    public Department updateDepartment(Department department) throws Exception {
        Department existingDepartment = departmentRepository.findById(department.getId()).get();
        if (!Boolean.TRUE.equals(existingDepartment.getIsActive())) {
            throw new Exception("Department is not active");
        }

        if (existingDepartment.getIsActive()) {
            department.setUpdatedDate(new Date());
            department.setCreateDate(existingDepartment.getCreateDate());
            department.setIsActive(existingDepartment.getIsActive());

            return departmentRepository.save(department);
        } else {
            throw new Exception("Department not found");
        }
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

    public Department getDepartmentById(Integer id) throws Exception {
        return  departmentRepository.findById(id).get();

    }


}




