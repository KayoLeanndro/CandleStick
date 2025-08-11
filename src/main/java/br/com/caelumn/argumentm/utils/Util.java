package br.com.caelumn.argumentm.utils;

import java.util.Calendar;


public class Util {
	
	public static Calendar verificaDataNula(Calendar date) {
		if(date == null) {
			throw new IllegalArgumentException("O campo data não pode ser nulo");
		}
		return date;
	}
	
	
}
