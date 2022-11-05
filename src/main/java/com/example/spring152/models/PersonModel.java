package com.example.spring152.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "person_152")
@Data
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "person_name")
    private String name;

    @Column(name = "age")
    private int age;
}
