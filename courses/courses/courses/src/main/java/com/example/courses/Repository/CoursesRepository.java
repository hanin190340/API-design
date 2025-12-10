package com.example.courses.Repository;

import com.example.courses.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE c.isActive = true AND c.id =:course")
    Course getCoursesById(Integer course);
    @Query("SELECT c FROM Course c WHERE c.isActive = true")
    List<Course> findAllActiveCourses();
}
