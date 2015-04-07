package com.br.phpsitecreator2.main;

import com.br.phpsitecreator2.model.Classe;
import com.br.phpsitecreator2.model.Variavel;

public class GeradorDeClasse {

	public static Classe getPessoa()
	{
		Classe pessoa = new Classe("Pessoa");
		Variavel nome = new Variavel("Nome", Variavel.STRING,"Nome da pessoa");
		return pessoa;
	}
	
	public static Classe getUsuario()
	{
		Classe Usuario = new Classe("Usuario");
		return Usuario;		
	}
	
	public static Classe getAtividade()
	{
		Classe Usuario = new Classe("Atividade");
		return Usuario;		
	}
	
	public static Classe getServico()
	{
		Classe Usuario = new Classe("Servico");
		return Usuario;		
	}
	
	public static Classe getPatrimonio()
	{
		Classe Usuario = new Classe("Patrimonio");
		return Usuario;		
	}
	
	public static Classe getChamado()
	{
		Classe Usuario = new Classe("Chamado");
		return Usuario;		
	}
	
	public static Classe getDocumentacao()
	{
		Classe Usuario = new Classe("Documentacao");
		return Usuario;		
	}
	
	public static Classe getLocais()
	{
		Classe Usuario = new Classe("Locais");
		return Usuario;		
	}
	
	public static Classe getCargo()
	{
		Classe Usuario = new Classe("Cargo");
		return Usuario;		
	}
	
	public static Classe getTesesEDissertacoes()
	{
		Classe Usuario = new Classe("TesesEDissertacoes");
		return Usuario;		
	}
	
	public static Classe getTramite()
	{
		Classe Usuario = new Classe("Tramite");
		return Usuario;		
	}
}
