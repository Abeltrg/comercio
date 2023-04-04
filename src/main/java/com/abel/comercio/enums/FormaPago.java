package com.abel.comercio.enums;

/**
 * 
 * @author abeltrg
 *
 */
public enum FormaPago {


	BIZUM("bizum"),
	CUENTA_BANCARIA("Cuenta bancaria"),
	PAYPAL("paypal"),
	TARJETA("tarjeta");


	private final String descripcion;


	FormaPago(final String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDescripcion() {
		return descripcion;
	}

}
