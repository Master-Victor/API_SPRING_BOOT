package com.utn.productos.repositories;

import com.utn.productos.models.ProductoBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource( path="ProductoBase" )
public interface ProductoBaseRepository extends JpaRepository<ProductoBase, Long> {
//    public List<ProductoBase> findByNombre(String nombre, Pageable page);
//     public void addProducto( ProductoBase producto );
     public ProductoBase findByNombre( String nombre );
     public List<ProductoBase> findByNombreContaining( String nombre );
//     public int count();
//     public List<ProductoBase> all();
//     public ProductoBase findById( Long producto_ID );
//     public ProductoBase deleteById( Long producto_ID );
//     public ProductoBase save( ProductoBase producto );
}
