package com.example.courses.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table
@ToString(exclude = "course") // prevents recursion
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String Name;
    private String Subject;
    private Date CreateDate;
    private Date UpdateDate;
    private Boolean isActive;


    @OneToOne(mappedBy = "instructor")
    private Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    Department department;

}
