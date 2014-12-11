package com.br.PHPSiteCreator.control.builders;

import com.br.PHPSiteCreator.model.Arquivo;
import com.br.PHPSiteCreator.model.Classe;

public abstract class ConstrutorBasico {

	protected Arquivo arquivo;
	protected Classe classe;
	protected String pastaConstrutor;
	protected String iniciaisConstrutor;

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	protected ConstrutorBasico(Classe classe, String pastaConstrutor,
			String iniciaisConstrutor) {
		super();
		this.classe = classe;
		this.pastaConstrutor = pastaConstrutor;
		this.iniciaisConstrutor = iniciaisConstrutor;
		this.iniciar();
		//TODO pensar melhor em como implementar isso.
	}

	private void iniciar() {
		this.abrirArquivo();
		this.construtor();
		this.corpo();
		this.destruidor();
		this.fecharArquivo();
	}

	private void abrirArquivo() {

	}

	private void fecharArquivo() {
		
		arquivo.gravar();
	}

	private void construtor() {

	}

	private void destruidor() {

	}
	
	public abstract void variaveis();
	
	public abstract void getsAndSets();

	public abstract void corpo();

}
