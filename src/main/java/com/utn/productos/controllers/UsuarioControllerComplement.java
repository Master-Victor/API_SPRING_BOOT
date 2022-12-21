package com.utn.productos.controllers;

import com.utn.productos.DTO.CompraDTO;
import com.utn.productos.models.Compra;
import com.utn.productos.models.ProductoPersonalizado;
import com.utn.productos.models.Usuario;
import com.utn.productos.models.Vendedor;
import com.utn.productos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )

@RepositoryRestController
public class UsuarioControllerComplement {
    @Autowired
    ProductoBaseRepository repoBase;
    @Autowired
    CategoriaRepository repoCategoria;
    @Autowired
    ProductoPersonalizacionRepository repo_producto_personalizado;
    @Autowired
    VendedorRepository repo_Vendedor;
    @Autowired
    UsuarioRepository repo_usuario;
    @Autowired
    CompraRepository repo_compra;
//    @PostMapping("/vendedor/{vendedorID}/compra")
//    public @ResponseBody ResponseEntity<CompraDTO> agregarCompraVendedor(@PathVariable("vendedorID") Long id, @RequestBody @Valid CompraDTO compra ) {
//        Vendedor vendedor = repo_Vendedor.findById(id).get();
//        Usuario usuario = repo_usuario.findByNombre( compra.getUsuario() );
//        List<ProductoPersonalizado> productos = new ArrayList<>();
//        compra.getProductos().stream().forEach( p -> {
//            if( repo_producto_personalizado.findByNombre(p) != null ) {
//                productos.add( repo_producto_personalizado.findByNombre(p) );
//            }
//        } );
//        if (vendedor != null && usuario != null && productos.size() > 0) {
//            Compra compraDB = new Compra();
//            compraDB.setVendedor(vendedor);
//            compraDB.setPrecio(compra.getPrecio());
//            compraDB.setUsuario(usuario);
//            compraDB.setMetodoDePago(compra.getMetodoDePago());
//            compraDB.setProductos(productos);
//            repo_compra.save( compraDB );
//            return new ResponseEntity<CompraDTO>( new CompraDTO(compraDB) , HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("/usuario/{username}/compras")
    public @ResponseBody ResponseEntity< List<CompraDTO> > obtenerCompras(@PathVariable("username") String username) {
        System.out.println(username);
            List<Compra> compras = repo_compra.findAll().stream().filter(c ->
                c.getUsuario().getNombre().equals(username)
             ).collect(Collectors.toList());
            List<CompraDTO> comprasDTO = compras.stream().map( c -> new CompraDTO(c) ).collect(Collectors.toList());

            return new ResponseEntity< List<CompraDTO> >(comprasDTO, HttpStatus.OK);
    }
    @PostMapping("/usuario/{usuarioID}/compra")
    public @ResponseBody ResponseEntity< CompraDTO > agregarCompra(@PathVariable("usuarioID") Long id, @RequestBody @Valid CompraDTO compra) {
        Vendedor vendedor = repo_Vendedor.findByNombre( compra.getVendedor() );
        Usuario usuario = repo_usuario.findById( id ).get();
        List<ProductoPersonalizado> productos = new ArrayList<>();
        compra.getProductos().stream().forEach( p -> {
            if( repo_producto_personalizado.findByNombre(p) != null ) {
                productos.add( repo_producto_personalizado.findByNombre(p) );
            }
        } );
        if (vendedor != null && usuario != null && productos.size() > 0) {
            Compra compraDB = new Compra();
            compraDB.setVendedor(vendedor);
            compraDB.setPrecio(compra.getPrecio());
            compraDB.setUsuario(usuario);
            compraDB.setMetodoDePago(compra.getMetodoDePago());
            compraDB.setProductos(productos);
            repo_compra.save( compraDB );
            return new ResponseEntity<CompraDTO>( new CompraDTO(compraDB) , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping( "/usuario/search/{UsuarioNombre}" )
    public @ResponseBody ResponseEntity< List<Usuario> > searchProductoName(@PathVariable("UsuarioNombre") String nombre){
        if(repo_usuario.findByNombreContaining(nombre) != null){
            List<Usuario> usuarios = repo_usuario.findByNombreContaining(nombre);
            return new ResponseEntity< List<Usuario> >(usuarios, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
//    @GetMapping("/compra/{compraID}")
//    public @ResponseBody ResponseEntity< CompraDTO > obtenerCompra(@PathVariable("compraID") Long id) {
//        CompraDTO compra = new CompraDTO(repo_compra.findById(id).get());
//        return new ResponseEntity< CompraDTO >(compra, HttpStatus.OK);
//    }
//    @GetMapping("/vendedor/{vendedorID}/compra/{compraID}")
//    public @ResponseBody ResponseEntity< CompraDTO > obtenerCompraVender(@PathVariable("vendedorID") Long idVenta, @PathVariable("compraID") Long idCompra) {
//        if ( repo_Vendedor.existsById(idVenta) ) {
//            Vendedor vendedor = repo_Vendedor.findById(idVenta).get();
//            CompraDTO compra = new CompraDTO(repo_compra.findById(idCompra).get());
////            System.out.println( compras.stream().map( c -> c.getProductos() ).collect(Collectors.toList()) );
//            return new ResponseEntity< CompraDTO >(compra, HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
////    @PutMapping( "/productosPersonalizado/{productosPersonalizadoID}/productoBase" )
//    public @ResponseBody ResponseEntity<ProductoPersonalizado> actualizarProductoBaseDeProductoPersonalizado(@PathVariable("productosPersonalizadoID") Long id, @RequestBody @Valid ProductoBase productoBase){
//        if( repo_producto_personalizado.existsById(id) ){
//            ProductoPersonalizado productoPersonalizado = repo_producto_personalizado.findById(id).get();
//            ProductoBase productoBaseDB = repo.findByNombre( productoBase.getNombre() );
//            if( productoBaseDB != null ){    //si no existe la categoria la guarda y luego guarda la categoria en el producto
//                productoPersonalizado.setProductoBase(productoBaseDB);   //en caso de existir solo la guarda en el producto
//                repo_producto_personalizado.save(productoPersonalizado);
//                return new ResponseEntity<ProductoPersonalizado>(productoPersonalizado, HttpStatus.OK);
//            }
//        }
//            return ResponseEntity.notFound().build();
//    }
}
