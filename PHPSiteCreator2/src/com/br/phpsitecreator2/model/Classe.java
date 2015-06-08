package com.br.phpsitecreator2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.br.phpsitecreator2.util.Debug;

/**
 * 
 * @author vitor.padovan89@gmail.com
 * Classe responsável por representar uma classe em PHP
 */
public class Classe {

	private String nome;
	private List<Variavel> variaveis;
	private List<Variavel> chaves;
	private List<Variavel> chavesEstrangeiras;
	
	public Classe(String nome)
	{
		Debug.d("Criando a classe "+nome);
		this.nome = nome;
		this.variaveis = new ArrayList<Variavel>();
		this.chaves = new ArrayList<Variavel>();
		this.chavesEstrangeiras = new ArrayList<Variavel>();
		Variavel v = new Variavel("cod_"+nome.toLowerCase(),Variavel.INTEIRO,"Chave primária da class "+nome);
		v.setChave();
		this.addVariavel(v);	}
	
	/**
	 * Adiciona uma variavel á classe.
	 * Também faz o papel de verificar se a variavel é uma classe,
	 * Faz o papel de verificar se a variavel possui alguma referência com uma
	 * classe externa representando assim uma chave estrangeira
	 * @param variavel
	 */
	public void addVariavel(Variavel variavel)
	{
		Debug.d("Adicionando variavel "+variavel.getNome());
		this.variaveis.add(variavel);
		if(variavel.isChave())
		{
			this.chaves.add(variavel);
		}
		if(variavel.getChaveEstrangeira() != null)
		{
			this.chavesEstrangeiras.add(variavel);
		}
	}
	
	/**
	 * Retorna as variaveis da classe
	 * @return
	 * List<Variavel> contendo as variaveis da classe
	 */
	public List<Variavel> getVariaveis()
	{
		Debug.d("Retornando as variaveis da classe "+this.nome);
		return this.variaveis;
	}
	
	/**
	 * Retorna as chaves da classe
	 * @return
	 * List<Variavel> contendo as chaves da classe
	 */
	public List<Variavel> getChaves()
	{
		Debug.d("Retornando as chaves de "+this.nome);
		return this.chaves;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Variavel> getChavesEstrangeiras() {
		return chavesEstrangeiras;
	}

	public void setChavesEstrangeiras(List<Variavel> chavesEstrangeiras) {
		this.chavesEstrangeiras = chavesEstrangeiras;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public void setChaves(List<Variavel> chaves) {
		this.chaves = chaves;
	}
	
	
}
