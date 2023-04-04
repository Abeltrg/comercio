package com.abel.comercio.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abel.comercio.model.Producto;

/**
 * 
 * @author abeltrg
 *
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {


	List<Producto> findByStockGreaterThan(BigDecimal stock);

	Producto findByCodigoBarras(String codigoBarras);

}
