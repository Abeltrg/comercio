package com.abel.comercio.service;

import static com.abel.comercio.util.UtilBigDecimal.multiplicarYRedondear;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abel.comercio.exception.ComercioException;
import com.abel.comercio.model.Cesta;
import com.abel.comercio.model.Producto;
import com.abel.comercio.model.ProductoCesta;
import com.abel.comercio.repository.CestaRepository;
import com.abel.comercio.repository.ProductoCestaRepository;
import com.abel.comercio.repository.ProductoRepository;

/**
 * 
 * @author abeltrg
 *
 */
@Service("CestaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CestaServiceImpl implements CestaService {


	@Autowired
	CestaRepository cestaRepository;

	@Autowired
	ProductoCestaRepository productoCestaRepository;

	@Autowired
	ProductoRepository productoRepository;


	@Override
	public List<Cesta> obtenerCestas() {
		return cestaRepository.findAll();
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public Cesta anadirProducto(final UUID idCesta, final UUID idProducto, final BigDecimal unidades) throws ComercioException {

		final Optional<Producto> productoOptional = productoRepository.findById(idProducto);
		if (!productoOptional.isPresent()) {
			throw new ComercioException("Producto inexistente");
		}

		final Producto producto = productoOptional.get();
		if (unidades.compareTo(producto.getStock()) > 0) {
			throw new ComercioException("Stock insuficiente");
		}

		Cesta cesta = obtenerPorId(idCesta);
		if (null == cesta) {

			cesta = new Cesta(new Date());
			final ProductoCesta productoCesta = new ProductoCesta(producto, cesta, unidades);
			productoCesta.setPrecio(multiplicarYRedondear(unidades, producto.getPrecio()));
			cesta.getProductosCestas().add(productoCesta);
			cestaRepository.save(cesta);

		} else {

			ProductoCesta productoCesta = productoCestaRepository.findByProductoAndCesta(producto, cesta);
			if (null != productoCesta) {

				final BigDecimal unidadesAAnadir = unidades.add(productoCesta.getUnidades());
				if (unidadesAAnadir.compareTo(productoCesta.getProducto().getStock()) > 0) {
					throw new ComercioException("Stock insuficiente");
				}
				productoCesta.setUnidades(unidadesAAnadir);

			} else {
				productoCesta = new ProductoCesta(producto, cesta, unidades);
			}

			productoCesta.setPrecio(multiplicarYRedondear(productoCesta.getUnidades(), productoCesta.getProducto().getPrecio()));
			productoCestaRepository.save(productoCesta);
		}

		return cesta;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public Cesta restarProducto(final UUID idCesta, final UUID idProducto, final BigDecimal unidades) throws ComercioException {

		final Cesta cesta = obtenerPorId(idCesta);
		if (null == cesta) {
			throw new ComercioException("Cesta inexistente");
		}

		final Optional<Producto> productoOptional = productoRepository.findById(idProducto);
		if (!productoOptional.isPresent()) {
			throw new ComercioException("Producto inexistente");
		}

		final Producto producto = productoOptional.get();
		final ProductoCesta productoCesta = productoCestaRepository.findByProductoAndCesta(producto, cesta);
		if (null == productoCesta) {
			throw new ComercioException("Producto inexistente en cesta");
		}

		if (unidades.compareTo(productoCesta.getUnidades()) > 0) {
			throw new ComercioException("Unidades en cesta insuficientes");
		}

		productoCesta.setUnidades(productoCesta.getUnidades().subtract(unidades));
		if (productoCesta.getUnidades().equals(BigDecimal.ZERO)) {
			productoCestaRepository.delete(productoCesta);
		} else {
			productoCesta.setPrecio(multiplicarYRedondear(productoCesta.getUnidades(), productoCesta.getProducto().getPrecio()));
			productoCestaRepository.save(productoCesta);
		}

		return cesta;
	}


	private Cesta obtenerPorId(final UUID id) {

		if (null != id) {
			final Optional<Cesta> cesta = cestaRepository.findById(id);
			return cesta.isPresent() ? cesta.get() : null;
		}

		return null;
	}
}
