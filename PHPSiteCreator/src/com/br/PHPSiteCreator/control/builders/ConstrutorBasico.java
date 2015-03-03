package com.br.PHPSiteCreator.control.builders;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.model.Arquivo;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Variavel;
import com.br.PHPSiteCreator.util.Debug;

public abstract class ConstrutorBasico {

	protected Arquivo arquivo;
	protected Classe classe;
	protected String pastaConstrutor;
	protected String iniciaisConstrutor;
	protected String extensao;
	protected int indentacaoInicial;
	private List<String> parametros;
	
	

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 * @param extensao
	 */
	protected ConstrutorBasico(Classe classe, String pastaConstrutor,
			String iniciaisConstrutor, String extensao) {
		super();
		this.parametros = new ArrayList<String>();
		this.indentacaoInicial = 3;
		this.classe = classe;
		this.pastaConstrutor = pastaConstrutor;
		this.iniciaisConstrutor = iniciaisConstrutor;
		this.extensao = extensao;
		this.arquivo = new Arquivo(iniciaisConstrutor+classe.getNome(), pastaConstrutor, extensao);
		this.iniciar();
		
	}

	private void iniciar() {
		this.abrirArquivo();
		this.commentVariaveis();
		this.variaveis();
		this.construtor();
		this.commentGetsAndSets();
		this.getsAndSets();
		this.corpo();
		this.destruidor();
		this.fecharArquivo();
	}

	protected void abrirArquivo() {
		arquivo.addLinha("<?php");
		arquivo.addLinha("Debug::m(\"Classe "+this.iniciaisConstrutor+this.classe.getNome()+" carregada\");");
		arquivo.addLinha("class "+this.iniciaisConstrutor+this.classe.getNome(),1);
		arquivo.addLinha("{",1);
		Debug.m("Abrindo um arquivo");
	}

	private void fecharArquivo() {
		arquivo.addLinha("}",1);
		arquivo.addLinha("?>");
		Debug.m("Fechando um arquivo");
		arquivo.gravar();
	}

	protected void construtor() {
		arquivo.addLinha("");
		arquivo.addLinha("public function __construct()",2);
		arquivo.addLinha("{",2);
			arquivo.addLinha("Debug::m(\"Construindo a classe "+this.iniciaisConstrutor+classe.getNome()+"\",\"h1\");",3);
		arquivo.addLinha("}",2);
	}

	private void destruidor() {
		arquivo.addLinha("");
		arquivo.addLinha("public function __destruct()",2);
		arquivo.addLinha("{",2);
			arquivo.addLinha("Debug::m(\"Destruindo a classe "+this.iniciaisConstrutor+classe.getNome()+"\",\"h1\");",3);
		arquivo.addLinha("}",2);

	}
	
	public void commentVariaveis()
	{
		arquivo.addLinha("###Variaveis",2);
	}
	public abstract void variaveis();
	
	public void commentGetsAndSets()
	{
		arquivo.addLinha("###Gets & Sets",2);
	}
	
	protected String capitalize(String s)
	{
		String[] array = s.split("_");
		String resultado = "";
		for(String a : array)
		{
			resultado +=  a.substring(0, 1).toUpperCase()+a.substring(1);
			s = resultado;
		}
		return s;
	}
	
	protected void addParametroFuncao(String var)
	{
		this.parametros.add(var);
	}
	
	protected void iniciarFuncao(String nome)
	{
		arquivo.addLinha("");
		String nomeFuncao = "public function "+nome+"(";
		if(parametros.size()>0)
		{
			for(String v : parametros)
			{
				if(parametros.indexOf(v)>0)
				{
					nomeFuncao += ", ";
				}
				nomeFuncao += "$"+v;
			}
			parametros.clear();
		}
		nomeFuncao += ")";
		arquivo.addLinha(nomeFuncao,2);
		arquivo.addLinha("{",2);
		arquivo.addLinha("Debug::m('Executando a função ("+nomeFuncao+") na classe "+this.iniciaisConstrutor+classe.getNome()+"');",3);
		
	}
	
	protected void finalizarFuncao()
	{
		arquivo.addLinha("}",2);
	}
	
	public abstract void getsAndSets();
	

	public abstract void corpo();

}
