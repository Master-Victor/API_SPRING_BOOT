package com.utn.productos.repositories;

import com.utn.productos.models.Compra;
import com.utn.productos.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource( path="vendedor" )
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    //aca van los request o response custom
    public Vendedor findByNombre(String nombre);
}
