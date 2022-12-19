package com.utn.productos;

import com.utn.productos.repositories.ProductoBaseRepository;
import com.utn.productos.repositories.ProductoPersonalizacionRepository;
import com.utn.productos.models.*;
import com.utn.productos.repositories.CompraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductosApplication {

	private static Logger Log = LoggerFactory.getLogger(ProductosApplication.class);
	@Value("${min.inscriptos}")
	private int minInscriptos;
	public static void main(String[] args) {
		SpringApplication.run(ProductosApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(){
		Log.info("arranque");
		return (args -> {
			System.out.println(minInscriptos);
		});
	}
}
