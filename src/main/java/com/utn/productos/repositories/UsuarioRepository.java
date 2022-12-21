package com.utn.productos.repositories;

import com.utn.productos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RepositoryRestResource( path="usuario" )
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //aca van los request o response custom
    public Usuario findByNombre(String nombre );
    public List<Usuario> findByNombreContaining(String nombre );
}
