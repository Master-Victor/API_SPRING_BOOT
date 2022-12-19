package com.utn.productos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table( name = "producto_base")
public class ProductoBase extends EntidadPersistente{
    @Column( name = "nombre", unique = true)
    private String nombre;
    @Column( name = "precio_base")
    private Long precioBase;
    @Column( name = "descripcion")
    private String descripcion;
    @Column( name = "tiempo_de_fabricacion")
    private Long tiempoDeFabricacion;
    //categoria
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn( name = "categoria_id", referencedColumnName = "id")
    @JsonIgnoreProperties( value = {"applications", "hibernateLazyInitializer"})
    private Categoria categoria;
    public ProductoBase() {}
//    public ProductoBase(String nombre, Long precioBase, String descripcion, Long tiempoDeFabricacion, Categoria categoria) {
//        this.nombre = nombre;
//        this.precioBase = precioBase;
//        this.descripcion = descripcion;
//        this.tiempoDeFabricacion = tiempoDeFabricacion;
//        this.categoria = categoria;
//    }

    public ProductoBase(String nombre, Long precioBase, String descripcion, Long tiempoDeFabricacion) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.descripcion = descripcion;
        this.tiempoDeFabricacion = tiempoDeFabricacion;
    }
}
