package com.ciesto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_master")
@Data
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

}
