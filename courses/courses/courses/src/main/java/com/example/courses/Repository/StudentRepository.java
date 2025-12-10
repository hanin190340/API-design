package com.example.courses.Repository;

import com.example.courses.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.id =:id AND s.isActive = true")
    Student getStudentById(Integer id);
    @Query("SELECT s FROM Student s WHERE s.isActive = true")
    List<Student> findAllActiveStudents();
}
