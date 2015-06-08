package com.br.phpsitecreator2.main;

import com.br.phpsitecreator2.model.Classe;
import com.br.phpsitecreator2.model.Variavel;

public class GeradorDeClasse {

	public static Classe getPessoa()
	{
		Classe pessoa = new Classe("Pessoa");
		
		Variavel nome = new Variavel("Nome", Variavel.STRING, "Nome da pessoa");
		nome.setTamanho(250);
		nome.setRequerido(true);
		
		Variavel sexo = new Variavel("Sexo",Variavel.STRING,"Sexo da pessoa");
		sexo.setTamanho(1);
		sexo.setRequerido(true);
		
		Variavel data_nascimento = new Variavel("Data de nascimento",Variavel.DATA,"Data de nascimento da pessoa");
		
		Variavel email = new Variavel("Email",Variavel.EMAIL,"Email da pessoa");
		email.setRequerido(true);
		
		Variavel CPF = new Variavel("CPF",Variavel.DOCUMENTO,"CPF da pessoa");
		
		Variavel curriculo_lattes = new Variavel("Curriculo Lattes",Variavel.LINK,"Link para o curriculo lattes pessoal");
		
		Variavel numero_funcional = new Variavel("Numero Funcional",Variavel.STRING,"Numero funciona da pessoa, tal como EMPRESA - NUMERO FUNCIONAL");
		
		Variavel foto = new Variavel("Foto",Variavel.ARQUIVO,"Foto da pessoa");
		foto.setRequerido(true);
		foto.setDefaultValue("foto.jpeg");
		
		Variavel especializacao = new Variavel("Especialização",Variavel.STRING,"Especialização da pessoa");
		
		pessoa.addVariavel(nome);
		pessoa.addVariavel(sexo);
		pessoa.addVariavel(data_nascimento);
		pessoa.addVariavel(email);
		pessoa.addVariavel(CPF);
		pessoa.addVariavel(curriculo_lattes);
		pessoa.addVariavel(numero_funcional);
		pessoa.addVariavel(foto);
		pessoa.addVariavel(especializacao);
		
		return pessoa;
	}
	
	public static Classe getUsuario()
	{
		Classe Usuario = new Classe("Usuario");
		
		Variavel funcionario = new Variavel("Funcionario",Variavel.INTEIRO,"Funcionário a qual vai representar este usuário");
		funcionario.setRequerido(true);
		funcionario.addChaveEstrangeira(GeradorDeClasse.getPessoa());
		
		Variavel login = new Variavel("login", Variavel.STRING, "Login de um determinado funcionario");
		login.setRequerido(true);
		login.setUnico(true);
		
		Variavel senha = new Variavel("Senha",Variavel.STRING,"Senha de um determinado login");
		senha.setRequerido(true);
		
		Variavel nivel = new Variavel("Nivel",Variavel.STRING,"Nível de permissão do usuário");
		nivel.setRequerido(true);
		
		Variavel data_cadastro = new Variavel("Data de cadastro",Variavel.DATA_HORARIO,"Data de cadastro do usuário");
		data_cadastro.setRequerido(true);
		
		Usuario.addVariavel(funcionario);
		Usuario.addVariavel(login);
		Usuario.addVariavel(senha);
		Usuario.addVariavel(nivel);
		Usuario.addVariavel(data_cadastro);
		
		
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
