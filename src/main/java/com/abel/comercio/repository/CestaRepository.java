package com.abel.comercio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abel.comercio.model.Cesta;

/**
 * 
 * @author abeltrg
 *
 */
@Repository
public interface CestaRepository extends JpaRepository<Cesta, UUID> {

}
