/**
 * 
 */
package com.br.phpsitecreator2.control;

import java.io.File;

import com.br.phpsitecreator2.main.SiteInfo;
import com.br.phpsitecreator2.model.Arquivo;
import com.br.phpsitecreator2.model.Classe;
import com.br.phpsitecreator2.model.Modificador;
import com.br.phpsitecreator2.model.Parametro;
import com.br.phpsitecreator2.model.Variavel;

/**
 * @author VitorHugo
 *
 */
public class PHPModelGerador extends PHPClasseGerador {
	
	public PHPModelGerador(Classe classe) {
		super(classe,"model");
	}

	@Override
	protected void gerarGetAndSets() {
		for(Variavel v: this.classe.getVariaveis())
		{
			Parametro p = new Parametro(v.getNome(),"null", v.getTipo());
			this.abrirFuncao(new Modificador(Modificador.PUBLICO), "set", v.getNomeProgramavel(), p);
			a.addLinha(v.getNomePropriedade()+ " = "+v.getNomeVariavelProgramavel()+";",2);
			this.fecharFuncao();
			
			this.abrirFuncao(new Modificador(Modificador.PUBLICO), "get", v.getNomeProgramavel());
			a.addLinha("return "+v.getNomePropriedade()+";",2);
			this.fecharFuncao();
		}
	}

	@Override
	protected void corpo() {
		this.gerarJSON();
	} 
	
	private void gerarJSON()
	{
		this.abrirFuncao(new Modificador(Modificador.PUBLICO), "get", "JSON");
		this.fecharFuncao();
	}

	@Override
	protected void iniciarClasse() {
		a.addFrase("class "+classe.getNome());
		a.addLinha("{");
	}
}
