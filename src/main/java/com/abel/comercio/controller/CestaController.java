package com.abel.comercio.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abel.comercio.model.Cesta;
import com.abel.comercio.service.CestaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author abeltrg
 *
 */
@RestController
@RequestMapping("/cestas")
public class CestaController {


	private static final String PARAM_CESTA = "cesta";
	private static final String PARAM_MENSAJE = "mensaje";


	@Autowired
	CestaService cestaService;


	@GetMapping("/")
	@ApiOperation(value = "Obtiene la lista de cestas almacenadas", notes = "Devuelve una lista de cestas")
	public ResponseEntity<List<Cesta>> obtenerCestas() {
		return ResponseEntity.ok(cestaService.obtenerCestas());
	}


	@PostMapping(value = "/anadirProducto")
	@ApiOperation(value = "Añade un producto a la cesta indicada o a una nueva si esta no se indica", notes = "Devuelve una cesta nueva o actualizada y un mensaje de éxito o error")
	public ResponseEntity<Map<String, Object>> anadirProducto(
			@ApiParam(name = "idProducto", type = "UUID", value = "id de producto", example = "e76293dd-41ef-47c2-9d1a-95b5da9d9383", required = true) @RequestParam final UUID idProducto,
			@ApiParam(name = "idCesta", type = "UUID", value = "id de cesta", required = false) @RequestParam(required = false) final UUID idCesta,
			@ApiParam(name = "unidades", type = "BigDecimal", value = "Unidades de producto a añadir", example = "2", required = true) @RequestParam final BigDecimal unidades) {

		final Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put(PARAM_CESTA, cestaService.anadirProducto(idCesta, idProducto, unidades));
			resultado.put(PARAM_MENSAJE, "Producto añadido");

		} catch (final Exception e) {
			resultado.put(PARAM_CESTA, null);
			resultado.put(PARAM_MENSAJE, e.getMessage());
			return new ResponseEntity<>(resultado, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}


	@PostMapping(value = "/restarProducto")
	@ApiOperation(value = "Resta las unidades de producto indicadas de un producto y cesta indicados", notes = "Devuelve una cesta actualizada y un mensaje de éxito o error")
	public ResponseEntity<Map<String, Object>> restarProducto(
			@ApiParam(name = "idProducto", type = "UUID", value = "id de producto", example = "e76293dd-41ef-47c2-9d1a-95b5da9d9383", required = true) @RequestParam final UUID idProducto,
			@ApiParam(name = "idCesta", type = "UUID", value = "id de cesta", required = true) @RequestParam(name = "idCesta") final UUID idCesta,
			@ApiParam(name = "unidades", type = "BigDecimal", value = "Unidades de producto a eliminar", example = "2", required = true) @RequestParam final BigDecimal unidades) {

		final Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put(PARAM_CESTA, cestaService.restarProducto(idCesta, idProducto, unidades));
			resultado.put(PARAM_MENSAJE, "Producto restado");

		} catch (final Exception e) {
			resultado.put(PARAM_CESTA, null);
			resultado.put(PARAM_MENSAJE, e.getMessage());
			return new ResponseEntity<>(resultado, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

}
