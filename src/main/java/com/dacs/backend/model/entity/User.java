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

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String bornDate;

    private String registerDate;

    private Boolean isActiveUser;

    private String profileDescription;

    private String userName;

    private String password;
}
