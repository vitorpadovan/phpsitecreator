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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void variaveis() {
		if(classe.getChavePrimaria() != null)
		{
			for(Variavel var : classe.getChavePrimaria().getChavePrimaria())
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void corpo() {
		// TODO Auto-generated method stub
		
	}

}
