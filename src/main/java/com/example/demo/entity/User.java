package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data; // Se tiver Lombok. Se der erro, avise.

@Entity
@Table(name = "tb_user")
@Data // Gera Getters, Setters e toString automaticamente
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}