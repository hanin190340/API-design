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
    private Integer id;
    private Integer markObtained;
    private String studentName;
    private Double score;
    private String grade;
    private Date CreateDate;
    private Date UpdateDate;
    private Boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;
}



