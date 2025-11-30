package com.levelup.proyectoFullstack_ll.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="plataforma")
@Data
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String nombre;
}
