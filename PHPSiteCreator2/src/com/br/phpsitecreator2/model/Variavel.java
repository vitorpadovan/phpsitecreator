package com.br.phpsitecreator2.model;

/**
 * 
 * @author vitor.padovan89@gmail.com Respons�vel por representar uma variavel e
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
	 * Tipos de Vari�veis
	 */

	/**
	 * Representa n�meros inteiros Ex. no MySql "INT"
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
	 * Representa o sexo com uma �nica letra. Ex.: M,F
	 */
	public static final int SEXO = 6;

	/**
	 * Representa telefone no formato +xx (xx) xxxx-xxxx
	 */
	public static final int TELEFONE = 7;

	/**
	 * Representa documento
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
	 * Representa uma frase com tamanho. Ex. de variavel MySql "VARCHAR", o mesmo que Variavel.FRASE;
	 */
	public static final int STRING = 10;

	/**
	 * Representa verdadeiro ou falso Ex. de variavel MySql "bool"
	 */
	public static final int BOOLEANA = 11;

	/*
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * ****************************************************************************************************
	 * Vari�veis
	 */

	/**
	 * Mostra se � uma chave prim�ria
	 */
	private boolean chave;

	/**
	 * Nome da variavel
	 */
	private String nome;

	/**
	 * Tipo da variavel;
	 */
	private int tipo = Variavel.FRASE;

	/**
	 * Tamanho da variavel padr�o � 100
	 */
	private int tamanho = 100;

	/**
	 * Valor padr�o inicial da variavel
	 */
	private String defaultValue = null;

	/**
	 * Mostra quando a variavel � visivel por padr�o em um campo do tipo
	 * ComboBox
	 */
	private boolean visibilidadeCmb = false;

	/**
	 * Mostra quando a variavel � visivel por padr�o em um campo do tipo
	 * CheckBox
	 */
	private boolean visibilidadeChk = false;

	/**
	 * Mostra quando a variavel � visivel por padr�o em um campo do tipo Table
	 */
	private boolean visibilidadeTbl = false;

	/**
	 * Mostra quando a variavel � requerida
	 */
	private boolean requerido = false;

	/**
	 * Mostra quando a variavel possui o atributo auto_increment no banco de dados
	 */
	private boolean autoIncrement = false;

	/**
	 * Chave estrangeira que a variavel est� relacionada
	 */
	private Variavel chaveEstrangeira = null;

	/**
	 * Contem a descri��o da variavel
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
	 * Coloca a descri��o da variavel, tamb�m ser� usada na hora de montar o banco de dados
	 * @param descricao
	 * Descri��o da variavel
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	
	/**
	 * Retorna a descri��o da variavel
	 * @return
	 * Descri��o da variavel
	 */
	public String getDescricao()
	{
		return this.descricao;
	}
	
	/**
	 * Adiciona uma classe cujo qual ser� a referencia desta chage estrangeira
	 * @param chaveEstrangeira
	 * Classe � qual a chave est� relacionada
	 */
	public void addChaveEstrangeira(Variavel chaveEstrangeira)
	{
		if(chaveEstrangeira != null)
		{
			this.chaveEstrangeira = chaveEstrangeira;
		}
	}
	
	/**
	 * Retorna a classe � ser referenciada pela classe estrangeira
	 * @return
	 * Retorna uma classe, caso contr�rio retorna NULL
	 */
	public Variavel getChaveEstrangeira()
	{
		return this.chaveEstrangeira;
	}
	

	/**
	 * @return Nome da vari�vel
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            O Nome da vari�vel
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Tipo da Vari�vel
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            Tipo da Vari�vel, s� aceita os tipos que est�o est�ticos nesta
	 *            classe, do contr�rio ir� colocar como padr�o o tipo Frase
	 */
	public void setTipo(int tipo) {
		tipo = Variavel.validarTipo(tipo);
	}

	/**
	 * @return Tamanho da Vari�vel
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * @param tamanho
	 *            Tamanho da Vari�vel
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * @return the Valor padr�o da vari�vel
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param default_value
	 *            Valor padr�o da vari�vel
	 */
	public void setDefaultValue(String default_value) {
		this.defaultValue = default_value;
	}

	/**
	 * @return Visibilidade quando a vari�vel apresentada em um ComboBox, caso
	 *         true ela ser� mostrada
	 */
	public boolean isVisibilidadeCmb() {
		return visibilidadeCmb;
	}

	/**
	 * @param visibilidadeCmb
	 *            Visibilidade quando a vari�vel apresentada em um ComboBox,
	 *            caso true ela ser� mostrada
	 */
	public void setVisibilidadeCmb(boolean visibilidadeCmb) {
		this.visibilidadeCmb = visibilidadeCmb;
	}

	/**
	 * @return Visibilidade quando a vari�vel apresentada em um CheckBox, caso
	 *         true ela ser� mostrada
	 */
	public boolean isVisibilidadeChk() {
		return visibilidadeChk;
	}

	/**
	 * @param visibilidadeChk
	 *            Visibilidade quando a vari�vel apresentada em um CheckBox,
	 *            caso true ela ser� mostrada
	 */
	public void setVisibilidadeChk(boolean visibilidadeChk) {
		this.visibilidadeChk = visibilidadeChk;
	}

	/**
	 * @return Visibilidade quando a vari�vel apresentada em uma Table, caso
	 *         true ela ser� mostrada
	 */
	public boolean isVisibilidadeTbl() {
		return visibilidadeTbl;
	}

	/**
	 * @param visibilidadeTbl
	 *            Visibilidade quando a vari�vel apresentada em uma Table, caso
	 *            true ela ser� mostrada
	 */
	public void setVisibilidadeTbl(boolean visibilidadeTbl) {
		this.visibilidadeTbl = visibilidadeTbl;
	}

	/**
	 * @return True se for chave prim�ria, false se n�o for chave prim�ria
	 */
	public boolean isChave() {
		return chave;
	}

	/**
	 * @param chave
	 *            True se for chave prim�ria, false se n�o for chave prim�ria
	 */
	public void setChave(boolean chave) {
		this.chave = chave;
		if(chave)
		{	
			this.setRequerido(chave);
			this.setAutoIncrement(chave);
		}
	}

	/**
	 * @return the requerido
	 */
	public boolean isRequerido() {
		return requerido;
	}

	/**
	 * @param requerido the requerido to set
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
	 * @param autoIncrement s� pode ser TRUE quando a tipo da Variavel � Variavel.INTEIRO do contr�rio ela sempre ser� FALSE
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		if(this.tipo == Variavel.INTEIRO)
		{
			this.autoIncrement = autoIncrement;
		}
		else
		{
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
	public Variavel(String nome, int tipo,String descricao) {
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
		switch (tipo) {
		case Variavel.DATA:
			break;
		case Variavel.DATA_HORARIO:
			break;
		case Variavel.DINHEIRO:
			break;
		case Variavel.DOCUMENTO:
			break;
		case Variavel.EMAIL:
			break;
		case Variavel.FLOAT:
			break;
		case Variavel.FRASE:
			break;
		case Variavel.INTEIRO:
			break;
		case Variavel.SEXO:
			break;
		case Variavel.TELEFONE:
			break;
		case Variavel.TEXTO:
			break;
		default:
			tipo = Variavel.FRASE;
		}
		return tipo;
	}
}
