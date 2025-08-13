package br.com.caelum.argentum.utils;

import java.util.Calendar;

public class Util {

	public static Calendar verificaDataNula(Calendar date) {
		if (date == null) {
			throw new NullPointerException("O campo data não pode ser nulo");
		}
		return date;
	}
	

}
