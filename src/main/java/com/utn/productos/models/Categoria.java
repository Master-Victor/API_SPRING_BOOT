package com.utn.productos.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table( name = "categoria")
public class Categoria extends EntidadPersistente{
    @Column(name = "nombre",unique = true)
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    public Categoria(){}
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
