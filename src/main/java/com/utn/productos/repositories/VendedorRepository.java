package com.utn.productos.repositories;

import com.utn.productos.models.Compra;
import com.utn.productos.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RepositoryRestResource( path="vendedor" )
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    //aca van los request o response custom
    public Vendedor findByNombre(String nombre);
    public List<Vendedor> findByNombreContaining(String nombre);
}
