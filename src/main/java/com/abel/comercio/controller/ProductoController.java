package com.abel.comercio.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abel.comercio.model.Producto;
import com.abel.comercio.service.ProductoService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author abeltrg
 *
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {


	@Autowired
	ProductoService productoService;


	@GetMapping("/")
	@ApiOperation(value = "Obtiene la lista de productos almacenados", notes = "Devuelve una lista de productos")
	public ResponseEntity<List<Producto>> obtenerProductos() {
		return ResponseEntity.ok(productoService.obtenerProductos());
	}


	@GetMapping("/disponibles")
	@ApiOperation(value = "Obtiene la lista de productos almacenados que cuentan con stock mayor a 0", notes = "Devuelve la lista de productos con stock")
	public ResponseEntity<List<Producto>> obtenerProductosDisponibles() {
		return ResponseEntity.ok(productoService.obtenerProductosDisponibles());
	}


	@GetMapping("/masvendidosultimasemana")
	@ApiOperation(value = "Obtiene los productos que han sido vendidos la última semana ordenados de más a menos",
	notes = "Devuelve una relación del código de barras del producto y las unidades vendidas")
	public ResponseEntity<Map<Producto, BigDecimal>> obtenerProductosMasVendidosUltimaSemana() {
		return ResponseEntity.ok(productoService.obtenerProductosMasVendidosUltimaSemana());
	}

}
