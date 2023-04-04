package com.abel.comercio.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abel.comercio.exception.ComercioException;
import com.abel.comercio.model.Cesta;
import com.abel.comercio.model.Producto;
import com.abel.comercio.model.ProductoCesta;
import com.abel.comercio.service.CestaService;

/**
 * 
 * @author abeltrg
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CestaServiceTest {


	@Autowired
	CestaService cestaService;


	@Test
	void testAnadirProductoInexistente() {

		final String mensajeSiError = "Espero ComercioException";
		assertThrows(ComercioException.class, () -> {
			cestaService.anadirProducto(null, UUID.randomUUID(), new BigDecimal(5));
		}, mensajeSiError);
	}


	@Test
	void testAnadirProductoStockInsuficiente() throws ComercioException {

		final String mensajeSiError = "Espero ComercioException";
		assertThrows(ComercioException.class, () -> {
			cestaService.anadirProducto(null, UUID.fromString("1020f066-b479-4e9c-8d2d-6265f9d5f306"), new BigDecimal(70));
		}, mensajeSiError);
	}


	@Test
	void testAnadirProducto() throws ComercioException {

		final UUID idProducto = UUID.fromString("1020f066-b479-4e9c-8d2d-6265f9d5f306");
		final Cesta cesta = cestaService.anadirProducto(null, idProducto, new BigDecimal(5));
		final List<Producto> productosEnCesta = cesta.getProductosCestas().stream().map(ProductoCesta::getProducto).toList();
		assertEquals(true, productosEnCesta.stream().anyMatch(iterProducto -> iterProducto.getId().equals(idProducto)), "Id de producto inexistente entre los productos de la cesta");
	}


	@Test
	void testRestarProductoCestaInexistente() {

		final String mensajeSiError = "Espero ComercioException";
		assertThrows(ComercioException.class, () -> {
			cestaService.restarProducto(UUID.randomUUID(), null, new BigDecimal(5));
		}, mensajeSiError);
	}

}
