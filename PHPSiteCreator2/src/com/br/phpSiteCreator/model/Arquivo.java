package com.br.phpSiteCreator.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.br.phpSiteCreator.control.util.Debug;

public class Arquivo {

	private String fileName;
	private String pathName;
	private File arquivo;
	private String string;;

	public Arquivo() {
		// TODO Auto-generated constructor stub
	}
	
	public void proximaLinha()
	{
		this.addLinha("");
	}

	public Arquivo(String fileName, String pathName) {
		super();
		this.fileName = fileName;
		this.pathName = pathName;
		this.arquivo = new File(pathName+File.separator+fileName);
		this.string = "";
	}
	public void iniciarPHP()
	{
		this.addLinha("<?php");
	}
	
	public void finalizarPHP()
	{
		this.addLinha("?>");
	}
	
	
	public void addFrase(String frase)
	{
		this.string += frase;
	}
	
	public void addLinha(String linha)
	{
		this.addFrase("\n"+linha);
	}
	
	public void addLinha(String linha,int tab)
	{
		this.addFrase("\n");
		for(int i = 0;i<tab;i++)
		{
			this.addFrase("\t");
		}
		this.addFrase(linha);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
	public boolean gravar()
	{
		//Debug.mensagem("Iremos gravar o seguinte arquivo\n"+this.arquivo.getAbsolutePath());
		Writer writer = null;
		
		//Debug.mensagem(this.pathName);
		File pasta = new File(this.pathName);
		if(!pasta.exists())
		{
			pasta.mkdirs();
		}

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(this.arquivo), "utf-8"));
		    writer.write(this.string);
		} catch (IOException ex) {
		  // report
			ex.printStackTrace();
			return false;
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
		return true;
	}

}
