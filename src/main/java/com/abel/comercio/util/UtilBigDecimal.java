package com.abel.comercio.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @author abeltrg
 *
 */
public class UtilBigDecimal {


	private UtilBigDecimal() {
		super();
	}


	public static BigDecimal multiplicarYRedondear(final BigDecimal bg1, final BigDecimal bg2) {
		return bg1.multiply(bg2).setScale(2, RoundingMode.HALF_DOWN);
	}

}
