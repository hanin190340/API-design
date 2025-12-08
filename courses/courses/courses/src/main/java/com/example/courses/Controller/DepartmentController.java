package com.example.courses.Controller;

import com.example.courses.Entity.Department;
import com.example.courses.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/createDepartment")
    public Department createDepartment(@RequestBody Department requestObj) {
        Department department = departmentService.saveDepartment(requestObj);
        return department;

    }

    @GetMapping("/getDepartmentById/{id}")
    public Department getDepartment(@PathVariable int id) throws Exception {
        return departmentService.getDepartmentById(id);
    }


    @GetMapping("/getAllDepartments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping("/updateDepartment")
    public Department updateDepartment(@RequestBody Department updateObjFromUser) throws Exception {
        return departmentService.updateDepartment(updateObjFromUser);

    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable int id) throws Exception {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}
