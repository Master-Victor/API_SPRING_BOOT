package com.utn.productos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table( name = "vendedor")
public class Vendedor extends EntidadPersistente{
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Column( name = "contrasenia")
    private String contrasenia;
    @OneToMany( mappedBy = "vendedor", fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties( value = {"applications", "hibernateLazyInitializer"})
    private List<Compra> compras;
    @ManyToMany
//    @JoinColumn( name = "producto_personalizado_id", referencedColumnName = "id")
//    @JsonIgnoreProperties( value = {"applications", "hibernateLazyInitializer"})
    private List<ProductoPersonalizado> productos;
    public Vendedor(){
        this.compras = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public Vendedor(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.compras = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public void agregarCompra( Compra compra ){
        this.compras.add(compra);
        compra.setVendedor(this);
    }
    public void agregarProductoPersonalizado( ProductoPersonalizado producto ){
        this.getProductos().add(producto);
    }
    public void borrarProductoPersonalizado( ProductoPersonalizado producto ){
        this.productos = this.productos.stream().filter( p -> p.getNombre() != producto.getNombre() ).collect(Collectors.toList());
    }
}
