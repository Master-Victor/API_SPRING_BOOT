package com.utn.productos.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table( name = "usuario")
public class Usuario extends EntidadPersistente{
    //one to many
    //id de las compras
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column( name = "contrasenia")
    private String contrasenia;
    @OneToMany( mappedBy = "usuario",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Compra> compras;
    public Usuario(){
        this.compras = new ArrayList<>();
    }
    public Usuario(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.compras = new ArrayList<>();
    }
    public void agregarCompra( Compra compra ){
        compras.add(compra);
        compra.setUsuario(this);
    }
}
