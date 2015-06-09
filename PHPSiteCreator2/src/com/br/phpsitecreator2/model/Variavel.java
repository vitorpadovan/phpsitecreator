package com.br.phpsitecreator2.model;

import java.text.Normalizer;

import com.br.phpsitecreator2.util.Debug;

/**
 * 
 * @author vitor.padovan89@gmail.com Responsável por representar uma variavel e
 *         suas propriedades
 */
public class Variavel {

	/*
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * Tipos de Variáveis
	 */

	/**
	 * Representa números inteiros Ex. no MySql "INT"
	 */
	public static final int INTEIRO = 0;

	/**
	 * Representa ponto flutuante Ex. no MySql "FLOAT"
	 */
	public static final int FLOAT = 1;

	/**
	 * Representa moedas
	 */
	public static final int DINHEIRO = 2;

	/**
	 * Representa e-mail
	 */
	public static final int EMAIL = 3;

	/**
	 * Representa data ex.: 25/07/2015
	 */
	public static final int DATA = 4;

	/**
	 * Representa data e hora ex.: 25/07/2015 08:00
	 */
	public static final int DATA_HORARIO = 5;

	/**
	 * Representa o sexo com uma única letra. Ex.: M,F
	 */
	public static final int SEXO = 6;

	/**
	 * Representa telefone no formato +xx (xx) xxxx-xxxx
	 */
	public static final int TELEFONE = 7;

	/**
	 * Representa documento de uma pessoa
	 */
	public static final int DOCUMENTO = 8;

	/**
	 * Representa um texto grande, sem tamanho ex. de Variavel MySql "TEXT"
	 */
	public static final int TEXTO = 9;

	/**
	 * Representa uma frase com tamanho. Ex. de variavel MySql "VARCHAR";
	 */
	public static final int FRASE = 10;

	/**
	 * Representa uma frase com tamanho. Ex. de variavel MySql "VARCHAR", o
	 * mesmo que Variavel.FRASE;
	 */
	public static final int STRING = 11;

	/**
	 * Representa verdadeiro ou falso Ex. de variavel MySql "bool"
	 */
	public static final int BOOLEANA = 12;

	/**
	 * Representa um arquivo de computador
	 */
	public static final int ARQUIVO = 13;

	/**
	 * Representa um link de um site
	 */
	public static final int LINK = 14;

	/*
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * Variáveis
	 */

	/**
	 * Mostra se é uma chave primária
	 */
	private boolean chave = false;

	/**
	 * Mostra se é um indice
	 */
	private boolean indice = false;

	/**
	 * Nome da variavel
	 */
	private String nome = null;

	/**
	 * Tipo da variavel;
	 */
	private int tipo = Variavel.FRASE;

	/**
	 * Tamanho da variavel padrão é 100
	 */
	private int tamanho = 100;

	/**
	 * Valor padrão inicial da variavel
	 */
	private String defaultValue = null;

	/**
	 * Mostra quando a variavel é visivel por padrão em um campo do tipo
	 * ComboBox
	 */
	private boolean visibilidadeCmb = false;

	/**
	 * Mostra quando a variavel é visivel por padrão em um campo do tipo
	 * CheckBox
	 */
	private boolean visibilidadeChk = false;

	/**
	 * Mostra quando a variavel é visivel por padrão em um campo do tipo Table
	 */
	private boolean visibilidadeTbl = false;

	/**
	 * Mostra quando a variavel é requerida
	 */
	private boolean requerido = false;

	/**
	 * Mostra quando a variavel deve ser única
	 */
	private boolean unico = false;

	/**
	 * Mostra quando a variavel possui o atributo auto_increment no banco de
	 * dados
	 */
	private boolean autoIncrement = false;

	/**
	 * Chave estrangeira que a variavel está relacionada
	 */
	private Classe chaveEstrangeira = null;

	/**
	 * Contem a descrição da variavel
	 */
	private String descricao = null;

	/*
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * Getters and Setters
	 */

	/**
	 * Coloca a descrição da variavel, também será usada na hora de montar o
	 * banco de dados
	 * 
	 * @param descricao
	 *            Descrição da variavel
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a descrição da variavel
	 * 
	 * @return Descrição da variavel
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Adiciona uma classe cujo qual será a referencia desta chage estrangeira
	 * 
	 * @param chaveEstrangeira
	 *            Classe á qual a chave está relacionada
	 */
	public void addChaveEstrangeira(Classe classe) {
		if (chaveEstrangeira != null) {
			if(this.chaveEstrangeira.getChaves().get(0).getTipo() == this.tipo)
			{
				this.chaveEstrangeira = classe;
			}
			else
			{
				Debug.e("Chaves não são compatíveis");
			}
		}
		else
		{
			Debug.e("Chave estrangeira é nula");
		}
	}

	/**
	 * Retorna a classe á ser referenciada pela classe estrangeira
	 * 
	 * @return Retorna uma classe, caso contrário retorna NULL
	 */
	public Classe getChaveEstrangeira() {
		return this.chaveEstrangeira;
	}

	/**
	 * @return Nome da variável
	 */
	public String getNome() {
		return nome;
	}

	public String getNomeProgramavel() {
		String resultado = Normalizer.normalize(this.nome, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		resultado = resultado.replaceAll(" ", "_");
		resultado = resultado.toLowerCase();
		return resultado;
	}

	public String getNomeVariavelProgramavel() {
		String resultado = Normalizer.normalize(this.nome, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		resultado = resultado.replaceAll(" ", "_");
		resultado = resultado.toLowerCase();
		resultado = "$" + resultado;
		return resultado;
	}

	public String getNomePropriedade() {
		String resultado = Normalizer.normalize(this.nome, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		resultado = resultado.replaceAll(" ", "_");
		resultado = resultado.toLowerCase();
		resultado = "$this->" + resultado;
		return resultado;
	}

	/**
	 * @param nome
	 *            O Nome da variável
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Torna a variável indice ou não
	 * 
	 * @param indice
	 */
	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	/**
	 * Retorna se a variável é um indice ou não
	 * 
	 * @return
	 */
	public boolean getIndice() {
		return this.indice;
	}

	/**
	 * Mostra quando a variavel é única
	 * 
	 * @return
	 */
	public boolean isUnico() {
		return unico;
	}

	/**
	 * true deixa a variável unica
	 * 
	 * @param unico
	 */
	public void setUnico(boolean unico) {
		this.unico = unico;
	}

	/**
	 * @return Tipo da Variável
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            Tipo da Variável, só aceita os tipos que estão estáticos nesta
	 *            classe, do contrário irá colocar como padrão o tipo Frase
	 */
	public void setTipo(int tipo) {
		this.tipo = this.validarTipo(tipo);
	}

	/**
	 * @return Tamanho da Variável
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho
	 *            Tamanho da Variável
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the Valor padrão da variável
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param default_value
	 *            Valor padrão da variável
	 */
	public void setDefaultValue(String default_value) {
		this.defaultValue = default_value;
	}

	/**
	 * @return Visibilidade quando a variável apresentada em um ComboBox, caso
	 *         true ela será mostrada
	 */
	public boolean isVisibilidadeCmb() {
		return visibilidadeCmb;
	}

	/**
	 * @param visibilidadeCmb
	 *            Visibilidade quando a variável apresentada em um ComboBox,
	 *            caso true ela será mostrada
	 */
	public void setVisibilidadeCmb(boolean visibilidadeCmb) {
		this.visibilidadeCmb = visibilidadeCmb;
	}

	/**
	 * @return Visibilidade quando a variável apresentada em um CheckBox, caso
	 *         true ela será mostrada
	 */
	public boolean isVisibilidadeChk() {
		return visibilidadeChk;
	}

	/**
	 * @param visibilidadeChk
	 *            Visibilidade quando a variável apresentada em um CheckBox,
	 *            caso true ela será mostrada
	 */
	public void setVisibilidadeChk(boolean visibilidadeChk) {
		this.visibilidadeChk = visibilidadeChk;
	}

	/**
	 * @return Visibilidade quando a variável apresentada em uma Table, caso
	 *         true ela será mostrada
	 */
	public boolean isVisibilidadeTbl() {
		return visibilidadeTbl;
	}

	/**
	 * @param visibilidadeTbl
	 *            Visibilidade quando a variável apresentada em uma Table, caso
	 *            true ela será mostrada
	 */
	public void setVisibilidadeTbl(boolean visibilidadeTbl) {
		this.visibilidadeTbl = visibilidadeTbl;
	}

	/**
	 * @return True se for chave primária, false se não for chave primária
	 */
	public boolean isChave() {
		return chave;
	}

	/**
	 * @param chave
	 *            True se for chave primária, false se não for chave primária
	 */
	public void setChave(boolean chave) {
		this.chave = chave;
		if (chave) {
			this.setRequerido(chave);
			this.setAutoIncrement(chave);
		}
	}

	public void setChave() {
		this.setChave(true);
	}

	/**
	 * @return the requerido
	 */
	public boolean isRequerido() {
		return requerido;
	}

	/**
	 * @param requerido
	 *            the requerido to set
	 */
	public void setRequerido(boolean requerido) {
		this.requerido = requerido;
	}

	/**
	 * @return the autoIncrement
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * @param autoIncrement
	 *            só pode ser TRUE quando a tipo da Variavel é Variavel.INTEIRO
	 *            do contrário ela sempre será FALSE
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		if (this.tipo == Variavel.INTEIRO) {
			this.autoIncrement = autoIncrement;
		} else {
			this.autoIncrement = false;
		}
	}

	/*
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * Construtores
	 */

	/**
	 * Inicia uma variavel
	 * 
	 * @param nome
	 *            Nome da variavel
	 * @param tipo
	 *            Tipo da Variavel
	 */
	public Variavel(String nome, int tipo, String descricao) {
		setNome(nome);
		setTipo(tipo);
	}

	/*
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * Metodos variados
	 */

	public static int validarTipo(int tipo) {
		if(tipo>=0 && tipo<=14)
		{
			return tipo;
		}
		return Variavel.STRING;

	}
}
