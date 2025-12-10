package com.example.courses.Repository;

import com.example.courses.DTO.MarkCreateRequestDTO;
import com.example.courses.Entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    @Query("SELECT m FROM Mark m WHERE m.id IN : marks")
    List<Mark>getMarkByIds(List<String> marks);
    @Query("SELECT m FROM Mark m WHERE m.isActive = true AND m.id =:markId")
    Mark getMarkById(Integer markId);
    @Query("SELECT m FROM Mark m WHERE m.isActive = true")
    List<Mark> findAllActiveMarks();
}
