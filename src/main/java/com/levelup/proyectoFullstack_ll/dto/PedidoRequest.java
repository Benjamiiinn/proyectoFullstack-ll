package com.levelup.proyectoFullstack_ll.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String region;
    private String comuna;

    private List<ProductoPedidoDto> items;

    @Data
    public static class  ProductoPedidoDto {
        private int idProducto;
        private int cantidad;
        private int precio;
    }
}
