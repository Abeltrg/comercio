package com.abel.comercio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abel.comercio.model.LineaFactura;

/**
 * 
 * @author abeltrg
 *
 */
@Repository
public interface LineaFacturaRepository extends JpaRepository<LineaFactura, UUID> {

}
