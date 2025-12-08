package com.example.courses.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String Name;
    String Subject;
    Date CreateDate;
    Date UpdateDate;
    Boolean isActive;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Courses courses;
    @ManyToOne(cascade = CascadeType.ALL)
    Department department;

}
