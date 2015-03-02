package com.br.phpsitecreator2.control;

import java.util.ArrayList;
import java.util.List;

import com.br.phpsitecreator2.model.Classe;
import com.br.phpsitecreator2.model.Parametro;

public class PHPClasseGerador {

	private Classe classe;

	/**
	 * @param classe
	 */
	public PHPClasseGerador(Classe classe) {
		super();
		this.classe = classe;
	}

	protected void gerarConstrutor(List<Parametro> parametros) {

	}

	protected void gerarConstrutor(Parametro parametro) {

		List<Parametro> p = new ArrayList<Parametro>();
		p.add(parametro);
		gerarConstrutor(p);
	}

	protected void gerarConstrutor() {

	}

	protected void gerarFuncao(List<Parametro> parametros) {

	}

	protected void gerarFuncao(Parametro parametro) {
		List<Parametro> p = new ArrayList<Parametro>();
		p.add(parametro);
		gerarFuncao(p);
	}

	protected void gerarFuncao() {

	}

}
