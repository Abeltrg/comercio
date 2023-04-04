package com.abel.comercio.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abel.comercio.enums.FormaPago;
import com.abel.comercio.exception.ComercioException;
import com.abel.comercio.model.Cesta;
import com.abel.comercio.model.Factura;
import com.abel.comercio.model.LineaFactura;
import com.abel.comercio.repository.CestaRepository;
import com.abel.comercio.repository.FacturaRepository;
import com.abel.comercio.repository.ProductoRepository;

/**
 * 
 * @author abeltrg
 *
 */
@Service("FacturaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FacturaServiceImpl implements FacturaService {


	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	CestaRepository cestaRepository;

	@Autowired
	ProductoRepository productoRepository;



	@Override
	public List<Factura> obtenerFacturas() {
		return facturaRepository.findAll();
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public Factura facturar(final String nombreCliente, final FormaPago formaPago, final UUID idCesta) throws ComercioException {

		final Optional<Cesta> cestaOptional = cestaRepository.findById(idCesta);
		if (!cestaOptional.isPresent()) {
			throw new ComercioException("Cesta inexistente");
		}

		final Cesta cesta = cestaOptional.get();
		if (cesta.getProductosCestas().isEmpty()) {
			throw new ComercioException("Cesta vacía");
		}

		final Factura factura = new Factura(new Date(), nombreCliente, formaPago);

		final List<LineaFactura> lineasFacturas = new ArrayList<>(0);
		cesta.getProductosCestas()
		.forEach(iterProdCesta -> lineasFacturas.add(
				new LineaFactura(factura, iterProdCesta.getProducto(), iterProdCesta.getProducto().getNombre(),	iterProdCesta.getUnidades(), iterProdCesta.getPrecio())));
		factura.setLineasFacturas(lineasFacturas);
		facturaRepository.save(factura);

		lineasFacturas.forEach(this::actualizarStock);
		cestaRepository.delete(cesta);

		return factura;
	}


	private void actualizarStock(final LineaFactura lineaFactura) {
		lineaFactura.getProducto().setStock(lineaFactura.getProducto().getStock().subtract(lineaFactura.getUnidades()));
		productoRepository.save(lineaFactura.getProducto());
	}

}
