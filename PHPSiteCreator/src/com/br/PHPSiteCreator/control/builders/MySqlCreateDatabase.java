/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.util.List;

import com.br.PHPSiteCreator.model.Classe;


/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class MySqlCreateDatabase {
	
	private List<Classe> classes;

	/**
	 * @param classes
	 */
	protected MySqlCreateDatabase(List<Classe> classes) {
		super();
		this.classes = classes;
	}
	
	
}
