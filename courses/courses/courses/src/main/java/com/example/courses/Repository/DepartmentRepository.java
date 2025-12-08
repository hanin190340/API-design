package com.example.courses.Repository;

import com.example.courses.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT d FROM Department d WHERE d.isActive = true AND d.id =:departmentId")
    Department getDepartmentById(Integer departmentId);
}
