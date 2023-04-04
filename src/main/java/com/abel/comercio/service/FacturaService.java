package com.abel.comercio.service;

import java.util.List;
import java.util.UUID;

import com.abel.comercio.enums.FormaPago;
import com.abel.comercio.exception.ComercioException;
import com.abel.comercio.model.Factura;

/**
 * 
 * @author abeltrg
 *
 */
public interface FacturaService {


	List<Factura> obtenerFacturas();

	Factura facturar(String nombreCliente, FormaPago formaPago, UUID idCesta) throws ComercioException;

}
