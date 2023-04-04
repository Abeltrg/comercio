package com.abel.comercio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.abel.comercio.enums.FormaPago;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author abeltrg
 *
 */
@Entity
@Table(name = "FACTURAS")
public class Factura {


	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "uuid", nullable = false)
	@ApiModelProperty(notes = "Identificador de la factura", example = "1020f066-b479-4e9c-8d2d-6265f9d5f306", required = true)
	private UUID id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA", nullable = false)
	@ApiModelProperty(notes = "Fecha de factura", example = "2023-04-02T19:06:09.834+00:00", required = true)
	private Date fecha;

	@NotBlank
	@Size(max = 100)
	@Column(name = "NOMBRE_CLIENTE", length = 100, nullable = false)
	@ApiModelProperty(notes = "Nombre del cliente en la factura", example = "José Luis", required = true)
	private String nombreCliente;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "FORMA_PAGO", length = 20, nullable = false)
	@ApiModelProperty(notes = "Forma de pago utilizada en la factura", required = true)
	private FormaPago formaPago;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference(value = "factura")
	@ApiModelProperty(notes = "Relación con las líneas de factura", required = true)
	private List<LineaFactura> lineasFacturas = new ArrayList<>(0);


	public Factura() {
		super();
	}


	public Factura(@NotNull final Date fecha, @NotBlank @Size(max = 100) final String nombreCliente,
			@NotNull final FormaPago formaPago) {
		super();
		this.fecha = fecha;
		this.nombreCliente = nombreCliente;
		this.formaPago = formaPago;
	}


	public UUID getId() {
		return id;
	}


	public void setId(final UUID id) {
		this.id = id;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(final String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public FormaPago getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(final FormaPago formaPago) {
		this.formaPago = formaPago;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}


	public List<LineaFactura> getLineasFacturas() {
		return lineasFacturas;
	}


	public void setLineasFacturas(final List<LineaFactura> lineasFacturas) {
		this.lineasFacturas = lineasFacturas;
	}

}
