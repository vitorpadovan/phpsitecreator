/**
 * 
 */
package com.br.PHPSiteCreator.control.builders.others;

import com.br.PHPSiteCreator.control.builders.ConstrutorBasico;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.SiteInfo;

/**
 * @author cleverUserNameToUseInSourceCode
 *
 */
public class PHPKernel extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 * @param extensao
	 */
	public PHPKernel() {
		super(new Classe("Kernel"), "system", "", ".php");
		//REMOVE continuar esta classe
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#variaveis()
	 */
	@Override
	public void variaveis() {
		arquivo.addLinha("$debug = false;",2);
		arquivo.addLinha("if(isset($_GET['d']))",2);
		arquivo.addLinha("{",2);
		arquivo.addLinha("$debug = true;",3);
		arquivo.addLinha("}",2);
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#getsAndSets()
	 */
	@Override
	public void getsAndSets() {
		//NOT_USABLE não precisa disto
	}

	/* (non-Javadoc)
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#corpo()
	 */
	@Override
	public void corpo() {
		this.iniciarKernel();
		this.carregarDebug();
		this.carregarClasses();
	}
	
	public void iniciarKernel()
	{
		this.iniciarFuncao("iniciarKernel");
		this.arquivo.addLinha("$this->carregarDebug();",3);
		this.arquivo.addLinha("$this->carregarClasses();",3);
		this.finalizarFuncao();
	}
	
	public void carregarDebug()
	{
		this.iniciarFuncao("carregarDebug");
		arquivo.addLinha("if(strtoupper(substr(PHP_OS, 0, 3)) === 'WIN')",3);
		arquivo.addLinha("{",3);
			arquivo.addLinha("$OS = 1;",4);
			arquivo.addLinha("$pasta_atual = '"+SiteInfo.getPastaBase()+"\\system';",4);
		arquivo.addLinha("}",3);
		arquivo.addLinha("else",3);
		arquivo.addLinha("{",3);
			arquivo.addLinha("$OS = 2;",4);
		arquivo.addLinha("}",3);
		arquivo.addLinha("require_once(\"\");",3);
		this.finalizarFuncao();
	}
	
	public void carregarClasses()
	{
		this.iniciarFuncao("carregarClasses");
		this.finalizarFuncao();
	}

}
