package com.abel.comercio.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author abeltrg
 *
 */
@Entity
@Table(name = "PRODUCTOS_CESTAS")
public class ProductoCesta {


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "uuid", nullable = false)
	@ApiModelProperty(notes = "Identificador del productoCesta", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private UUID id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PRODUCTO", nullable = false)
	@JsonBackReference(value = "producto")
	@ApiModelProperty(notes = "Identificador del producto en la cesta", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private Producto producto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CESTA", nullable = false)
	@JsonBackReference(value = "cesta")
	@ApiModelProperty(notes = "Identificador de la cesta con el producto", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private Cesta cesta;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	@Column(name = "UNIDADES", precision = 8, scale = 2, nullable = false)
	@ApiModelProperty(notes = "Unidades del producto en la cesta", example = "10", required = true)
	private BigDecimal unidades = BigDecimal.ZERO;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	@Column(name = "PRECIO", precision = 8, scale = 2, nullable = false)
	@ApiModelProperty(notes = "Precio total de un producto en la cesta (unidades * precio del producto)", example = "200", required = true)
	private BigDecimal precio = BigDecimal.ZERO;


	public ProductoCesta() {
		super();
	}


	public ProductoCesta(final Producto producto, final Cesta cesta, final BigDecimal unidades) {
		super();
		this.producto = producto;
		this.cesta = cesta;
		this.unidades = unidades;
	}


	public UUID getId() {
		return id;
	}


	public void setId(final UUID id) {
		this.id = id;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(final Producto producto) {
		this.producto = producto;
	}


	public void setCesta(final Cesta cesta) {
		this.cesta = cesta;
	}


	public Cesta getCesta() {
		return cesta;
	}


	public void setUnidades(final BigDecimal unidades) {
		this.unidades = unidades;
	}


	public BigDecimal getUnidades() {
		return unidades;
	}


	public void setPrecio(final BigDecimal precio) {
		this.precio = precio;
	}


	public BigDecimal getPrecio() {
		return precio;
	}

}
