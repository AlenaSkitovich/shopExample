package com.example.spring152.models;

import com.example.spring152.models.enums.ItemEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shop_item")
@Data
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "discription")
    private String disc;

    @Column(name = "price")
    private String price;

    @Column(name = "url")
    private String url;

    @Enumerated
    @Column(name = "item_type")
    ItemEnum itemEnum;
}
