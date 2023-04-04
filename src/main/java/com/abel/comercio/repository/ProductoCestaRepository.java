package com.abel.comercio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abel.comercio.model.Cesta;
import com.abel.comercio.model.Producto;
import com.abel.comercio.model.ProductoCesta;

/**
 * 
 * @author abeltrg
 *
 */
@Repository
public interface ProductoCestaRepository extends JpaRepository<ProductoCesta, UUID> {


	List<ProductoCesta> findByCesta(Cesta cesta);

	ProductoCesta findByProductoAndCesta(Producto producto, Cesta cesta);

}
