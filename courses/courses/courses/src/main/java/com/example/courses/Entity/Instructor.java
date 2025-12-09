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
    private Integer id;
    private String Name;
    private String Subject;
    private Date CreateDate;
    private Date UpdateDate;
    private Boolean isActive;
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course courses;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    Department department;

}
