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
	
	

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 * @param extensao
	 */
	protected ConstrutorBasico(Classe classe, String pastaConstrutor,
			String iniciaisConstrutor, String extensao) {
		super();
		this.classe = classe;
		this.pastaConstrutor = pastaConstrutor;
		this.iniciaisConstrutor = iniciaisConstrutor;
		this.extensao = extensao;
		this.arquivo = new Arquivo(iniciaisConstrutor+classe.getNome(), pastaConstrutor, extensao);
		this.iniciar();
		
	}

	private void iniciar() {
		this.abrirArquivo();
		this.construtor();
		this.corpo();
		this.destruidor();
		this.fecharArquivo();
	}

	private void abrirArquivo() {
		arquivo.addLinha("<?php");
		Debug.m("Abrindo um arquivo");
	}

	private void fecharArquivo() {
		arquivo.addLinha("?>");
		Debug.m("Fechando um arquivo");
		arquivo.gravar();
	}

	private void construtor() {
		arquivo.addLinha("public function __construct()",1);
		arquivo.addLinha("{",1);
			arquivo.addLinha("#Debug::m(\"Construindo a classe "+classe.getNome()+"\");",2);
		arquivo.addLinha("}",1);
	}

	private void destruidor() {
		arquivo.addLinha("public function __destruct()",1);
		arquivo.addLinha("{",1);
		arquivo.addLinha("#Debug::m(\"Destruindo a classe "+classe.getNome()+"\");",2);
		arquivo.addLinha("}",1);

	}
	
	public abstract void variaveis();
	
	public abstract void getsAndSets();

	public abstract void corpo();

}
