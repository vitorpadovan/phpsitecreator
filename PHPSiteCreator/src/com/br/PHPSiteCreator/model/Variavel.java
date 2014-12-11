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
	}
	
	

}
