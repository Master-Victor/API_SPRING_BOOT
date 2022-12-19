package com.utn.productos.controllers;

import com.utn.productos.DTO.ProductoPersonalizadoDTO;
import com.utn.productos.models.Categoria;
import com.utn.productos.models.ProductoBase;
import com.utn.productos.models.ProductoPersonalizado;
import com.utn.productos.repositories.CategoriaRepository;
import com.utn.productos.repositories.ProductoBaseRepository;
import com.utn.productos.repositories.ProductoPersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RepositoryRestController
public class ProductoPersonalizadoControllerComplement {
    @Autowired
    ProductoBaseRepository repo;
    @Autowired
    CategoriaRepository repoCategoria;
    @Autowired
    ProductoPersonalizacionRepository repo_producto_personalizado;

    @PostMapping("/productosPersonalizado")
    public @ResponseBody ResponseEntity<ProductoPersonalizado> agregarProductoPersonalizado(@RequestBody @Valid ProductoPersonalizadoDTO request_producto_personalizado) {
        ProductoBase productoBase = repo.findByNombre(request_producto_personalizado.getProductoBase());
        if (productoBase != null) {
            repo_producto_personalizado.save(new ProductoPersonalizado(request_producto_personalizado, productoBase));
            return new ResponseEntity<ProductoPersonalizado>(repo_producto_personalizado.findByNombre(request_producto_personalizado.getNombre()), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping ("/productosPersonalizado/{productosPersonalizadoID}")
    public @ResponseBody ResponseEntity<ProductoPersonalizado> actualizarProductoPersonalizado(@PathVariable("productosPersonalizadoID") Long id ,@RequestBody @Valid ProductoPersonalizadoDTO request_producto_personalizado) {
        ProductoBase productoBase = repo.findByNombre(request_producto_personalizado.getProductoBase());
        ProductoPersonalizado productoPersonalizado = repo_producto_personalizado.findById(id).get();
        if (productoBase != null && productoPersonalizado != null) {
            productoPersonalizado.setProductoBase(productoBase);
            repo_producto_personalizado.save(productoPersonalizado);
            return new ResponseEntity<ProductoPersonalizado>(productoPersonalizado, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/productosPersonalizado/{productosPersonalizadoID}/productoBase")
    public @ResponseBody ResponseEntity<ProductoBase> obtenerProductoBaseProductoBase(@PathVariable("productosPersonalizadoID") Long id) {
        if ( repo_producto_personalizado.existsById(id) ) {
            ProductoPersonalizado producto_personalizado = repo_producto_personalizado.findById(id).get();
            return new ResponseEntity<ProductoBase>(producto_personalizado.getProductoBase(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping( "/productosPersonalizado/{productosPersonalizadoID}/productoBase" )
    public @ResponseBody ResponseEntity<ProductoPersonalizado> actualizarProductoBaseDeProductoPersonalizado(@PathVariable("productosPersonalizadoID") Long id, @RequestBody @Valid ProductoBase productoBase){
        if( repo_producto_personalizado.existsById(id) ){
            ProductoPersonalizado productoPersonalizado = repo_producto_personalizado.findById(id).get();
            ProductoBase productoBaseDB = repo.findByNombre( productoBase.getNombre() );
            if( productoBaseDB != null ){    //si no existe la categoria la guarda y luego guarda la categoria en el producto
                productoPersonalizado.setProductoBase(productoBaseDB);   //en caso de existir solo la guarda en el producto
                repo_producto_personalizado.save(productoPersonalizado);
                return new ResponseEntity<ProductoPersonalizado>(productoPersonalizado, HttpStatus.OK);
            }
        }
            return ResponseEntity.notFound().build();
    }
}
