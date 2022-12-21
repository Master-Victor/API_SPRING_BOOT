package com.utn.productos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utn.productos.DTO.ProductoPersonalizadoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table( name = "producto_personalizado")
public class ProductoPersonalizado extends EntidadPersistente{
    @Column(name = "nombre",unique = true)
    private String nombre;
    private Long precioExtra;
    private String descripcion;
    @ElementCollection
    @CollectionTable( name = "estampado_producto_personalizado", joinColumns = @JoinColumn( name = "id"))
    @Column( name = "ruta")
    private List<String> estampado;
    private String color;
    private Long total;
    private Long stock;
    private boolean visible;
    @ElementCollection
    @CollectionTable( name = "fotos_producto_personalizado", joinColumns = @JoinColumn( name = "id"))
    @Column( name = "ruta")
    private List<String> fotos;
    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)  //carga cuando lo usa, cascade guarda cuando se guarda el producto
    @JoinColumn(name = "producto_base_id", referencedColumnName = "id")
    @JsonIgnoreProperties( value = {"applications", "hibernateLazyInitializer"})
    private ProductoBase productoBase;
    public ProductoPersonalizado() {
        this.fotos = new ArrayList<>();
    }

    public ProductoPersonalizado(String nombre, Long precioExtra, String descripcion, String estampado, String color, Long total, String foto, Long stock) {
        this.nombre = nombre;
        this.precioExtra = precioExtra;
        this.descripcion = descripcion;
        this.estampado = new ArrayList<>();
        this.color = color;
        this.total = total;
        this.fotos = new ArrayList<>();
        this.stock = stock;
        this.productoBase = new ProductoBase();
        this.visible  = true;
    }
    public ProductoPersonalizado(ProductoPersonalizadoDTO producto_personalizado, ProductoBase productoBase) {
        this.nombre = producto_personalizado.getNombre();
        this.precioExtra = producto_personalizado.getPrecioExtra();
        this.descripcion = producto_personalizado.getDescripcion();
        this.estampado = new ArrayList<>( producto_personalizado.getEstampado() );
        this.color = producto_personalizado.getColor();
        this.total = producto_personalizado.getTotal();
        this.fotos = new ArrayList<>( producto_personalizado.getFotos() );
        this.stock = producto_personalizado.getStock();
        this.productoBase = productoBase;
        this.visible = true;
    }
    public boolean pauseProductoPersonalizado(){
        this.visible = false;
        return this.visible;
    }
    public boolean playProductoPersonalizado(){
        this.visible = true;
        return this.visible;
    }
}
