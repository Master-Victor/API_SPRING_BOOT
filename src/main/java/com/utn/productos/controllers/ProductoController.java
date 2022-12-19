package com.utn.productos.controllers;

import com.utn.productos.repositories.ProductoBaseRepository;
import com.utn.productos.models.ProductoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

//@RestController //une las peticiones http con mi codigo
//@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoBaseRepository repo;
    @RequestMapping( path={"", "/"} )
    Page<ProductoBase> productos(
            @RequestParam( name = "nombre", required = false ) String nombre, Pageable page) {
            if( nombre == null ){
//                return (Page<ProductoBase>) repo.all();
//                return new PageImpl<ProductoBase>(repo.all());
                return repo.findAll(page);
            }else{
//                return (Page<ProductoBase>) repo.findByNombre(nombre);
//                return new PageImpl<ProductoBase>((List<ProductoBase>) repo.findByNombre(nombre));
//                return new PageImpl<ProductoBase>((List<ProductoBase>) repo.findByNombre(nombre, page));
                return null;
            }

    }

    @GetMapping( path = "/{productoID}")
    ProductoBase getProducto( @PathVariable("productoID") Long producto_id ){
        Optional<ProductoBase> byId = repo.findById(producto_id);
        if( byId.isEmpty() ){
            return null;
        }else{
            return byId.get();
        }
    }
    @DeleteMapping( path = "/{productoID}")
    String delecteProducto(@PathVariable("productoID") Long producto_id) {
//        return repo.deleteById( producto_id );
        if(repo.existsById( producto_id ) ){
            repo.deleteById( producto_id );
            return "Borrado";
        }else{
            throw new IllegalStateException("el id no existe");
        }
    }
    @PostMapping( path = {"", "/"})
    public ProductoBase agregarPoducto(@RequestBody @Valid ProductoBase producto, BindingResult bindingResult){
        if( !bindingResult.hasErrors() ) {
            return repo.save(producto);
        }else{
            throw new IllegalStateException("error producto invalido");
        }
    }
}
