package com.tup.programacion3.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(of = "nombre")
public class Producto extends Base {

    private String nombre;

    private Double precio;

    private String descripcion;

    private Integer stock;

    private String imagen;

    private Boolean disponible;

    private Categoria categoria;
}