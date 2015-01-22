/**
 * 
 */
package com.br.PHPSiteCreator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Classe {

	private String nome;
	private List<Variavel> variaveis;
	private ChavePrimaria chavePrimaria;

	public Classe(String nome) {
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
		this.variaveis.add(var);
	}

	/**
	 * @return the variaveis
	 */
	public List<Variavel> getVariaveis() {
		return variaveis;
	}
	
	public void addChave(Variavel var)
	{
		if(chavePrimaria == null)
		{
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
	
	public Variavel getChavePrimaria()
	{
		return chavePrimaria.getChavePrimaria().get(0);
	}

	/**
	 * @param chavePrimaria the chavePrimaria to set
	 */
	public void setChavePrimaria(ChavePrimaria chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}
	
	

}
