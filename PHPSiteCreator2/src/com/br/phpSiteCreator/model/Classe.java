package com.br.phpSiteCreator.model;

import java.util.ArrayList;
import java.util.List;

public class Classe {

	private List<Variavel> variaveis;
	private String nome;
	private Variavel chavePrimaria;
	private boolean existeReferencia;
	private List<Classe> referencias;

	public Classe(String nome) {
		// TODO Auto-generated constructor stub
		this.variaveis = new ArrayList<Variavel>();
		this.referencias = new ArrayList<Classe>();
		this.nome = nome;
	}

	public void add(Variavel var) {
		if (var.isExisteChaveEstrangeira()) {
			this.existeReferencia = true;
			this.referencias.add(var.getChaveEstrangeira());
		}
		this.variaveis.add(var);
	}

	public List<Classe> getReferencias() {
		return referencias;
	}

	public boolean isExisteReferencia() {
		return existeReferencia;
	}

	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public String getNome() {
		return nome;
	}

	public Variavel getChavePrimaria() {
		return chavePrimaria;
	}

	public void setChavePrimaria(Variavel chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}

}
