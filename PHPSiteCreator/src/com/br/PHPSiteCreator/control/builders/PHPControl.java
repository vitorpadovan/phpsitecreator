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
public class PHPControl extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	public PHPControl(Classe classe) {
		super(classe,"system"+File.separator+"control","CTRL_",".class.php");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void variaveis() {
		// TODO Auto-generated method stub
		
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
