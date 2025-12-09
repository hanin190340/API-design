package com.example.courses.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Boolean isActive;
    String name;
    Date UpdatedDate;
    Date createDate;
@OneToMany (mappedBy = "department")
    private List<Instructor> instructors;

}

