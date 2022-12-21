package com.utn.productos.controllers;

import com.utn.productos.DTO.CompraDTO;
import com.utn.productos.DTO.ProductoPersonalizadoDTO;
import com.utn.productos.DTO.VendedorDTO;
import com.utn.productos.models.*;
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
public class VendedorControllerComplement {
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
    @PostMapping("/vendedor/{vendedorID}/compra")
    public @ResponseBody ResponseEntity<CompraDTO> agregarCompraVendedor(@PathVariable("vendedorID") Long id, @RequestBody @Valid CompraDTO compra ) {
//        System.out.println(compra);
        Vendedor vendedor = repo_Vendedor.findById(id).get();
        Usuario usuario = repo_usuario.findByNombre( compra.getUsuario() );
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
    @GetMapping("/vendedor/{vendedorID}/compra")
    public @ResponseBody ResponseEntity< List<CompraDTO> > obtenerProductoBaseProductoBase(@PathVariable("vendedorID") Long id) {
        if ( repo_Vendedor.existsById(id) ) {
            Vendedor vendedor = repo_Vendedor.findById(id).get();
            List<CompraDTO> compras = vendedor.getCompras().stream().map( c -> new CompraDTO(c) ).collect(Collectors.toList());
//            System.out.println( compras.stream().map( c -> c.getProductos() ).collect(Collectors.toList()) );
            return new ResponseEntity< List<CompraDTO> >(compras, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/vendedor/{vendedorID}/compra/{compraID}")
    public @ResponseBody ResponseEntity< CompraDTO > obtenerCompraVender(@PathVariable("vendedorID") Long idVenta, @PathVariable("compraID") Long idCompra) {
        if ( repo_Vendedor.existsById(idVenta) ) {
            Vendedor vendedor = repo_Vendedor.findById(idVenta).get();
            CompraDTO compra = new CompraDTO(repo_compra.findById(idCompra).get());
//            System.out.println( compras.stream().map( c -> c.getProductos() ).collect(Collectors.toList()) );
            return new ResponseEntity< CompraDTO >(compra, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping( "/vendedor/{vendedorID}/productoPersonalizado" )
    public @ResponseBody ResponseEntity<ProductoPersonalizadoDTO> agregarProductoPersonalizadoVendedor(@PathVariable("vendedorID") Long id, @RequestBody @Valid ProductoPersonalizadoDTO productoPersonalizado){
        if( repo_Vendedor.existsById(id) && repo_producto_personalizado.findByNombre( productoPersonalizado.getNombre() ) == null ){
            ProductoBase productoBase = repoBase.findByNombre(productoPersonalizado.getProductoBase());
            ProductoPersonalizado productoPersonalizadoDB = new ProductoPersonalizado(productoPersonalizado, productoBase);
            Vendedor vendedor = repo_Vendedor.findById(id).get();
            repo_producto_personalizado.save(productoPersonalizadoDB);
            vendedor.agregarProductoPersonalizado( repo_producto_personalizado.findByNombre(productoPersonalizadoDB.getNombre()) );
            repo_Vendedor.save(vendedor);
                return new ResponseEntity<ProductoPersonalizadoDTO>(productoPersonalizado, HttpStatus.OK);
        }
            return ResponseEntity.notFound().build();
    }
    @GetMapping( "/vendedor/{vendedorID}/productoPersonalizado" )
    public @ResponseBody ResponseEntity< List<ProductoPersonalizado> > agregarProductoPersonalizadoVendedor(@PathVariable("vendedorID") Long id){
        if( repo_Vendedor.existsById(id) ){
            Vendedor vendedor = repo_Vendedor.findById(id).get();
            List<ProductoPersonalizado> productos = vendedor.getProductos();
            return new ResponseEntity< List<ProductoPersonalizado> >(productos, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping( "/vendedor/{vendedorID}/productoPersonalizado/{ProductoPersonalizadoID}" )
    public @ResponseBody ResponseEntity< ProductoPersonalizadoDTO > borrarProductoPersonalizadoVendedor(@PathVariable("vendedorID") Long idVendedor, @PathVariable("ProductoPersonalizadoID") Long idProductoPersonalizado){
        if( repo_Vendedor.existsById(idVendedor) && repo_producto_personalizado.existsById(idProductoPersonalizado)){
            Vendedor vendedor = repo_Vendedor.findById(idVendedor).get();
            ProductoPersonalizado producto = repo_producto_personalizado.findById(idProductoPersonalizado).get();
            vendedor.borrarProductoPersonalizado(producto);
            repo_Vendedor.save(vendedor);
            return new ResponseEntity< ProductoPersonalizadoDTO >( new ProductoPersonalizadoDTO(producto), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping( "/vendedor/{vendedorID}/productoPersonalizado/{ProductoPersonalizadoID}/pause" )
    public @ResponseBody ResponseEntity< ProductoPersonalizadoDTO > pausarProductoPersonalizadoVendedor(@PathVariable("vendedorID") Long idVendedor, @PathVariable("ProductoPersonalizadoID") Long idProductoPersonalizado){
        if( repo_Vendedor.existsById(idVendedor) && repo_producto_personalizado.existsById(idProductoPersonalizado)){
            Vendedor vendedor = repo_Vendedor.findById(idVendedor).get();
            ProductoPersonalizado producto = repo_producto_personalizado.findById(idProductoPersonalizado).get();
            producto.pauseProductoPersonalizado();
            repo_producto_personalizado.save(producto);
            return new ResponseEntity< ProductoPersonalizadoDTO >( new ProductoPersonalizadoDTO(producto), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping( "/vendedor/{vendedorID}/productoPersonalizado/{ProductoPersonalizadoID}/play" )
    public @ResponseBody ResponseEntity< ProductoPersonalizadoDTO > playProductoPersonalizadoVendedor(@PathVariable("vendedorID") Long idVendedor, @PathVariable("ProductoPersonalizadoID") Long idProductoPersonalizado){
        if( repo_Vendedor.existsById(idVendedor) && repo_producto_personalizado.existsById(idProductoPersonalizado)){
            Vendedor vendedor = repo_Vendedor.findById(idVendedor).get();
            ProductoPersonalizado producto = repo_producto_personalizado.findById(idProductoPersonalizado).get();
            producto.playProductoPersonalizado();
            repo_producto_personalizado.save(producto);
            return new ResponseEntity< ProductoPersonalizadoDTO >( new ProductoPersonalizadoDTO(producto), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping( "/vendedor/search/{vendedorNombre}" )
    public @ResponseBody ResponseEntity< List<Vendedor> > searchProductoName(@PathVariable("vendedorNombre") String nombre){
        if(repo_usuario.findByNombreContaining(nombre) != null){
            List<Vendedor> vendedores = repo_Vendedor.findByNombreContaining(nombre);
            return new ResponseEntity< List<Vendedor> >(vendedores, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
