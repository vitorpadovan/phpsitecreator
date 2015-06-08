package com.br.phpsitecreator2.model;

public class Modificador {
	
	public final static int PRIVADO = 1;
	public final static int PUBLICO = 2;
	public final static int PROTEGIDO = 3;
	
	private int modificador;
	
	public Modificador(int modificador)
	{
		switch(modificador)
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			modificador = 2;
			break;
		}
		
		this.modificador = modificador;
	}
	
	public String getModificador()
	{
		switch(modificador)
		{
		case 1:
			return "private";
			
		case 2:
			return "public";
		case 3:
			return "protegido";
		}
		return "public";
	}

}
