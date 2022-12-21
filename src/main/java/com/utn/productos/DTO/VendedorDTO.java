package com.utn.productos.DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utn.productos.models.Compra;
import com.utn.productos.models.ProductoPersonalizado;
import com.utn.productos.models.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class VendedorDTO {
        private Long id;
        private String nombre;
        private String contrasenia;
        private List<Long> compras;
        private List<String> productos;

        public VendedorDTO(String nombre, String contrasenia, List<Long> compras, List<String> productos) {
                this.nombre = nombre;
                this.contrasenia = contrasenia;
                this.compras = compras;
                this.productos = productos;
        }
        public VendedorDTO(Vendedor vendedor){
                this.id = vendedor.getId();
                this.nombre = vendedor.getNombre();
                this.contrasenia = vendedor.getContrasenia();
                this.compras = vendedor.getCompras().stream().map( c -> c.getId() ).collect(Collectors.toList());
                this.productos = vendedor.getProductos().stream().map( p -> p.getNombre() ).collect(Collectors.toList());
        }
}

