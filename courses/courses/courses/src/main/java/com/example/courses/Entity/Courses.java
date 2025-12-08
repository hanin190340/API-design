package com.example.courses.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String language;
    Date UpdatedDate;
    Date createDate;
    Boolean isActive;

    @OneToOne(mappedBy = "courses", cascade = CascadeType.ALL)
    Instructor instructor;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Mark> marks = new ArrayList<>();


}
