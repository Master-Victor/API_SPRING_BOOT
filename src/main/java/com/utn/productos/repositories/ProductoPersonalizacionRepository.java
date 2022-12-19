package com.utn.productos.repositories;

import com.utn.productos.models.ProductoPersonalizado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="productosPersonalizado")
public interface ProductoPersonalizacionRepository extends JpaRepository<ProductoPersonalizado, Long> {
//    public List<ProductoPersonalizado> findByNombre(String nombre, Pageable page);
//    public ProductoPersonalizado findByTexto(String texto );
//    @RestResource( exported = false ) //para que no este publico
//    @Override
//    void deleteById(Long id);
//    @RestResource( exported = false )   //para que no este publico
//    @Override
//    void delete(ProductoPersonalizacion entity);
    public ProductoPersonalizado findByNombre( String nombre );
}
