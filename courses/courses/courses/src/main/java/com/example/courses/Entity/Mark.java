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
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Integer markObtained;
    String studentName;
    Double score;
    String grade;
    Date CreateDate;
    Date UpdateDate;
    Boolean isActive;
    @ManyToOne(cascade = CascadeType.ALL)
    private Courses course;
}



