package com.abel.comercio.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.abel.comercio.model.Producto;

/**
 * 
 * @author abeltrg
 *
 */
public interface ProductoService {


	public List<Producto> obtenerProductos();

	public List<Producto> obtenerProductosDisponibles();

	public Map<Producto, BigDecimal> obtenerProductosMasVendidosUltimaSemana();

}
