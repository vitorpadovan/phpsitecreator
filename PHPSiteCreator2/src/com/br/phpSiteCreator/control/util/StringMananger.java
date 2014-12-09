package com.br.phpSiteCreator.control.util;

public class StringMananger {

	private StringMananger() {
	}

	public static final String capitalize(String s)
	{
		String[] array = s.split("_");
		String resultado = "";
		for(String a : array)
		{
			resultado +=  a.substring(0, 1).toUpperCase()+a.substring(1);
			s = resultado;
		}
		return s;
	}
}