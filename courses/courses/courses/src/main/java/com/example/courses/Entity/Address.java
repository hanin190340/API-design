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
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String houseNumber;
    private String street;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;

    private Boolean isActive;

    private Date createdDate;

    private Date updatedDate;


    @OneToOne
    @JoinColumn(name = "student")
    private Student student;
}
