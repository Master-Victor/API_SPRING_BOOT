package com.utn.productos.app;

import com.utn.productos.models.ProductoBase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductoBaseRepositoryMemory{
     private Collection<ProductoBase> productos;
     public ProductoBaseRepositoryMemory(){
         super();
         this.productos = new ArrayList<ProductoBase>();
     }
     public void addProducto( ProductoBase producto ){
         this.productos.add(producto);
     }
     public ProductoBase findByNombre( String nombre ){
         return this.productos.stream().filter( p -> p.getNombre().equals(nombre) ).findFirst().get();
     }

    public ProductoBase findById( Long producto_id ){
        return this.productos.stream().filter( p -> p.getId().equals(producto_id) ).findFirst().get();
    }

    public ProductoBase deleteById( Long producto_id ){
//         ProductoBase productoDelete = this.findById(producto_id);
//         this.productos = this.productos.stream().filter( p -> p.getId().equals(producto_id) ).toList();
//        Stream<ProductoBase> filter = this.productos.stream().filter( p -> p.getId().equals(producto_id) );
//        this.productos = filter.toList();
//            this.productos = this.productos.stream().filter( p -> p.getId().equals(producto_id) ).toList();
        return null;
    }

    public ProductoBase save( ProductoBase producto ){
         this.productos.add(producto);
         return producto;
    }
     public int count(){
         return this.productos.size();
     }

     public List<ProductoBase> all(){
         return this.productos.stream().toList();
     }

}
