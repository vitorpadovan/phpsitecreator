/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.io.File;

import com.br.PHPSiteCreator.model.ChavePrimaria;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Tipo;
import com.br.PHPSiteCreator.model.Variavel;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class PHPModel extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	public PHPModel(Classe classe) {
		super(classe,"system"+File.separator+"model","",".class.php");
		//NOT_USABLE não precisa disto
	}

	@Override
	public void variaveis() {
		if(classe.getChavesPrimarias() != null)
		{
			for(Variavel var : classe.getChavesPrimarias().getChavePrimaria())
			{
				arquivo.addLinha("private $"+var.getNome()+";",2);
			}
		}
		
		for(Variavel var : classe.getVariaveis())
		{
			arquivo.addLinha("private $"+var.getNome()+";",2);
		}
	}

	@Override
	public void getsAndSets() {
		Variavel ch = classe.getChavePrimaria();
		this.addParametroFuncao(ch.getNome());
		this.iniciarFuncao("set"+this.capitalize(ch.getNome()));
		arquivo.addLinha("#Debug::m(\"set do "+this.capitalize(ch.getNome())+"\");",3);
		arquivo.addLinha("$this->"+ch.getNome()+" = $"+ch.getNome()+";",3);
		this.finalizarFuncao();
		
		this.iniciarFuncao("get"+this.capitalize(ch.getNome()));
		arquivo.addLinha("#Debug::m(\"get do "+this.capitalize(ch.getNome())+"\");",3);
		arquivo.addLinha("return $this->"+ch.getNome()+";",3);
		this.finalizarFuncao();
		
		for(Variavel var : this.classe.getVariaveis())
		{
			this.addParametroFuncao(var.getNome());
			this.iniciarFuncao("set"+this.capitalize(var.getNome()));
			arquivo.addLinha("#Debug::m(\"set do "+this.capitalize(var.getNome())+"\");",3);
			arquivo.addLinha("$this->"+var.getNome()+" = $"+var.getNome()+";",3);
			this.finalizarFuncao();
			
			this.iniciarFuncao("get"+this.capitalize(var.getNome()));
			arquivo.addLinha("#Debug::m(\"get do "+this.capitalize(var.getNome())+"\");",3);
			arquivo.addLinha("return $this->"+var.getNome()+";",3);
			this.finalizarFuncao();
		}
	}

	@Override
	public void corpo() {
		this.JSON();
		this.setAll();
		this.setRequired();
		
	}
	
	private void JSON()
	{
		this.iniciarFuncao("getJSON");
			for(Variavel v: classe.getVariaveis())
			{
				arquivo.addLinha("$s .= '\""+v.getNome()+"\":"+variavelJSON(v)+"';",3);
			}
		this.finalizarFuncao();
	}
	
	private String variavelJSON(Variavel var)
	{
		switch(var.getTipo())
		{
		case Tipo.DATE:
			return "\""+var.getNome()+"\"";
		case Tipo.DATETIME:
			return "\""+var.getNome()+"\"";
		case Tipo.DINHEIRO:
			return var.getNome();
		case Tipo.EMAIL:
			return "\""+var.getNome()+"\"";
		case Tipo.TEXTO:
			return "\""+var.getNome()+"\"";
		case Tipo.VARCHAR:
			return "\""+var.getNome()+"\"";
		case Tipo.INT:
			return var.getNome();
		default:
			return var.getNome();
		}
	}
	
	private void setAll()
	{
		//TODO adicionar as chaves estrangeiras
		for(Variavel v : this.classe.getVariaveis())
		{
			this.addParametroFuncao(v.getNome());
		}
		this.iniciarFuncao("setAll");
		arquivo.addLinha("#Debug::m(\"setAll do "+classe.getNome()+"\");",3);
		for(Variavel v : this.classe.getVariaveis())
		{	
			arquivo.addLinha("$this->"+v.getNome()+" = $"+v.getNome()+";",3);
		}
		this.finalizarFuncao();
	}
	
	private void setRequired()
	{
		for(Variavel v : this.classe.getVariaveis())
		{
			if(v.isRequerido())
			{
				this.addParametroFuncao(v.getNome());
			}
		}
		this.iniciarFuncao("setRequired");
		arquivo.addLinha("#Debug::m(\"setRequired do "+classe.getNome()+"\");",3);
		for(Variavel v : this.classe.getVariaveis())
		{
			if(v.isRequerido())
			{
				arquivo.addLinha("$this->"+v.getNome()+" = $"+v.getNome()+";",3);
			}
		}
		this.finalizarFuncao();
	}

}
