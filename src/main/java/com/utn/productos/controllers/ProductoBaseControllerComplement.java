package com.utn.productos.controllers;

import com.utn.productos.models.Categoria;
import com.utn.productos.models.ProductoBase;
import com.utn.productos.repositories.CategoriaRepository;
import com.utn.productos.repositories.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RepositoryRestController
public class ProductoBaseControllerComplement {
    @Autowired
    ProductoBaseRepository repo;
    @Autowired
    CategoriaRepository repoCategoria;
    @PostMapping("/ProductoBase/{ProductoBaseID}/categoria")
    public @ResponseBody ResponseEntity<ProductoBase> agregarCategoriaProductoBase(@PathVariable("ProductoBaseID") Long id, @RequestBody @Valid Categoria categoria){
        if( repo.existsById(id) ){
            ProductoBase producto = repo.findById(id).get();
            Categoria categoriaRepo = repoCategoria.findByNombre(categoria.getNombre() );
            if( categoriaRepo == null ){    //si no existe la categoria la guarda y luego guarda la categoria en el producto
                repoCategoria.save( categoria );
                producto.setCategoria(categoria);
            }else{
                producto.setCategoria(categoriaRepo);   //en caso de existir solo la guarda en el producto
                repo.save(producto);
            }
            return new ResponseEntity<ProductoBase>(producto, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping( "/ProductoBase/{ProductoBaseID}/categoria" )
    public @ResponseBody ResponseEntity<Categoria> obtenerCategoriaProductoBase(@PathVariable("ProductoBaseID") Long id){
        if( repo.existsById(id) ){
            ProductoBase producto = repo.findById(id).get();
            return new ResponseEntity<Categoria>(producto.getCategoria(), HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping( "/ProductoBase/{ProductoBaseID}/categoria" )
    public @ResponseBody ResponseEntity<ProductoBase> actualizarCategoriaProductoBase(@PathVariable("ProductoBaseID") Long id, @RequestBody @Valid Categoria categoria){
        if( repo.existsById(id) ){
            ProductoBase producto = repo.findById(id).get();
            Categoria categoriaRepo = repoCategoria.findByNombre(categoria.getNombre() );
            if( categoriaRepo == null ){    //si no existe la categoria la guarda y luego guarda la categoria en el producto
                repoCategoria.save( categoria );
                producto.setCategoria(categoria);
            }else{
                producto.setCategoria(categoriaRepo);   //en caso de existir solo la guarda en el producto
                repo.save(producto);
            }
            return new ResponseEntity<ProductoBase>(producto, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping( "/ProductoBase/search/{ProductoBaseNombre}" )
        public @ResponseBody ResponseEntity< List<ProductoBase> > searchProductoName(@PathVariable("ProductoBaseNombre") String nombre){
            if(repo.findByNombreContaining(nombre) != null){
                List<ProductoBase> productos = repo.findByNombreContaining(nombre);
                return new ResponseEntity< List<ProductoBase> >(productos, HttpStatus.OK);
            }else{
                return ResponseEntity.notFound().build();
            }
        }
}
