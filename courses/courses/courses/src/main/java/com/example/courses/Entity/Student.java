package com.example.courses.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        private String firstName;
        private String lastName;
        private String email;
        private Date dateOfBirth;
        private String gender;
        private Boolean isActive;

        private Date createdDate;

        private Date updatedDate;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Address address;
    }


