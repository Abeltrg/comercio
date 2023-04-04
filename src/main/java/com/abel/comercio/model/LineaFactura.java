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
import javax.validation.constraints.NotBlank;
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
@Table(name = "LINEAS_FACTURAS")
public class LineaFactura {


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "uuid", nullable = false)
	@ApiModelProperty(notes = "Identificador de la línea de factura", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FACTURA", nullable = false)
	@JsonBackReference(value = "factura")
	@ApiModelProperty(notes = "Identificador de la factura a la que pertenecen las líneas", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private Factura factura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUCTO", nullable = false)
	@JsonBackReference(value = "producto")
	@ApiModelProperty(notes = "Identificador del producto a facturar", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private Producto producto;

	@NotBlank
	@Column(name = "NOMBRE_PRODUCTO", length = 50, nullable = false)
	@ApiModelProperty(notes = "Nombre de producto a facturar", example = "Tenis", required = true)
	private String nombreProducto;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	@Column(name = "UNIDADES", precision = 8, scale = 2, nullable = false)
	@ApiModelProperty(notes = "Unidades de producto a facturar", example = "50", required = true)
	private BigDecimal unidades = BigDecimal.ZERO;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	@Column(name = "PRECIO", precision = 8, scale = 2, nullable = false)
	@ApiModelProperty(notes = "Precio de las unidades del producto a facturar (precio del producto * unidades)", example = "56", required = true)
	private BigDecimal precio = BigDecimal.ZERO;


	public LineaFactura() {
		super();
	}


	public LineaFactura(final Factura factura, final Producto producto, final String nombreProducto,
			final BigDecimal unidades, final BigDecimal precio) {
		this.factura = factura;
		this.producto = producto;
		this.nombreProducto = nombreProducto;
		this.unidades = unidades;
		this.precio = precio;
	}


	public UUID getId() {
		return id;
	}


	public void setId(final UUID id) {
		this.id = id;
	}


	public Factura getFactura() {
		return factura;
	}


	public void setFactura(final Factura factura) {
		this.factura = factura;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(final Producto producto) {
		this.producto = producto;
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public void setNombreProducto(final String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public BigDecimal getUnidades() {
		return unidades;
	}


	public void setUnidades(final BigDecimal unidades) {
		this.unidades = unidades;
	}


	public BigDecimal getPrecio() {
		return precio;
	}


	public void setPrecio(final BigDecimal precio) {
		this.precio = precio;
	}

}
