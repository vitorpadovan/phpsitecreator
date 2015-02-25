/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.io.File;

import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Tipo;
import com.br.PHPSiteCreator.model.Variavel;
import com.br.PHPSiteCreator.util.Debug;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class PHPDatabase extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	public PHPDatabase(Classe classe) {
		super(classe, "system" + File.separator + "database", "DB_",
				".class.php");
		// NOT_USABLE não precisa disto
	}
	
	@Override
	protected void abrirArquivo()
	{
		arquivo.addLinha("<?php");
		arquivo.addLinha("require_once('MySql.class.php');",1);
		arquivo.addLinha("class "+this.iniciaisConstrutor+this.classe.getNome()+ " extends MySql",1);
		arquivo.addLinha("{",1);
		Debug.m("Abrindo um arquivo");
	}
	
	@Override
	protected void construtor() {
		arquivo.addLinha("");
		arquivo.addLinha("public function __construct()",2);
		arquivo.addLinha("{",2);
			arquivo.addLinha("Debug::m(\"Construindo a classe "+this.iniciaisConstrutor+classe.getNome()+"\",\"h1\");",3);
			arquivo.addLinha("parent::__construct();",3);
		arquivo.addLinha("}",2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#variaveis()
	 */
	@Override
	public void variaveis() {
		// NOT_USABLE não precisa disto
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.br.PHPSiteCreator.control.builders.ConstrutorBasico#getsAndSets()
	 */
	@Override
	public void getsAndSets() {
		// NOT_USABLE não precisa disto
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#corpo()
	 */
	@Override
	public void corpo() {
		this.extrairItem();
		this.salvarDB();
		this.excluirDB();
		this.atualizarDB();
		this.pesquisaPorId();
		this.lista();
		this.pesquisasDB();
		this.getBackup();
		this.getView();
	}
	
	private void getBackup()
	{
		this.iniciarFuncao("getBackup");
			arquivo.addLinha("$d = new DB_"+classe.getNome()+"();",3);
			arquivo.addLinha("$l = $d->getLista(\"cod\");",3);
			arquivo.addLinha("$r = \"\";",3);
			arquivo.addLinha("for($i = 0;$i<$l->getSize();$i++)",3);
			arquivo.addLinha("{",3);
				arquivo.addLinha("$t = $l->get($i);",4);
				arquivo.addLinha("$sql = new DML_SQL(\""+classe.getNome()+"\");",4);
				arquivo.addLinha("$sql->addInsert(\""+classe.getChavePrimaria().getNome()+"\",$t->get"+capitalize(classe.getChavePrimaria().getNome())+"());",4);
				for(Variavel v : classe.getVariaveis())
				{
					arquivo.addLinha("$sql->addInsert(\""+v.getNome()+"\",$t->get"+capitalize(v.getNome())+"());",4);
				}
			arquivo.addLinha("$r .= $sql->getInsert().\"<br />\";",4);
			arquivo.addLinha("}",3);
			arquivo.addLinha("return $r;",3);
		this.finalizarFuncao();
	}
	
	private String tratarTipo(Variavel v)
	{
		switch(v.getTipo())
		{
		case Tipo.DATE:
			break;
		case Tipo.DATETIME:
			break;
		case Tipo.DINHEIRO:
			break;
		case Tipo.EMAIL:
			break;
		case Tipo.INT:
			return ",DML_SQL::Numeric()";
		case Tipo.TEXTO:
			break;
		case Tipo.VARCHAR:
			break;
		}
		return "";
	}

	private void salvarDB() {
		this.addParametroFuncao("" + this.classe.getNome());
		this.iniciarFuncao("salvar_db");
		this.arquivo.addLinha("if($" + this.classe.getNome() + " instanceof "
				+ this.classe.getNome() + ")", 3);
		this.arquivo.addLinha("{", 3);
		for (Variavel v : this.classe.getVariaveis()) {
			if(v.isRequerido())
			{		
				this.arquivo.addLinha("if($"+classe.getNome()+"->get"+capitalize(v.getNome())+"() === NULL)", 4);
				this.arquivo.addLinha("{",4);
					this.arquivo.addLinha("return false;",5);
				this.arquivo.addLinha("}",4);
			}
		}
		this.arquivo.addLinha("$sql = new DML_SQL(\""+this.classe.getNome()+"\");",4);
		for (Variavel v : this.classe.getVariaveis()) {
			
			this.arquivo.addLinha("$sql->addInsert(\""+v.getNome()+"\",$"+classe.getNome()+"->get"+capitalize(v.getNome())+"());",4);
		}
		this.arquivo.addLinha("$string_sql = $sql->getInsert();",4);
		this.arquivo.addLinha("return $this->inserir($string_sql);",4);
		this.arquivo.addLinha("}", 3);
		this.arquivo.addLinha("return false;",3);
		this.finalizarFuncao();
	}

	private void excluirDB() {
		// THINK
		this.iniciarFuncao("excluir_db");
		this.finalizarFuncao();
	}

	private void atualizarDB() {
		// THINK
		this.iniciarFuncao("atualizar_db");
		this.finalizarFuncao();
	}
	
	private void lista()
	{
		this.iniciarFuncao("getLista");
		arquivo.addLinha("$query = \"select * from "+classe.getNome()+"\";",3);
		arquivo.addLinha("return $this->pesquisa($query);",3);
		this.finalizarFuncao();
	}

	
	private void getView()
	{
		if(classe.isChaveEstrangeira())
		{
			iniciarFuncao("getView");
			arquivo.addLinha("$query = \"select * from view_"+classe.getNome()+"\";",3);
			arquivo.addLinha("return $this->pesquisa($query);",3);
			finalizarFuncao();
		}	
	}
	
	private void pesquisaPorId()
	{
		this.addParametroFuncao(classe.getChavePrimaria().getNome());
		this.iniciarFuncao("getListaPor"+this.capitalize(classe.getChavePrimaria().getNome()));
		arquivo.addLinha("$query = \"select * from "+classe.getNome()+" where "+classe.getChavePrimaria().getNome()+" = \".$"+classe.getChavePrimaria().getNome()+";",3);
		arquivo.addLinha("return $this->pesquisa($query);",3);
		this.finalizarFuncao();
	}

	private void pesquisasDB() {
		for (Variavel v : this.classe.getVariaveis()) {
			if (v.isUnico()) {
				this.addParametroFuncao(v.getNome());
				this.iniciarFuncao("pesquisarPor"
						+ this.capitalize(v.getNome()));
					this.arquivo.addLinha("$query = \"select * from "+this.classe.getNome()+" where "+v.getNome()+" = '\".$"+v.getNome()+".\"';\";",3);
					this.arquivo.addLinha("return $this->pesquisa($query);",3);
				this.finalizarFuncao();
			}
		}

	}
	
	private void extrairItem()
	{
		this.addParametroFuncao("obj");
		this.iniciarFuncao("extrair_item");
			arquivo.addLinha("$resultado = new "+classe.getNome()+";",3);
				arquivo.addLinha("$resultado->set"+capitalize(classe.getChavePrimaria().getNome())+"($obj->"+classe.getChavePrimaria().getNome()+");",4);
			for(Variavel v : classe.getVariaveis())
			{
				arquivo.addLinha("$resultado->set"+this.capitalize(v.getNome())+"($obj->"+v.getNome()+");",4);
			}
			arquivo.addLinha("return $resultado;",3);
			this.finalizarFuncao();
	}

}
