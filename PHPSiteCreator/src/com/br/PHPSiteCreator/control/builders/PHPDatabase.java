/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.io.File;

import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Variavel;

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
		this.salvarDB();
		this.excluirDB();
		this.atualizarDB();
		this.pesquisaPorId();
		this.lista();
		this.pesquisasDB();
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
				this.arquivo.addLinha("if($"+v.getNome()+" === NULL)", 4);
				this.arquivo.addLinha("{",4);
					this.arquivo.addLinha("return false;",5);
				this.arquivo.addLinha("}",4);
				
				
			}
		}
		this.arquivo.addLinha("$sql = new DML_SQL(\""+this.classe.getNome()+"\");",4);
		for (Variavel v : this.classe.getVariaveis()) {
			
			this.arquivo.addLinha("$sql->addInsert(\""+v.getNome()+"\",$"+v.getNome()+")",4);
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

}
