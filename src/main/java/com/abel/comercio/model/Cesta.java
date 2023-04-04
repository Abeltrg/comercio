package com.abel.comercio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author abeltrg
 *
 */
@Entity
@Table(name = "CESTAS")
public class Cesta {


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "uuid", nullable = false)
	@ApiModelProperty(notes = "Identificador de la cesta", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private UUID id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA", nullable = false)
	@ApiModelProperty(notes = "Fecha de creación de la cesta", example = "2023-04-02T19:06:09.834+00:00", required = true)
	private Date fecha;

	@OneToMany(mappedBy = "cesta", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference(value = "cesta")
	@ApiModelProperty(notes = "Relación de productos en una cesta", required = true)
	private List<ProductoCesta> productosCestas = new ArrayList<>(0);


	public Cesta() {
		super();
	}


	public Cesta(final Date fecha) {
		super();
		this.fecha = fecha;
	}


	public Cesta(final UUID id, @NotNull final Date fecha, final List<ProductoCesta> productosCestas) {
		this.id = id;
		this.fecha = fecha;
		this.productosCestas = productosCestas;
	}


	public UUID getId() {
		return id;
	}


	public void setId(final UUID id) {
		this.id = id;
	}


	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}


	public Date getFecha() {
		return fecha;
	}


	public List<ProductoCesta> getProductosCestas() {
		return productosCestas;
	}


	public void setProductosCestas(final List<ProductoCesta> productosCestas) {
		this.productosCestas = productosCestas;
	}

}
