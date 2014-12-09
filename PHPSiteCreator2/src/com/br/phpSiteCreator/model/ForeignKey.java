package com.br.phpSiteCreator.model;

public class ForeignKey {

	private Classe classe;
	private Variavel variavel;

	public ForeignKey(Classe classe) {
		super();
		this.classe = classe;
	}

	public Classe getClasse() {
		return classe;
	}
}
