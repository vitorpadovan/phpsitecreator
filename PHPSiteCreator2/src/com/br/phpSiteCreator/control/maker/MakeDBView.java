package com.br.phpSiteCreator.control.maker;

import java.util.ArrayList;
import java.util.List;

import com.br.phpSiteCreator.model.Arquivo;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.Variavel;
import com.br.phpSiteCreator.model.View;

public class MakeDBView {

	private Arquivo a;
	private Classe c;

	public MakeDBView(Arquivo a, Classe c) {
		this.a = a;
		this.c = c;
		this.montarView();
	}

	public Arquivo getA() {
		return a;
	}

	public Classe getC() {
		return c;
	}

	private void montarView() {
		if (!this.c.isExisteReferencia()) {
			return;
		}

		List<String> strRef = new ArrayList<String>();
		List<Classe> clsRef = new ArrayList<Classe>();
		View v = new View(this.c);
		//this.c = v.getClasseFinal();

		this.a.proximaLinha();
		this.a.addLinha("CREATE VIEW "+v.getClasseFinal().getNome()+" as ");

		this.a.addLinha("SELECT");

		for (int i = 0; i < c.getVariaveis().size(); i++) {
			Variavel tempVar = c.getVariaveis().get(i);
			this.a.addLinha(c.getNome()+"."+tempVar.getNome()+",",2);
			if (tempVar.isExisteChaveEstrangeira()) {
				String refs = c.getNome()
						+ "."
						+ tempVar.getNome()
						+ " = "
						+ tempVar.getChaveEstrangeira().getNome()
						+ "."
						+ tempVar.getChaveEstrangeira().getChavePrimaria()
								.getNome();
				// System.out.println("\tA referencia é "+ref);
				strRef.add(refs);
				clsRef.add(tempVar.getChaveEstrangeira());
			}
		}
		boolean seg = false;
		for(Classe classe : clsRef)
		{
			for(Variavel variavel : classe.getVariaveis())
			{
				if(seg)
				{
					this.a.addFrase(",");
				}
				this.a.addLinha(classe.getNome()+"."+variavel.getNome()+" as "+View.makeVarName(classe, variavel),2);
				seg = true;
			}
		}
		
		
		this.a.addLinha("FROM");
		this.a.addLinha(c.getNome(),2);
		this.a.addLinha("LEFT JOIN");
		this.a.addLinha("(");
		seg = false;
		for(Classe ref : clsRef)
		{
			if(seg)
			{
				a.addFrase(", ");
			}
			this.a.addLinha(ref.getNome(),2);
			seg = true;
		}
		this.a.addLinha(")");
		this.a.addLinha("ON");
		this.a.addLinha("(");
		seg = false;
		for(String ref : strRef)
		{
			if(seg)
			{
				a.addFrase(" AND ");
			}
			this.a.addLinha(ref,2);
			seg = true;
		}
		this.a.addLinha(");");
	}
}
