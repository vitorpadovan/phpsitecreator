package com.br.PHPSiteCreator.control.builders;

import com.br.PHPSiteCreator.model.Arquivo;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.util.Debug;

public abstract class ConstrutorBasico {

	protected Arquivo arquivo;
	protected Classe classe;
	protected String pastaConstrutor;
	protected String iniciaisConstrutor;
	protected String extensao;
	protected int indentacaoInicial;
	
	

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 * @param extensao
	 */
	protected ConstrutorBasico(Classe classe, String pastaConstrutor,
			String iniciaisConstrutor, String extensao) {
		super();
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

	private void abrirArquivo() {
		arquivo.addLinha("<?php");
		arquivo.addLinha("class "+classe.getNome(),1);
		arquivo.addLinha("{",1);
		Debug.m("Abrindo um arquivo");
	}

	private void fecharArquivo() {
		arquivo.addLinha("}",1);
		arquivo.addLinha("?>");
		Debug.m("Fechando um arquivo");
		arquivo.gravar();
	}

	private void construtor() {
		arquivo.addLinha("");
		arquivo.addLinha("public function __construct()",2);
		arquivo.addLinha("{",2);
			arquivo.addLinha("#Debug::m(\"Construindo a classe "+classe.getNome()+"\");",3);
			//TODO Remover comentário quando a classe Debug ficar pronta;
		arquivo.addLinha("}",2);
	}

	private void destruidor() {
		arquivo.addLinha("");
		arquivo.addLinha("public function __destruct()",2);
		arquivo.addLinha("{",2);
			arquivo.addLinha("#Debug::m(\"Destruindo a classe "+classe.getNome()+"\");",3);
			//TODO Remover comentário quando a classe Debug ficar pronta;
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
	
	protected void iniciarFuncao(String nome)
	{
		arquivo.addLinha("");
		arquivo.addLinha("public function "+nome+"()",2);
		arquivo.addLinha("{",2);
		
	}
	
	protected void finalizarFuncao()
	{
		arquivo.addLinha("}",2);
	}
	
	public abstract void getsAndSets();
	

	public abstract void corpo();

}
