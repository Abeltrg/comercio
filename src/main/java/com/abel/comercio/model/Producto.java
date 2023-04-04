package com.abel.comercio.model;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author abeltrg
 *
 */
@Entity
@Table(name = "PRODUCTOS")
public class Producto {


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "uuid", nullable = false)
	@ApiModelProperty(notes = "Identificador del producto", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private UUID id;

	@NotBlank
	@Size(max = 13)
	@Column(name = "CODIGO_BARRAS", length = 13, unique = true, nullable = false)
	@ApiModelProperty(notes = "Código de barras del producto", example = "8484848456352", required = true)
	private String codigoBarras;

	@NotBlank
	@Size(max = 50)
	@Column(name = "NOMBRE", length = 50, nullable = false)
	@ApiModelProperty(notes = "Nombre del producto", example = "Abrigo", required = true)
	private String nombre;

	@Size(max = 1000)
	@Column(name = "DESCRIPCION", length = 1000, nullable = true)
	@ApiModelProperty(notes = "Descripción del producto", example = "Abrigo de piel marrón", required = false)
	private String descripcion;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	@Column(name = "PRECIO", precision = 8, scale = 2, nullable = false)
	@ApiModelProperty(notes = "Precio del producto", example = "20", required = true)
	private BigDecimal precio;

	// TODO: Mejora. Creación entidad Almacen para distribuir y gestionar el stock
	// por almacenes
	@NotNull
	@Digits(integer = 6, fraction = 2)
	@Column(name = "STOCK", precision = 8, scale = 2, nullable = false)
	@ApiModelProperty(notes = "Stock/Cantidad disponible del producto", example = "2", required = true)
	private BigDecimal stock;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	@JsonManagedReference(value = "producto")
	@ApiModelProperty(notes = "Relación de productos en una cesta", required = false)
	private List<ProductoCesta> productosCestas = new ArrayList<>(0);

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
	@JsonManagedReference(value = "producto")
	@ApiModelProperty(notes = "Relación con las líneas de factura", required = true)
	private List<LineaFactura> lineasFacturas = new ArrayList<>(0);


	public Producto() {
		super();
	}


	@Override
	public String toString() {
		return "Producto [codigoBarras=" + codigoBarras + ", nombre=" + nombre + "]";
	}


	public UUID getId() {
		return id;
	}


	public void setId(final UUID id) {
		this.id = id;
	}


	public String getCodigoBarras() {
		return codigoBarras;
	}


	public void setCodigoBarras(final String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}


	public BigDecimal getPrecio() {
		return precio;
	}


	public void setPrecio(final BigDecimal precio) {
		this.precio = precio;
	}


	public void setStock(final BigDecimal stock) {
		this.stock = stock;
	}


	public BigDecimal getStock() {
		return stock;
	}


	public List<ProductoCesta> getProductosCestas() {
		return productosCestas;
	}


	public void setProductosCestas(final List<ProductoCesta> productosCestas) {
		this.productosCestas = productosCestas;
	}


	public void setLineasFacturas(final List<LineaFactura> lineasFacturas) {
		this.lineasFacturas = lineasFacturas;
	}


	public List<LineaFactura> getLineasFacturas() {
		return lineasFacturas;
	}

}
