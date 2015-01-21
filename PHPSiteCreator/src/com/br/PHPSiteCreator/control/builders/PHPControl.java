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
		super(classe, "system" + File.separator + "control", "CTRL_",
				".class.php");
		// NOT_USABLE não precisa disto
	}

	@Override
	public void variaveis() {
		// NOT_USABLE não precisa disto

	}

	@Override
	public void getsAndSets() {
		// NOT_USABLE não precisa disto
	}

	@Override
	public void corpo() {
		this.salvar();
		this.excluir();
		this.atualizar();

	}

	private void salvar() {
		// TODO implementar o salvar dos itens;
	}

	private void excluir() {
		// TODO implementar o excluir dos itens
	}

	private void atualizar() {
		// TODO implementar o atualizar os itens
	}

}
