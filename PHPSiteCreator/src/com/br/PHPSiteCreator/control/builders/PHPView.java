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
public class PHPView extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	public PHPView(Classe classe) {
		super(classe,"system"+File.separator+"view","VW_",".class.php");
		//NOT_USABLE não precisa disto
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#variaveis()
	 */
	@Override
	public void variaveis() {
		for(Variavel v: this.classe.getVariaveis() )
		{
			arquivo.addLinha("private $show_"+v.getNome()+" = false;",2);
			arquivo.addLinha("private $"+v.getNome()+"_display_name;",2);
			arquivo.addLinha("",2);
		}
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#getsAndSets()
	 */
	@Override
	public void getsAndSets() {
		for(Variavel v: this.classe.getVariaveis() )
		{
			this.addParametroFuncao(v.getNome());
			this.iniciarFuncao("set"+this.capitalize(v.getNome())+"Name");
			arquivo.addLinha("if(strlen($"+v.getNome()+")>0)",3);
			arquivo.addLinha("{",3);
			arquivo.addLinha("$this->show_"+v.getNome()+" = true;",4);
			arquivo.addLinha("$this->"+v.getNome()+"_display_name = $"+v.getNome()+";",4);
			arquivo.addLinha("}",3);
			this.finalizarFuncao();
			//arquivo.addLinha("private set"+this.capitalize(v.getNome())+"Name;",2);
		}
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#corpo()
	 */
	@Override
	public void corpo() {
		this.clearShows();
		this.comboBox();
		this.tabela();
		this.cadastro();
	}
	
	public void clearShows()
	{
		//TODO implementar
	}
	
	public void comboBox()
	{
		//TODO implementar
	}
	
	public void tabela()
	{
		
		// TODO implementar
	}
	
	public void cadastro()
	{
		// TODO implementar
	}
	

}
