package com.dacs.backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String bornDate;

    private String registerDate;

    private Boolean active;

    private String description;
}
