package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Rol;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true, exclude = "pedidos")
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Base {

    private String nombre;

    private String email;

    private String password;

    private Rol rol;

    private Set<Pedido> pedidos;
}