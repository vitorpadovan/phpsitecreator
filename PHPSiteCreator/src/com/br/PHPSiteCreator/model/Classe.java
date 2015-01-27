/**
 * 
 */
package com.br.PHPSiteCreator.model;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.util.Debug;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Classe {

	private String nome;
	private List<Variavel> variaveis;
	private ChavePrimaria chavePrimaria;
	private boolean chaveEstrangeira;

	public Classe(String nome) {
		this.chaveEstrangeira = false;
		this.nome = nome;
		this.variaveis = new ArrayList<Variavel>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addVariavel(Variavel var) {
		if (var.getChaveEstrangeira() != null) {
			this.chaveEstrangeira = true;
		}
		this.variaveis.add(var);
	}

	/**
	 * @return the variaveis
	 */
	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void addChave(Variavel var) {
		if (chavePrimaria == null) {
			chavePrimaria = new ChavePrimaria(var);
			return;
		}
		this.chavePrimaria.addChave(var);
	}

	/**
	 * @return the chavePrimaria
	 */
	public ChavePrimaria getChavesPrimarias() {
		return chavePrimaria;
	}

	public Variavel getChavePrimaria() {
		Debug.m("Minha classe é "+this.nome);
		return chavePrimaria.getChavePrimaria().get(0);
	}

	/**
	 * @param chavePrimaria
	 *            the chavePrimaria to set
	 */
	public void setChavePrimaria(ChavePrimaria chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}

	/**
	 * @return the chaveEstrangeira
	 */
	public boolean isChaveEstrangeira() {
		return chaveEstrangeira;
	}

	/**
	 * @param chaveEstrangeira the chaveEstrangeira to set
	 */
	public void setChaveEstrangeira(boolean chaveEstrangeira) {
		this.chaveEstrangeira = chaveEstrangeira;
	}
	
	

}
