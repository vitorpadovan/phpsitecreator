package com.br.PHPSiteCreator.model;

import java.util.ArrayList;
import java.util.List;

public class ChavePrimaria {

	private List<Variavel> chavePrimaria;
	public ChavePrimaria(Variavel variavel) {
		this.chavePrimaria = new ArrayList<Variavel>();
		chavePrimaria.add(variavel);
	}
	
	public void addChave(Variavel variavel)
	{
		this.chavePrimaria.add(variavel);
	}

	/**
	 * @return the chavePrimaria
	 */
	public List<Variavel> getChavePrimaria() {
		return chavePrimaria;
	}

	
	
}
