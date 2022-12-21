package com.utn.productos.DTO;

import com.utn.productos.models.ProductoBase;
import com.utn.productos.models.ProductoPersonalizado;
import org.springframework.data.rest.core.config.Projection;

@Projection( name = "dtoProductoBase", types = {ProductoBase.class})
public interface DTOproductoBase {
//    @Value( "#{target.texto}, #{target.id}" )
    String getNombre();
    Long getId();
//    Long getPrecioExtra();

}
