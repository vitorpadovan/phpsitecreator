package com.br.phpsitecreator2.model;

/**
 * 
 * @author vitor.padovan89@gmail.com
 * Classe responsável por representar uma classe em PHP
 */
public class Classe {

	private String nome;
	private boolean referencias = false;
	
	public Classe(String nome)
	{
		this.nome = nome;
	}
}
