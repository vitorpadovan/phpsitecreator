/**
 * 
 */
package com.br.PHPSiteCreator;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.control.builders.MySqlCreateDatabase;
import com.br.PHPSiteCreator.control.builders.PHPControl;
import com.br.PHPSiteCreator.control.builders.PHPDatabase;
import com.br.PHPSiteCreator.control.builders.PHPModel;
import com.br.PHPSiteCreator.control.builders.PHPView;
import com.br.PHPSiteCreator.control.builders.others.PHPKernel;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.SiteInfo;
import com.br.PHPSiteCreator.util.Debug;

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
		new SiteInfo("NIPP_testes");
		this.inicarClassesBasicas();
		this.inicarVariaveis();
		this.adicionarClasses();
		this.processar();
	}
	
	private void inicarClassesBasicas()
	{
		new PHPKernel();
	}
	
	private void inicarVariaveis()
	{
		Debug.m("Iniciando Variáveis");
	}
	
	private void adicionarClasses()
	{
		Debug.m("Adicionando classes");
		this.classes.add(getPessoa());
	}
	
	private void processar()
	{
		Debug.m("Processando classes");
		for(Classe classe : classes)
		{	
			new PHPControl(classe);
			new PHPDatabase(classe);
			new PHPModel(classe);
			new PHPView(classe);
		}
	}
	
	private Classe getPessoa()
	{
		Debug.m("Obtendo classe pessoa");
		Classe pessoa = new Classe("Pessoa");
		return pessoa;
	}

}
