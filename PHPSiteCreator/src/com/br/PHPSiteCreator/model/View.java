package com.br.PHPSiteCreator.model;

import java.util.ArrayList;
import java.util.List;

public class View {

	private Classe c;
	private Classe resultado;
	
	public View(Classe classe)
	{
		if(classe.isChaveEstrangeira())
		{
			c = classe;
			processar();
		}
	}
	
	private void processar()
	{
		List<Variavel> referencias = new ArrayList<Variavel>();
		resultado = new Classe("view_"+c.getNome());
		Variavel chave = Variavel.clone(c.getChavePrimaria());
		chave.setNome(c.getNome()+"_"+chave.getNome());
		resultado.addChave(chave);
		for(Variavel v : c.getVariaveis())
		{
			if(v.isTemChaveEstrangeira())
			{
				referencias.add(v);
			}
			
			Variavel v2 = Variavel.clone(v);
			v2.setNome(c.getNome()+"_"+v.getNome());
			resultado.addVariavel(v2);
		}
		
		for(Variavel v: referencias)
		{
			ChaveEstrangeira ch = v.getChaveEstrangeira();
			
			Variavel v2 = ch.getClasse().getChavePrimaria();
			String nome = ch.getClasse().getNome()+"_"+v2.getNome();
			Variavel t = new Variavel(nome, v2.getTipo(), v2.getTamanho(), v2.isRequerido(), v2.getChaveEstrangeira(), v2.getDescricao());
			resultado.addVariavel(t);
			
			for(Variavel v3 : ch.getClasse().getVariaveis())
			{
				String nome2 = ch.getClasse().getNome()+"_"+v3.getNome();
				Variavel t2 = Variavel.clone(v3);
				t2.setNome(nome2);
				resultado.addVariavel(t2);
			}
		}
	}

	public Classe getResultado()
	{
		return resultado;
	}
}
