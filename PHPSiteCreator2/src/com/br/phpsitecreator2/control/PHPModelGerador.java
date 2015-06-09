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
		a.addLinha("$s = \"{\";", 2);
		for(Variavel v: this.classe.getVariaveis())
		{
			a.addLinha();
			a.addLinha("if(!is_null("+v.getNomePropriedade()+")){",2);
			a.addLinha("$s .= '\""+v.getNomeProgramavel()+"\":",3);
			if(!(v.getTipo() == Variavel.INTEIRO || v.getTipo() == Variavel.FLOAT || v.getTipo() == Variavel.DINHEIRO))
			{
				a.addFrase("\"");
			}
			a.addFrase("'."+v.getNomePropriedade()+".'");
			if(!(v.getTipo() == Variavel.INTEIRO || v.getTipo() == Variavel.FLOAT || v.getTipo() == Variavel.DINHEIRO))
			{
				a.addFrase("\"");
			}
			if(this.classe.getVariaveis().indexOf(v)<this.classe.getVariaveis().size()-1)
			{
				a.addFrase(",");
			}
			a.addFrase("';");
			a.addLinha("}else{",2);
			a.addLinha("$s .= '\""+v.getNomeProgramavel()+"\":null';",3);
			a.addLinha("}",2);
		}
		a.addLinha("$s .= '}';",2);
		this.fecharFuncao();
	}

	@Override
	protected void iniciarClasse() {
		a.addFrase("class "+classe.getNome());
		a.addLinha("{");
	}
}
