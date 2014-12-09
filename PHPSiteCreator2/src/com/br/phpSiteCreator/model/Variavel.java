package com.br.phpSiteCreator.model;

public class Variavel {

	private String nome;
	private int tipo;
	private int tamanho;
	private boolean requerido;
	private Classe chaveEstrangeira;
	private boolean existeChaveEstrangeira;

	public Variavel(String nome, int tipo) {
		super();
		this.nome = nome;
		this.setTamanho(250);
		this.setTipo(tipo);
	}

	public Variavel(String nome, int tipo, int tamanho) {
		super();
		this.nome = nome;
		this.setTipo(tipo);
		this.tamanho = tamanho;
		this.requerido = false;
		this.chaveEstrangeira = null;
	}

	public Variavel(String nome, int tipo, int tamanho, boolean requerido) {
		super();
		this.nome = nome;
		this.setTipo(tipo);
		this.tamanho = tamanho;
		this.requerido = requerido;
		this.chaveEstrangeira = null;
	}

	public Variavel(String nome, int tipo, boolean requerido) {
		super();
		this.setTamanho(250);
		this.nome = nome;
		this.setTipo(tipo);
		this.requerido = requerido;
		this.chaveEstrangeira = null;
	}

	public Classe getChaveEstrangeira() {
		return chaveEstrangeira;
	}

	public void setChaveEstrangeira(Classe chaveEstrangeira) {
		if (existeChaveEstrangeira && chaveEstrangeira != null) {
			return;
		}
		this.chaveEstrangeira = chaveEstrangeira;
		if (this.chaveEstrangeira != null) {
			this.existeChaveEstrangeira = true;
		}

	}

	public boolean isExisteChaveEstrangeira() {
		return existeChaveEstrangeira;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public boolean isRequerido() {
		return requerido;
	}

	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

}
