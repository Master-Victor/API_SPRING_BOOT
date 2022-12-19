package com.utn.productos.repositories;

import com.utn.productos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource( path="usuario" )
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //aca van los request o response custom
    public Usuario findByNombre(String nombre );
}
