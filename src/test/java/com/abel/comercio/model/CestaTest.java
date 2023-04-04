package com.abel.comercio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author abeltrg
 *
 */
class CestaTest {


	@Test
	final void testCesta() {
		assertNotNull(new Cesta());
	}


	@Test
	final void testCestaFecha() {

		final Date fecha = new Date();
		final Cesta cesta = new Cesta(fecha);
		assertEquals(fecha, cesta.getFecha(), "Fecha inesperada al instanciar Cesta con fecha");
	}

}
