package com.example.courses.Repository;

import com.example.courses.Entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
    @Query("SELECT p FROM PhoneNumber p WHERE p.id =:id AND p.isActive = true")
    PhoneNumber getPhoneNumberById(Integer id);
}
