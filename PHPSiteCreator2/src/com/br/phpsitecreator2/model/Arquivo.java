/**
 * 
 */
package com.br.phpsitecreator2.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.br.phpsitecreator2.util.Debug;


/**
 * @author VitorHugo
 * Responsável por manipular arquivos, gravando-os
 */
public class Arquivo {

	private File arquivo;
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
		String nomeCompleto = this.pasta+File.separator+this.nome+"."+this.extensao;
		this.arquivo = new File(nomeCompleto);
	}
	
	/**
	 * Adiciona um texto na mesma linha
	 * @param frase
	 */
	public void addFrase(String frase)
	{
		this.frase += frase;
	}
	
	public void addFrase(String frase,int tab)
	{
		this.frase += "\t";
		this.frase += frase;
	}
	
	/**
	 * Adicionar um texto na linha seguinte
	 * @param linha
	 */
	public void addLinha(String frase)
	{
		this.frase += "\n";
		this.frase += frase;
	}
	
	/**
	 * Adicionar um texto na linha seguinte com uma tabulação
	 * @param linha
	 * @param tab
	 */
	public void addLinha(String frase, int tab)
	{
		this.frase += "\n";
		for(int i = 0; i<tab;i++)
		{
			this.frase += "\t";
		}
		this.frase += frase;
		
	}
	
	public void addLinha()
	{
		this.frase += "\n";
	}
	
	/**
	 * Grava no arquivo
	 * @return
	 */
	public boolean gravar()
	{
		//Debug.d("Salvando "+this.frase+" no arquivo "+this.arquivo.getAbsolutePath());
		Writer writer = null;
		
		//Debug.mensagem(this.pathName);
		File pasta = new File(this.pasta);
		boolean retorno;
		if(!pasta.exists())
		{
			Debug.e("Pasta não existe "+pasta.getAbsolutePath());
			pasta.mkdirs();
		}
		else
		{
			Debug.d("Pasta existe "+pasta.getAbsolutePath());
		}

		try {
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.arquivo), "utf-8"));
		    writer.write(this.frase);
		    retorno = true;
		}
			catch (IOException ex) 
		{
				//report
				Debug.e("Impossivel salvar o arquivo "+arquivo.getAbsolutePath()+"\nMensagem de erro foi: "+ex.getMessage());
				//ex.printStackTrace();
				retorno = false;
		}
			finally 
		{
			try 
			{
				writer.close();
				retorno = true;
			} 
			catch (Exception ex) {
				Debug.e("Erro ao tentar fechar o arquivo "+this.arquivo.getAbsolutePath()+"\nMensagem de erro foi: "+ex.getMessage());
				retorno = false;
			}
		}
		return retorno;
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
