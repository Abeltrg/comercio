package com.abel.comercio.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * @author abeltrg
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {


	@Autowired
	ProductoController productoController;


	@Test
	void testObtenerProductosDisponibles() {
		assertEquals(6, productoController.obtenerProductosDisponibles().getBody().size(), "Productos disponibles inesperados");
	}

}
