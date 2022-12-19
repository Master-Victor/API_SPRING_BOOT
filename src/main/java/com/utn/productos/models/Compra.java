package com.utn.productos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utn.productos.DTO.CompraDTO;
import com.utn.productos.converter.LocalDateAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table( name = "compra")
public class Compra extends EntidadPersistente{
    //one to many
    //private Long ID producto_personalizados
    private Long precio;
    @Enumerated
    private MetodoDePago metodoDePago;
    @Column( name = "fecha_de_compra")
    private String fechaDeCompra;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn( name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn( name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinColumn( name = "producto_personalizado_id", referencedColumnName = "id")
//    @JsonIgnoreProperties( value = {"applications", "hibernateLazyInitializer"})
    private List<ProductoPersonalizado> productos;
    public Compra(){
        this.productos = new ArrayList<>();
        this.fechaDeCompra = LocalDate.now().toString();
    }
    public Compra(Long precio, MetodoDePago metodoDePago) {
        this.precio = precio;
        this.metodoDePago = metodoDePago;
        this.productos = new ArrayList<>();
        this.fechaDeCompra = LocalDate.now().toString();
    }
}
