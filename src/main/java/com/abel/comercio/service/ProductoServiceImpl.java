package com.abel.comercio.service;

import static com.abel.comercio.util.UtilFecha.calcularFecha;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abel.comercio.model.Factura;
import com.abel.comercio.model.LineaFactura;
import com.abel.comercio.model.Producto;
import com.abel.comercio.repository.FacturaRepository;
import com.abel.comercio.repository.ProductoRepository;

/**
 * 
 * @author abeltrg
 *
 */
@Service("ProductoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductoServiceImpl implements ProductoService {


	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	FacturaRepository facturaRepository;


	@Override
	public List<Producto> obtenerProductos() {
		return productoRepository.findAll();
	}


	@Override
	public List<Producto> obtenerProductosDisponibles() {
		return productoRepository.findByStockGreaterThan(new BigDecimal(0));
	}


	@Override
	public Map<Producto, BigDecimal> obtenerProductosMasVendidosUltimaSemana() {

		final List<Factura> facturasUltimaSemana = facturaRepository.findByFechaBetween(calcularFecha(new Date(), Calendar.WEEK_OF_MONTH, -1), new Date());
		final List<LineaFactura> lineasFacturas = facturasUltimaSemana.stream().map(Factura::getLineasFacturas).flatMap(Collection::stream).toList();

		final Map<Producto, BigDecimal> productosVendidosDesordenados = lineasFacturas.stream()
				.collect(Collectors.groupingBy(iterLinea -> iterLinea.getProducto(),
						Collectors.reducing(BigDecimal.ZERO, LineaFactura::getUnidades, BigDecimal::add)));

		return productosVendidosDesordenados.entrySet().stream().sorted(Comparator.comparing(Map.Entry<Producto, BigDecimal>::getValue).reversed())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}

}
