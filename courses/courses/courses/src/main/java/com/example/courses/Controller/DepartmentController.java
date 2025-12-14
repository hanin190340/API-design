package com.example.courses.Controller;

import com.example.courses.RequestObjects.DepartmentCreateRequestDTO;
import com.example.courses.RequestObjects.DepartmentRequestDTO;
import com.example.courses.ResponseObjects.DepartmentSummaryDTO;
import com.example.courses.Entity.Department;
import com.example.courses.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/createDepartment")
    public ResponseEntity< DepartmentSummaryDTO> createDepartment(@RequestBody DepartmentCreateRequestDTO requestObj)throws Exception {
       DepartmentCreateRequestDTO.validateDepartment(requestObj);
        DepartmentSummaryDTO department= departmentService.saveDepartment(requestObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

    @GetMapping("/getDepartmentById/{id}")
    public DepartmentSummaryDTO getDepartment(@PathVariable int id) throws Exception {
        return departmentService.getDepartmentById(id);
    }


    @GetMapping("/getAllDepartments")
    public List<DepartmentSummaryDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping("/updateDepartment")
    public DepartmentSummaryDTO updateDepartment(@RequestBody DepartmentRequestDTO updateObjFromUser) throws Exception {
        return departmentService.updateDepartment(updateObjFromUser);

    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable int id) throws Exception {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}
