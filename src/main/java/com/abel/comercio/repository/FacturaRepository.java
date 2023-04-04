package com.abel.comercio.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abel.comercio.model.Factura;

/**
 * 
 * @author abeltrg
 *
 */
@Repository
public interface FacturaRepository extends JpaRepository<Factura, UUID> {


	List<Factura> findByFechaBetween(Date inicio, Date fin);

}
