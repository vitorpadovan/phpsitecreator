package com.br.phpsitecreator2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.br.phpsitecreator2.main.SiteInfo;
import com.br.phpsitecreator2.model.Arquivo;
import com.br.phpsitecreator2.model.Classe;
import com.br.phpsitecreator2.model.Modificador;
import com.br.phpsitecreator2.model.Parametro;
import com.br.phpsitecreator2.model.Variavel;
import com.br.phpsitecreator2.util.Debug;

public abstract class PHPClasseGerador {

	protected Classe classe;
	protected Arquivo a;

	/**
	 * @param classe
	 */
	public PHPClasseGerador(Classe classe,String path_file) {
		super();
		this.classe = classe;
		this.a = new Arquivo(SiteInfo.SYSTEM_DIR+File.separator+path_file, classe.getNome(), "class.php");
		this.iniciarClasse();
		this.gerarVariaveis();
		this.gerarConstrutor();
		this.gerarGetAndSets();
		this.corpo();
		this.gerarDestrutor();
		this.finalizarClasse();
		this.a.gravar();
	}
	
	abstract protected void iniciarClasse();
	
	private void finalizarClasse()
	{
		this.a.addLinha("}");
	}
	
	protected void gerarConstrutor()
	{
		a.addLinha();
		a.addLinha("public function __construct(", 1);
		for(Variavel v :this.classe.getVariaveis())
		{
			
			a.addFrase(v.getNomeVariavelProgramavel()+" = null");
			if(this.classe.getVariaveis().indexOf(v) < (this.classe.getVariaveis().size())-1)
			{
				a.addFrase(" ,");
			}
			
		}
		Debug.d("Temos "+this.classe.getChaves().size()+" chaves");
		a.addFrase(")");
		a.addLinha("{",1);
		for(Variavel v :this.classe.getVariaveis())
		{
			a.addLinha(v.getNomePropriedade()+" = "+v.getNomeVariavelProgramavel()+";",2);
		}
		a.addLinha("}",1);
	}
	
	protected void gerarVariaveis()
	{
		for(Variavel v: this.classe.getVariaveis())
		{
			a.addLinha("private "+v.getNomeVariavelProgramavel()+" = null;",1);
		}
	}
	
	protected void gerarDestrutor()
	{
		a.addLinha();
		a.addLinha("public function __destruct()",1);
		a.addLinha("{",1);
			for(Variavel v: this.classe.getVariaveis())
			{
				a.addLinha(v.getNomeVariavelProgramavel()+" = null;",2);
			}
		a.addLinha("}",1);
	}
	
	public static String generalizarFuncao(Variavel variavel)
	{
		String s = variavel.getNomeProgramavel();
		String[] array = s.split("_");
		String resultado = "";
		for(String a : array)
		{
			resultado +=  a.substring(0, 1).toUpperCase()+a.substring(1);
			s = resultado;
		}
		return s;
	}
	
	public static String generalizarFuncao(String s)
	{
		String[] array = s.split("_");
		String resultado = "";
		for(String a : array)
		{
			resultado +=  a.substring(0, 1).toUpperCase()+a.substring(1);
			s = resultado;
		}
		return s;
	}
	
	public static String generalizarGet(Variavel variavel)
	{
		return null;
	}
	
	public static String generalizarSet(Variavel variavel)
	{

		return null;
	}
	
	protected void abrirFuncao(Modificador m,String prefixo,String nome,List<Parametro> parametros)
	{
		a.addLinha();
		String nomeFuncao = this.generalizarFuncao(nome);
		a.addLinha(m.getModificador()+ " "+"function "+prefixo+nomeFuncao+"(",1);
		for(Parametro p : parametros)
		{
			a.addFrase(p.getNomeVariavel());
		}
		a.addFrase(")");
		a.addLinha("{",1);
	}
	
	protected void abrirFuncao(Modificador m,String prefixo,String nome,Parametro parametro)
	{
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(parametro);
		this.abrirFuncao(m, prefixo, nome, parametros);
	}
	
	protected void abrirFuncao(Modificador m,String prefixo,String nome)
	{
		List<Parametro> parametros = new ArrayList<Parametro>();
		this.abrirFuncao(m, prefixo, nome, parametros);
	}
	
	protected void fecharFuncao()
	{
		a.addLinha("}",1);
	}
	
	abstract protected void gerarGetAndSets();
	abstract protected void corpo();
}
