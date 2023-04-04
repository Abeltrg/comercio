package com.abel.comercio.controller;

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

import com.abel.comercio.enums.FormaPago;
import com.abel.comercio.model.Factura;
import com.abel.comercio.service.CestaService;
import com.abel.comercio.service.FacturaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/facturas")
public class FacturaController {


	private static final String PARAM_FACTURA = "factura";
	private static final String PARAM_MENSAJE = "mensaje";


	@Autowired
	FacturaService facturaService;

	@Autowired
	CestaService cestaService;


	@GetMapping("/")
	@ApiOperation(value = "Obtiene la lista de facturas almacenadas", notes = "Devuelve una lista de facturas")
	public ResponseEntity<List<Factura>> obtenerFacturas() {
		return ResponseEntity.ok(facturaService.obtenerFacturas());
	}


	@PostMapping("/facturar")
	@ApiOperation(value = "Realiza la facturación creando una factura con una linea de factura para cada producto en la cesta. Actualiza el stock de productos y elimina la cesta",
	notes = "Devuelve una factura y un mensaje de éxito o error")
	public ResponseEntity<Map<String, Object>> facturar(
			@ApiParam(name = "idCesta", type = "UUID", value = "id de cesta", required = true) @RequestParam final UUID idCesta,
			@ApiParam(name = "nombreCliente", type = "String", value = "Nombre del cliente para facturar", example = "Alex", required = true) @RequestParam final String nombreCliente,
			@ApiParam(name = "formaPago", value = "Forma de pago elegida", required = true) @RequestParam final FormaPago formaPago) {

		final Map<String, Object> resultado = new HashMap<>();

		try {
			resultado.put(PARAM_FACTURA, facturaService.facturar(nombreCliente, formaPago, idCesta));
			resultado.put(PARAM_MENSAJE, "Facturación realizada");

		} catch (final Exception e) {
			resultado.put(PARAM_FACTURA, null);
			resultado.put(PARAM_MENSAJE, e.getMessage());
			return new ResponseEntity<>(resultado, HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

}
