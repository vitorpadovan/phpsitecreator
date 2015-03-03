package com.br.phpsitecreator2.main;

import java.util.ArrayList;
import java.util.List;

import com.br.phpsitecreator2.model.Classe;

public class Main {
	
	private List<Classe> classes;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	
	public Main()
	{
		classes = new ArrayList<Classe>();
		carregarClasses();
		processar();
	}
	
	private void carregarClasses()
	{
		classes.add(GeradorDeClasse.getPessoa());
		classes.add(GeradorDeClasse.getAtividade());
		classes.add(GeradorDeClasse.getServico());
		classes.add(GeradorDeClasse.getCargo());
		classes.add(GeradorDeClasse.getDocumentacao());
		classes.add(GeradorDeClasse.getPessoa());
	}
	
	private void processar()
	{
		
	}

}