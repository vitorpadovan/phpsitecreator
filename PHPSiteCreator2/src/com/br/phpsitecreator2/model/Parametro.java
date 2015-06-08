package com.br.phpsitecreator2.model;

import java.text.Normalizer;

public class Parametro {

	private String nome;
	private String valor = "NULL";
	private int tipo;

	/**
	 * @param nome
	 * @param valor
	 * @param tipo
	 */
	public Parametro(String nome, String valor, int tipo) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.setTipo(tipo);
	}

	/**
	 * @param nome
	 * @param tipo
	 */
	public Parametro(String nome, int tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	public String getNomeVariavel() {
		String resultado = Normalizer.normalize(this.nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		resultado = resultado.replaceAll(" ", "_");
		resultado = resultado.toLowerCase();
		resultado = "$"+resultado;
		return resultado;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(int tipo) {
		tipo = Variavel.validarTipo(tipo);
	}

}
