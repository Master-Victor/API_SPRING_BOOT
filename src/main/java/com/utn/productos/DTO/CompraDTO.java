package com.utn.productos.DTO;
import com.utn.productos.models.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class CompraDTO {
        private Long id;
        private Long precio;
        private MetodoDePago metodoDePago;
        private String fechaDeCompra;
        private String vendedor;
        private String usuario;
        private List<String> productos;
        public CompraDTO( Compra compra ){
                this.id = compra.getId();
                this.precio = compra.getPrecio();
                this.metodoDePago = compra.getMetodoDePago();
                this.fechaDeCompra = compra.getFechaDeCompra();
                this.vendedor = compra.getVendedor().getNombre();
                this.productos = compra.getProductos().stream().map( p -> p.getNombre() ).collect(Collectors.toList());
                this.usuario = compra.getUsuario().getNombre();
        }

        public CompraDTO(Long precio, MetodoDePago metodoDePago, String usuario, List<String> productos) {
                this.precio = precio;
                this.metodoDePago = metodoDePago;
                this.usuario = usuario;
                this.productos = productos;
        }
}

