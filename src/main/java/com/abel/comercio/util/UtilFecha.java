package com.abel.comercio.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author abeltrg
 *
 */
public class UtilFecha {


	private UtilFecha() {
		super();
	}


	public static Date calcularFecha(final Date fecha, final int unidadTiempo, final int cantidad) {

		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha);
		calendar.add(unidadTiempo, cantidad);

		return calendar.getTime();
	}

}
