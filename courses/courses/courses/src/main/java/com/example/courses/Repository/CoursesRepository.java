package com.example.courses.Repository;

import com.example.courses.Entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    @Query("SELECT c FROM Courses c WHERE c.isActive = true AND c.id =:courseId")
    Courses getCoursesById(Integer courseId);
}
