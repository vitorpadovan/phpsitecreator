/**
 * 
 */
package com.br.PHPSiteCreator.model;

import java.util.List;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Variavel {

	private String nome;
	private int tipo;
	private int tamanho;
	private boolean requerido;
	private ChaveEstrangeira chaveEstrangeira;
	private String descricao;
	private int modificador;
	private boolean estatico;

	/**
	 * @param nome
	 * @param tipo
	 * @param tamanho
	 * @param requerido
	 * @param chaveEstrangeira
	 * @param descricao
	 */
	public Variavel(String nome, int tipo, int tamanho, boolean requerido,
			ChaveEstrangeira chaveEstrangeira, String descricao) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.requerido = requerido;
		this.chaveEstrangeira = chaveEstrangeira;
		this.descricao = descricao;
		this.modificador = Modificador.PRIVADO;
		this.estatico = false;
	}

	/**
	 * @return the estatico
	 */
	public boolean isEstatico() {
		return estatico;
	}

	/**
	 * @param estatico
	 *            the estatico to set
	 */
	public void setEstatico(boolean estatico) {
		this.estatico = estatico;
	}

	/**
	 * @return the modificador
	 */
	public int getModificador() {
		return modificador;
	}

	/**
	 * @param modificador
	 *            the modificador to set
	 */
	public void setModificador(int modificador) {
		this.modificador = modificador;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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
		this.tipo = tipo;
	}

	/**
	 * @return the tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho
	 *            the tamanho to set
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the requerido
	 */
	public boolean isRequerido() {
		return requerido;
	}

	/**
	 * @param requerido
	 *            the requerido to set
	 */
	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	/**
	 * @return the chaveEstrangeira
	 */
	public ChaveEstrangeira getChaveEstrangeira() {
		return chaveEstrangeira;
	}

	/**
	 * @param chaveEstrangeira
	 *            the chaveEstrangeira to set
	 */
	public void setChaveEstrangeira(ChaveEstrangeira chaveEstrangeira) {
		this.chaveEstrangeira = chaveEstrangeira;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
