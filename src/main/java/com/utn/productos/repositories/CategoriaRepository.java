package com.utn.productos.repositories;

import com.utn.productos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( path="categoria" )
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByNombre( String nombre );
}
