/**
 * 
 */
package com.br.PHPSiteCreator.model;

import java.util.List;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Arquivo {

	private String nome;
	private String pasta;
	private String extensao;
	private int tamanho;
	private List<String> linhas;

	/**
	 * @param nome
	 * @param pasta
	 * @param extensao
	 */
	public Arquivo(String nome, String pasta, String extensao) {
		super();
		this.nome = nome;
		this.pasta = pasta;
		this.extensao = extensao;
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
	 * @return the pasta
	 */
	public String getPasta() {
		return pasta;
	}

	/**
	 * @param pasta
	 *            the pasta to set
	 */
	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	/**
	 * @return the extensao
	 */
	public String getExtensao() {
		return extensao;
	}

	/**
	 * @param extensao
	 *            the extensao to set
	 */
	public void setExtensao(String extensao) {
		this.extensao = extensao;
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
	 * @return the linhas
	 */
	public List<String> getLinhas() {
		return linhas;
	}

	/**
	 * @param linhas
	 *            the linhas to set
	 */
	public void setLinhas(List<String> linhas) {
		this.linhas = linhas;
	}

}
