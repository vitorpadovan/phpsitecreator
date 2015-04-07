package com.br.phpsitecreator2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author vitor.padovan89@gmail.com
 * Classe respons�vel por representar uma classe em PHP
 */
public class Classe {

	private String nome;
	private List<Variavel> variaveis;
	private List<Variavel> chaves;
	private List<Variavel> chavesEstrangeiras;
	
	public Classe(String nome)
	{
		this.nome = nome;
		this.variaveis = new ArrayList<Variavel>();
		this.chaves = new ArrayList<Variavel>();
		this.chavesEstrangeiras = new ArrayList<Variavel>();
		Variavel v = new Variavel("cod_"+nome.toLowerCase(),Variavel.INTEIRO,"Chave prim�ria da class "+nome);
		v.setChave(true);
	}
	
	/**
	 * Adiciona uma variavel � classe.
	 * Tamb�m faz o papel de verificar se a variavel � uma classe,
	 * Faz o papel de verificar se a variavel possui alguma refer�ncia com uma
	 * classe externa representando assim uma chave estrangeira
	 * @param variavel
	 */
	public void addVariavel(Variavel variavel)
	{
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
		return this.variaveis;
	}
	
	/**
	 * Retorna as chaves da classe
	 * @return
	 * List<Variavel> contendo as chaves da classe
	 */
	public List<Variavel> getChaves()
	{
		return this.chaves;
	}
}
