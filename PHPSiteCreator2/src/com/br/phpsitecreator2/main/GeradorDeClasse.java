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
		nome.setIndice(true);
		
		Variavel sexo = new Variavel("Sexo",Variavel.STRING,"Sexo da pessoa");
		sexo.setTamanho(1);
		sexo.setRequerido(true);
		
		Variavel data_nascimento = new Variavel("Data de nascimento",Variavel.DATA,"Data de nascimento da pessoa");
		
		Variavel email = new Variavel("Email",Variavel.EMAIL,"Email da pessoa");
		email.setRequerido(true);
		email.setIndice(true);
		
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
		nivel.setTamanho(10);
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
		Classe ClassAtividade = new Classe("Atividade");
		
		Variavel atividade = new Variavel("Atividade", Variavel.STRING, "Nome da atividade");
		atividade.setRequerido(true);
		atividade.setUnico(true);
		
		Variavel descricao = new Variavel("Descrição", Variavel.TEXTO,"Descrição da atividade");
		
		
		ClassAtividade.addVariavel(atividade);
		ClassAtividade.addVariavel(descricao);
		
		
		return ClassAtividade;		
	}
	
	public static Classe getServico()
	{
		Classe CServico = new Classe("Servico");
		
		Variavel servico = new Variavel("Serviço",Variavel.STRING,"Nome do serviço");
		servico.setRequerido(true);
		servico.setUnico(true);
		servico.setIndice(true);
		
		Variavel descricao = new Variavel("Descrição",Variavel.TEXTO,"Descrição do serviço");
		descricao.setRequerido(true);
		
		Variavel exemplos = new Variavel("Exemplos",Variavel.TEXTO,"Exemplos de serviços a serem oferecidos");
		exemplos.setRequerido(true);
		
		
		CServico.addVariavel(servico);
		CServico.addVariavel(descricao);
		CServico.addVariavel(exemplos);
		
		return CServico;		
	}
	
	public static Classe getPatrimonio()
	{
		Classe Usuario = new Classe("Patrimonio");
		return Usuario;		
	}
	
	public static Classe getChamado()
	{
		Classe Usuario = new Classe("Chamado");
		Variavel inicio = new Variavel("Inicio",Variavel.DATA_HORARIO,"Data e hora do inicio de um chamado");
		inicio.setRequerido(true);
		Variavel fim = new Variavel("FIm",Variavel.DATA_HORARIO,"Data e hora do fim de um chamado");
		fim.setRequerido(true);
		Variavel usuarioDeAbertura = new Variavel("Usuario de abertura",Variavel.INTEIRO,"Usuário que abriu o chamado");
		usuarioDeAbertura.setRequerido(true);
		Variavel usuarioSolicitante = new Variavel("Usuario solicitante",Variavel.INTEIRO,"Usuário que solicitou o chamado");
		usuarioSolicitante.setRequerido(true);
		Variavel descricao = new Variavel("Descricao",Variavel.TEXTO,"Descrição do chamado");
		descricao.setRequerido(true);
		Variavel patrimonio = new Variavel("Patrimonio",Variavel.INTEIRO,"Patrimonio associado ao chamado");
		
		
		Usuario.addVariavel(inicio);
		Usuario.addVariavel(fim);
		Usuario.addVariavel(usuarioDeAbertura);
		Usuario.addVariavel(usuarioSolicitante);
		Usuario.addVariavel(descricao);
		Usuario.addVariavel(patrimonio);
		
		return Usuario;		
	}
	
	public static Classe getDocumentacao()
	{
		Classe Documento = new Classe("Documentacao");
		Variavel pasta = new Variavel("Pasta",Variavel.STRING,"Pasta em que será arquivado o documento");
		pasta.setRequerido(true);
		Variavel nomeDoArquivo = new Variavel("Nome do arquivo",Variavel.STRING,"Nome do arquivo do documento");
		nomeDoArquivo.setRequerido(true);
		Variavel tipoDoArquivo = new Variavel("Tipo do arquivo",Variavel.STRING,"Tipo do arquivo a ser usado");
		tipoDoArquivo.setRequerido(true);
		Variavel extensao = new Variavel("Extensão",Variavel.STRING,"Extensão do arquivo do documento");
		extensao.setRequerido(true);
		
		Documento.addVariavel(pasta);
		Documento.addVariavel(nomeDoArquivo);
		Documento.addVariavel(tipoDoArquivo);
		Documento.addVariavel(extensao);
		
		return Documento;		
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
	
	public static Classe getAcesso()
	{
		Classe acesso = new Classe("Acesso");
		return acesso;
	}
}
