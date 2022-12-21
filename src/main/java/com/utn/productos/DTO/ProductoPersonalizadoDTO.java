package com.utn.productos.DTO;
import com.utn.productos.models.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ProductoPersonalizadoDTO {
        private String nombre;
        private Long precioExtra;
        private String descripcion;
        private List<String> estampado;
        private String color;
        private Long total;
        private Long stock;
        private List<String> fotos;
        private String productoBase;
        private boolean visible;
        public ProductoPersonalizadoDTO(String nombre, Long precioExtra, String descripcion, List<String> estampado, String color, Long total, Long stock, List<String> fotos, String productoBase) {
                this.nombre = nombre;
                this.precioExtra = precioExtra;
                this.descripcion = descripcion;
                this.estampado = estampado;
                this.color = color;
                this.total = total;
                this.stock = stock;
                this.fotos = fotos;
                this.productoBase = productoBase;
                this.visible = true;
        }
        public ProductoPersonalizadoDTO(ProductoPersonalizado producto) {
                this.nombre = producto.getNombre();
                this.precioExtra = producto.getPrecioExtra();
                this.descripcion = producto.getDescripcion();
                this.estampado = producto.getEstampado();
                this.color = producto.getColor();
                this.total = producto.getTotal();
                this.stock = producto.getStock();
                this.fotos = producto.getFotos();
                this.productoBase = producto.getProductoBase().getNombre();
                this.visible = producto.isVisible();
        }
}

