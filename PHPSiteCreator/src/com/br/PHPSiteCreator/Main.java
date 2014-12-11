/**
 * 
 */
package com.br.PHPSiteCreator;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.control.builders.PHPModel;
import com.br.PHPSiteCreator.model.Classe;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Main {
	
	private List<Classe> classes = new ArrayList<Classe>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	public Main()
	{
		this.inicarVariaveis();
		this.adicionarClasses();
		this.processar();
	}
	
	private void inicarVariaveis()
	{
	}
	
	private void adicionarClasses()
	{
		this.classes.add(getPessoa());
	}
	
	private void processar()
	{
		for(Classe classe : classes)
		{
			new PHPModel(classe);
		}
	}
	
	private Classe getPessoa()
	{
		Classe pessoa = new Classe("Pessoa");
		return pessoa;
	}

}
