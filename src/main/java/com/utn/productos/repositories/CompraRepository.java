package com.utn.productos.repositories;

import com.utn.productos.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource( path="compra" )
public interface CompraRepository extends JpaRepository<Compra, Long> {
    //aca van los request o response custom
}
