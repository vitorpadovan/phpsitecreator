/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.io.File;

import com.br.PHPSiteCreator.model.Classe;

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
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#variaveis()
	 */
	@Override
	public void variaveis() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#getsAndSets()
	 */
	@Override
	public void getsAndSets() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#corpo()
	 */
	@Override
	public void corpo() {
		// TODO Auto-generated method stub

	}

}
