package com.br.phpSiteCreator.model;

import java.util.ArrayList;
import java.util.List;

import com.br.phpSiteCreator.control.util.Debug;

public class View {

	private Classe classeOriginal;
	private Classe classeFinal;
	private List<Classe> referencias;

	public View(Classe classe) {
		this.classeOriginal = classe;
		this.classeFinal = null;
		this.referencias = new ArrayList<Classe>();
		this.validarView();
	}

	private void validarView() {
		if (this.classeOriginal.isExisteReferencia()) {
			//TODO debug aqui
			//Debug.mensagem("A Classe " + this.classeOriginal.getNome()+" tem referencias");
			this.prepararClasseFinal();
		}
	}

	private void prepararClasseFinal() {
		List<Classe> referencias = this.classeOriginal.getReferencias();
		String nome = this.classeOriginal.getNome();
		nome = View.makeViewName(this.classeOriginal);

		this.classeFinal = new Classe(nome);
		this.classeFinal.setView();
		this.classeFinal.setChavePrimaria(this.classeOriginal.getChavePrimaria());

		// Adicionando as variáveis da classe original
		for (Variavel v : this.classeOriginal.getVariaveis()) 
		{			
			Variavel t = new Variavel(v.getNome(), v.getTipo(), v.getTamanho(),v.isRequerido());
			t.setChaveEstrangeira(v.getChaveEstrangeira());
			this.classeFinal.add(t);
		}

		// ADicionadno as variavels das referências
		for (Classe c : referencias) {
			for (Variavel v : c.getVariaveis()) {
				String nomeDaVariavel = View.makeVarName(c, v);
				Variavel t = new Variavel(nomeDaVariavel, v.getTipo(),v.getTamanho(), v.isRequerido());
				t.setChaveEstrangeira(v.getChaveEstrangeira());
				this.classeFinal.add(t);
			}
		}
	}

	public static String makeVarName(Classe classe, Variavel variavel) {
		String resultado = classe.getNome().substring(0, 4) + "_"
				+ variavel.getNome();
		return resultado;
	}

	public static String makeViewName(Classe classe) {
		String nome = classe.getNome();
		for (Classe ref : classe.getReferencias()) {
			nome += "_" + ref.getNome().substring(0, 3);
		}
		return nome;
	}

	public Classe getClasseOriginal() {
		return classeOriginal;
	}

	public Classe getClasseFinal() {
		if(this.classeFinal!= null)
		{
			for(Variavel v: classeFinal.getVariaveis())
			{
				//TODO Debug aqui
				//Debug.mensagem("A classe final "+classeFinal.getNome()+" possui a seguinte variavel: "+v.getNome());
			}
		}
		return classeFinal;
	}

}
