package com.example.spring152.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="request")
public class RequestModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_id")
    private long itemId;

    @Column(name = "person_name")
    private String name;

    @Column(name = "contact")
    private String contact;
}
