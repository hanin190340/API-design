package com.example.courses.Repository;

import com.example.courses.Entity.Courses;
import com.example.courses.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstuctorRepository extends JpaRepository<Instructor, Integer> {
    @Query("SELECT i FROM Instructor i WHERE i.course_id =:courseId AND i.isActive = true")
    Instructor getInstructorById(Integer courseId);
}
