package com.abel.comercio.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.abel.comercio.exception.ComercioException;
import com.abel.comercio.model.Cesta;

/**
 * 
 * @author abeltrg
 *
 */
public interface CestaService {


	List<Cesta> obtenerCestas();

	Cesta anadirProducto(UUID idCesta, UUID idProducto, BigDecimal unidades) throws ComercioException;

	Cesta restarProducto(UUID idCesta, UUID idProducto, BigDecimal unidades) throws ComercioException;

}
