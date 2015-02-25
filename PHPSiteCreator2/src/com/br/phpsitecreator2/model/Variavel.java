package com.br.phpsitecreator2.model;

/**
 * 
 * @author vitor.padovan89@gmail.com
 * Responsável por representar uma variavel e suas propriedades
 */
public class Variavel {

	/**
	 * Representa números inteiros
	 */
	public static final int INTEIRO = 0;
	
	/**
	 * Representa ponto flutuante
	 */
	public static final int FLOAT = 1;
	
	/**
	 * Representa moedas
	 */
	public static final int DINHEIRO = 2;
	
	/**
	 * Representa e-mail
	 */
	public static final int EMAIL = 3;
	
	/**
	 * Representa data ex.: 25/07/2015
	 */
	public static final int DATA = 4;
	
	/**
	 * Representa data e hora ex.: 25/07/2015 08:00
	 */
	public static final int DATA_HORARIO = 5;
	
	/**
	 * Representa o sexo com uma única letra. Ex.: M,F
	 */
	public static final int SEXO = 6;
	
	/**
	 * Representa telefone no formato +xx (xx) xxxx-xxxx 
	 */
	public static final int TELEFONE = 7;
	
	/**
	 * Representa documento
	 */
	public static final int DOCUMENTO = 8;
	
	/**
	 * Representa um texto grande, sem tamanho ex. de Variavel MySql "TEXT"
	 */
	public static final int TEXTO = 9;
	
	/**
	 * Representa uma frase com tamanho. Ex. de variavel MySql "VARCHAR";
	 */
	public static final int FRASE = 10;
	
	
	/**
	 * Nome da variavel
	 */
	private String nome;
	
	/**
	 * Tipo da variavel;
	 */
	private int tipo;
	
	/**
	 * Tamanho da variavel padrão é 100
	 */
	private int tamanho = 100;
	
	/**
	 * Inicia uma variavel
	 * @param nome
	 * Nome da variavel
	 * @param tipo
	 * Tipo da Variavel
	 */
	public Variavel(String nome, int tipo)
	{
		//TODO colocar o setTipo(int tipo) para fazer o papel de deixar um tipo default
	}
}
