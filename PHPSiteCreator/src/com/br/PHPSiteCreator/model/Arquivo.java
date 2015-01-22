/**
 * 
 */
package com.br.PHPSiteCreator.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.util.Debug;

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
	private File arquivo;

	/**
	 * @param nome
	 * @param pasta
	 * @param extensao
	 */
	public Arquivo(String nome, String pasta, String extensao) {
		super();
		this.nome = nome;
		this.pasta = SiteInfo.getPastaBase()+File.separator+pasta;
		this.extensao = extensao;
		this.linhas = new ArrayList<String>();
		//String path = System.getProperty("user.home") + File.separator+"Desktop";
		String nomeCompletoArquivo = SiteInfo.getPastaBase()+File.separator+pasta+File.separator+nome+extensao;
		this.arquivo = new File(nomeCompletoArquivo);
		Debug.m("O nome completo do arquivo é: "+nomeCompletoArquivo);
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

	public boolean gravar() {
		//Debug.mensagem("Iremos gravar o seguinte arquivo\n"+this.arquivo.getAbsolutePath());
		Writer writer = null;
				
		//Debug.mensagem(this.pathName);
		File pasta = new File(this.pasta);
		if(!pasta.exists())
		{
			Debug.m("Pasta não existe "+pasta.getAbsolutePath());
			pasta.mkdirs();
		}
		else
		{
			Debug.m("Pasta existe "+pasta.getAbsolutePath());
		}

		try {
			
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.arquivo), "utf-8"));
		    for(String string : linhas)
		    {
		    	 writer.write(string);
		    }
		   
		}
			catch (IOException ex) 
		{
				//report
				Debug.e("Impossivel salvar o arquivo "+arquivo.getAbsolutePath()+"\nMensagem de erro foi: "+ex.getMessage());
				//ex.printStackTrace();
				return false;
		}
			finally 
		{
			try {writer.close();} catch (Exception ex) {}
		}
		return true;
	}
	
	public void addFrase(String frase)
	{
		this.linhas.add(frase);
	}

	public void addLinha(String linha) {
		this.linhas.add("\n");
		this.linhas.add(linha);
	}

	public void addLinha(String linha, int ident) {
		this.linhas.add("\n");
		for (int i = 0; i < ident; i++) {
			linha = "\t" + linha;
		}
		this.linhas.add(linha);
	}
	
	public void proximaLinha()
	{
		this.linhas.add("\n");
	}

}
