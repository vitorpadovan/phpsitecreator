/**
 * 
 */
package com.br.PHPSiteCreator;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.control.builders.PHPControl;
import com.br.PHPSiteCreator.control.builders.PHPDatabase;
import com.br.PHPSiteCreator.control.builders.PHPModel;
import com.br.PHPSiteCreator.control.builders.PHPView;
import com.br.PHPSiteCreator.control.builders.others.PHPKernel;
import com.br.PHPSiteCreator.model.ChaveEstrangeira;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Relacionamento;
import com.br.PHPSiteCreator.model.SiteInfo;
import com.br.PHPSiteCreator.model.Tipo;
import com.br.PHPSiteCreator.model.Variavel;
import com.br.PHPSiteCreator.util.Debug;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class Main {
	
	private List<Classe> classes = new ArrayList<Classe>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
	
	public Main()
	{
		new SiteInfo("NIPP_testes");
		this.inicarClassesBasicas();
		this.inicarVariaveis();
		this.adicionarClasses();
		this.processar();
	}
	
	private void inicarClassesBasicas()
	{
		//NOT_USABLE Classes basicas desativadas
		//new PHPKernel();
	}
	
	private void inicarVariaveis()
	{
		Debug.m("Iniciando Variáveis");
	}
	
	private void adicionarClasses()
	{
		Debug.m("Adicionando classes");
		this.classes.add(getPessoa());
		this.classes.add(getUsuario());
	}
	
	private void processar()
	{
		Debug.m("Processando classes");
		for(Classe classe : classes)
		{	
			new PHPModel(classe);
			new PHPControl(classe);
			new PHPDatabase(classe);
			new PHPView(classe);
		}
	}
	
	private Classe getPessoa()
	{
		Debug.m("Obtendo classe pessoa");
		Classe pessoa = new Classe("Pessoa");
				
		Variavel cod_pessoa = new Variavel("cod_pessoa", Tipo.INT, 100, true, null, "Chave primária da pessoa");
		Variavel nome = new Variavel("nome", Tipo.VARCHAR, 100, true, null, "Nome da pessoa",true, true);
		Variavel sexo = new Variavel("sexo", Tipo.VARCHAR, 1, true, null, "Sexo da pessoa");
		Variavel data_nascimento = new Variavel("data_nascimento", Tipo.DATE, 0,false, null, "Data de nascimento da pessoa");
		Variavel email = new Variavel("email", Tipo.VARCHAR, 100, true, null, "Email da pessoa",true,true);
		Variavel cpf = new Variavel("cpf", Tipo.VARCHAR, 100, false, null, "CPF da pessoa",true,true);
		Variavel curriculo_lattes = new Variavel("curriculo_lattes", Tipo.VARCHAR, 100, false, null, "Curriculo Lattes da pessoa",true,true);
		Variavel numero_funcional = new Variavel("numero_funcional", Tipo.VARCHAR, 100, false, null, "Numero funcional da pessoa");
		
		pessoa.addChave(cod_pessoa);
		pessoa.addVariavel(nome);
		pessoa.addVariavel(sexo);
		pessoa.addVariavel(data_nascimento);
		pessoa.addVariavel(email);
		pessoa.addVariavel(cpf);
		pessoa.addVariavel(curriculo_lattes);
		pessoa.addVariavel(numero_funcional);
		
		return pessoa;
	}
	
	private Classe getUsuario()
	{
		Debug.m("Obtendo class Usuário");
		Classe usuario = new Classe("Usuario");
		
		Variavel cod_usuario = new Variavel("cod_usuario", Tipo.INT, 100, true, null, "Código do usuário");
		Variavel funcionario = new Variavel("funcionario", Tipo.INT, 100, true, new ChaveEstrangeira(this.getPessoa(), Relacionamento.UM_UM), "Código do funcionário relacionado com este usuário",true,true);
		Variavel login = new Variavel("login", Tipo.VARCHAR, 100, true, null, "Login do usuário",true,true);
		Variavel senha = new Variavel("senha",Tipo.VARCHAR,50,true, null,"senha do usuário");
				
		usuario.addChave(cod_usuario);
		usuario.addVariavel(funcionario);
		usuario.addVariavel(login);
		usuario.addVariavel(senha);
		
		return usuario;
	}

}
