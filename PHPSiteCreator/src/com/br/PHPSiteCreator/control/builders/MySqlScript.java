/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.model.Classe;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class MySqlScript {

	private List<Classe> classes;

	public MySqlScript() {
		this.classes = new ArrayList<Classe>();
		this.iniciar();
		this.finalizar();
	}
	
	public void addClasse(Classe classe)
	{
		this.classes.add(classe);
	}
	
	private void iniciar()
	{
		//TODO implemetar
	}
	
	private void finalizar()
	{
		// TODO implementar
	}

}
