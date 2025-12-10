package com.example.courses.Repository;

import com.example.courses.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("SELECT i FROM Instructor i WHERE i.id =:id AND i.isActive = true")
    Instructor getInstructorById(Integer id);
    @Query("SELECT i FROM Instructor i WHERE i.isActive = true")
    List<Instructor> findAllActiveInstructors();
}
