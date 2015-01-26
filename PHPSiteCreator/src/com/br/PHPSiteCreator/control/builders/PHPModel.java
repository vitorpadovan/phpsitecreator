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
		for(Variavel var : this.classe.getVariaveis())
		{
			this.addParametroFuncao(var.getNome());
			this.iniciarFuncao("set"+this.capitalize(var.getNome()));
			//REMOVE Debug arquivo.addLinha("#Debug::m(\"set do "+this.capitalize(var.getNome())+"\");",3);
			arquivo.addLinha("$this->"+var.getNome()+" = $"+var.getNome()+";",3);
			this.finalizarFuncao();
			
			this.iniciarFuncao("get"+this.capitalize(var.getNome()));
			//REMOVE Debug arquivo.addLinha("#Debug::m(\"get do "+this.capitalize(var.getNome())+"\");",3);
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
				//TODO terminar - Pensar em como tratar os tipos usando tiparVariavel(Variavel var);
				arquivo.addLinha("$s .= \""+v.getNome()+"\":;",3);
			}
		this.finalizarFuncao();
	}
	
	private void setAll()
	{
		for(Variavel v : this.classe.getVariaveis())
		{
			this.addParametroFuncao(v.getNome());
		}
		this.iniciarFuncao("setAll");
		//REMOVE Debug arquivo.addLinha("#Debug::m(\"setAll do "+classe.getNome()+"\");",3);
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
		//REMOVE Debug arquivo.addLinha("#Debug::m(\"setRequired do "+classe.getNome()+"\");",3);
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
