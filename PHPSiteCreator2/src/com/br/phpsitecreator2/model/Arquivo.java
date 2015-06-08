/**
 * 
 */
package com.br.phpsitecreator2.model;

/**
 * @author VitorHugo
 * Responsável por manipular arquivos, gravando-os
 */
public class Arquivo {

	private String pasta;
	private String nome;
	private String extensao;
	private String frase;
	
	public Arquivo(String pasta,String nome,String extensao)
	{
		this.frase = "";
		this.pasta = pasta;
		this.nome = nome;
		this.extensao = extensao;
	}
	
	/**
	 * Adiciona um texto na mesma linha
	 * @param frase
	 */
	public void adicionarFrase(String frase)
	{
		this.frase += frase;
	}
	
	/**
	 * Adicionar um texto na linha seguinte
	 * @param linha
	 */
	public void adicionarLinha(String linha)
	{
		this.adicionarFrase("\n"+linha);
	}
	
	/**
	 * Adicionar um texto na linha seguinte com uma tabulação
	 * @param linha
	 * @param tab
	 */
	public void adicionarLinha(String linha, int tab)
	{
		this.frase += "\n";
		for(int i = 0; i<tab;i++)
		{
			this.frase += "\t";
		}
	}
	
	/**
	 * Grava no arquivo
	 * @return
	 */
	public boolean gravar()
	{
		//TODO implementar
		return false;
	}

	/**
	 * Retorna a pasta em que se encontra o arquivo
	 * @return
	 */
	public String getPasta() {
		return pasta;
	}
	
	/**
	 * Retorna o nome do arquivo
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	
	/**
	 * Retorna a extensão do arquivo
	 * @return
	 */
	public String getExtensao() {
		return extensao;
	}

	/**
	 * Retorna o texto a ser gravado no arquivo
	 * @return
	 */
	public String getFrase() {
		return frase;
	}
	
	
}
