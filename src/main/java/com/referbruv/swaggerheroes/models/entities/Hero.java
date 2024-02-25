package com.referbruv.swaggerheroes.models.entities;

import java.sql.Date;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity(name = "hero")
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int heroId;

    private String name;
    private String bio;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @CreationTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    public Hero(String name, String bio) {
        this.setName(name);
        this.setBio(bio);
    }

    // default constructor
    // for JPA fetch operation
    public Hero() {}
}