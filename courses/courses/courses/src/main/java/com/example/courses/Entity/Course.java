package com.example.courses.Entity;

import com.example.courses.RequestObjects.MarkCreateRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table
@ToString(exclude = "instructor") // if Course has a back-reference
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String language;
    private Date UpdatedDate;
    private Date createDate;
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "instructor_id") // foreign key in Course table
    private Instructor instructor;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Mark> marks = new ArrayList<>();




}
