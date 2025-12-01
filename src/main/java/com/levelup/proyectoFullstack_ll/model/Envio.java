package com.levelup.proyectoFullstack_ll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreContacto;
    
    private String emailContacto;

    private String telefonoContacto;

    private String direccion;
    private String region;
    private String comuna;

    @OneToOne(mappedBy= "envio")
    @JsonIgnore
    private Pedido pedido;
}
