/**
 * 
 */
package com.br.PHPSiteCreator;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.control.builders.MySqlScript;
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

	public Main() {
		new SiteInfo("NIPP_testes");
		this.inicarClassesBasicas();
		this.inicarVariaveis();
		this.adicionarClasses();
		this.processar();
	}

	private void inicarClassesBasicas() {
		// NOT_USABLE Classes basicas desativadas
		// new PHPKernel();
	}

	private void inicarVariaveis() {
		Debug.m("Iniciando Variáveis");
	}

	private void adicionarClasses() {
		Debug.m("Adicionando classes");
		this.classes.add(getPessoa());
		this.classes.add(getUsuario());
		this.classes.add(getCargo());
		classes.add(getRelacaoPessoaCargo());
		classes.add(getAtividade());
		classes.add(getServico());
		classes.add(getLocais());
		classes.add(getPatrimonio());
		classes.add(getChamado());
	}

	private void processar() {
		MySqlScript script = new MySqlScript();
		Debug.m("Processando classes");
		for (Classe classe : classes) {
			new PHPModel(classe);
			new PHPControl(classe);
			new PHPDatabase(classe);
			new PHPView(classe);
			script.addClasse(classe);
		}
		script.executar();
	}

	private Classe getPessoa() {
		Debug.m("Obtendo classe pessoa");
		Classe pessoa = new Classe("Pessoa");

		Variavel cod_pessoa = new Variavel("cod_pessoa", Tipo.INT, 100, true,
				null, "Chave primária da pessoa");
		Variavel nome = new Variavel("nome", Tipo.VARCHAR, 100, true, null,
				"Nome da pessoa", true, true);
		Variavel sexo = new Variavel("sexo", Tipo.VARCHAR, 1, true, null,
				"Sexo da pessoa");
		Variavel data_nascimento = new Variavel("data_nascimento", Tipo.DATE,
				0, false, null, "Data de nascimento da pessoa");
		Variavel email = new Variavel("email", Tipo.VARCHAR, 100, true, null,
				"Email da pessoa", true, true);
		Variavel cpf = new Variavel("cpf", Tipo.VARCHAR, 100, false, null,
				"CPF da pessoa", true, true);
		Variavel curriculo_lattes = new Variavel("curriculo_lattes",
				Tipo.VARCHAR, 100, false, null, "Curriculo Lattes da pessoa",
				true, true);
		Variavel numero_funcional = new Variavel("numero_funcional",
				Tipo.VARCHAR, 100, false, null, "Numero funcional da pessoa");

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

	private Classe getUsuario() {
		Debug.m("Obtendo class Usuário");
		Classe usuario = new Classe("Usuario");

		Variavel cod_usuario = new Variavel("cod_usuario", Tipo.INT, 100, true,
				null, "Código do usuário");

		Variavel funcionario = new Variavel("funcionario", Tipo.INT, 100, true,
				null, "Código do funcionário relacionado com este usuário",
				true, true);
		ChaveEstrangeira c = new ChaveEstrangeira(funcionario,
				this.getPessoa(), Relacionamento.UM_UM);
		funcionario.setChaveEstrangeira(c);

		Variavel login = new Variavel("login", Tipo.VARCHAR, 100, true, null,
				"Login do usuário", true, true);
		Variavel senha = new Variavel("senha", Tipo.VARCHAR, 50, true, null,
				"senha do usuário");

		usuario.addChave(cod_usuario);
		usuario.addVariavel(funcionario);
		usuario.addVariavel(login);
		usuario.addVariavel(senha);

		return usuario;
	}

	private Classe getCargo() {
		Classe clsCargo = new Classe("Cargo");

		Variavel cod_cargo = new Variavel("cod_cargo", Tipo.INT, 100, true,
				null, "Código do cargo");
		Variavel cargo = new Variavel("cargo", Tipo.VARCHAR, 100, true, null,
				"Nome do cargo");
		Variavel prioridade = new Variavel("prioridade", Tipo.INT, 100, true,
				null, "Priporidade do cargo");

		clsCargo.addChave(cod_cargo);
		clsCargo.addVariavel(cargo);
		clsCargo.addVariavel(prioridade);

		return clsCargo;
	}

	private Classe getRelacaoPessoaCargo() {
		Classe resultado = new Classe("RelacaoPessoaCargo");

		Variavel cod_relacao_pessoa_cargo = new Variavel(
				"cod_relacao_pessoa_cargo", Tipo.INT, 100, true, null,
				"Código darelação");
		Variavel pessoa = new Variavel("pessoa", Tipo.INT, 100, true, null,
				"Código da pessoa");
		Variavel cargo = new Variavel("cargo", Tipo.INT, 100, true, null,
				"Código do cargo");
		Variavel data_cadastro = new Variavel("data_cadastro", Tipo.DATETIME,
				100, true, null, "Data em que o cargo foi cadastrado");
		Variavel validade = new Variavel("validade", Tipo.DATETIME, 100, false,
				null, "Data em que o contrato da pessoa irá terminar");

		ChaveEstrangeira rlCargo = new ChaveEstrangeira(cargo, getCargo(),
				Relacionamento.UM_MUITOS);
		cargo.setChaveEstrangeira(rlCargo);

		ChaveEstrangeira rlPessoa = new ChaveEstrangeira(pessoa, getPessoa(),
				Relacionamento.UM_MUITOS);
		pessoa.setChaveEstrangeira(rlPessoa);

		resultado.addChave(cod_relacao_pessoa_cargo);
		resultado.addVariavel(pessoa);
		resultado.addVariavel(cargo);
		resultado.addVariavel(data_cadastro);
		resultado.addVariavel(validade);

		return resultado;
	}

	private Classe getAtividade() {
		Classe r = new Classe("Atividade");

		Variavel cod_atividade = new Variavel("cod_atividade", Tipo.INT, 100,
				true, null, "Código da atividade");
		Variavel atividade = new Variavel("atividade", Tipo.VARCHAR, 100, true,
				null, "Nome da atividade");

		r.addChave(cod_atividade);
		r.addVariavel(atividade);

		return r;
	}

	private Classe getServico() {
		Classe r = new Classe("Servico");

		Variavel cod_servico = new Variavel("cod_servico", Tipo.INT, 100, true,
				null, "Código do serviço");
		Variavel servico = new Variavel("servico", Tipo.VARCHAR, 100, true,
				null, "Nome do serviço");

		r.addChave(cod_servico);
		r.addVariavel(servico);
		return r;
	}

	private Classe getLocais() {
		Classe r = new Classe("Locais");
		Variavel cod_servico = new Variavel("cod_servico", Tipo.INT, 100, true,
				null, "Código do local");
		Variavel local = new Variavel("local", Tipo.VARCHAR, 100, true, null,
				"Nome do local");

		r.addChave(cod_servico);
		r.addVariavel(local);
		return r;
	}

	private Classe getPatrimonio() {
		Classe r = new Classe("Patrimonio");

		Variavel cod_patrimonio = new Variavel("cod_patrimonio", Tipo.INT, 100,
				true, null, "Código do patrimonio");
		Variavel empresa = new Variavel("empresa", Tipo.VARCHAR, 100, true,
				null, "Empresa dona do patrimônio");
		Variavel responsavel = new Variavel("responsavel", Tipo.INT, 100, true,
				null, "Responsável pelo patrimonio");
		Variavel localizacao = new Variavel("localizacao", Tipo.INT, 100, true,
				null, "Responsável pelo patrimonio");

		Variavel tipo = new Variavel("tipo", Tipo.VARCHAR, 100, true, null,
				"Responsável pelo patrimonio");
		Variavel marca = new Variavel("marca", Tipo.VARCHAR, 100, false, null,
				"Responsável pelo patrimonio");
		Variavel modelo = new Variavel("modelo", Tipo.VARCHAR, 100, false,
				null, "Responsável pelo patrimonio");
		Variavel mac_eth0 = new Variavel("mac_eth0", Tipo.VARCHAR, 100, false,
				null, "Responsável pelo patrimonio");
		Variavel ip_eth0 = new Variavel("ip_eth0", Tipo.VARCHAR, 100, false,
				null, "Responsável pelo patrimonio");
		Variavel mac_wifi = new Variavel("mac_wifi", Tipo.VARCHAR, 100, false,
				null, "Responsável pelo patrimonio");
		Variavel ip_wifi = new Variavel("ip_wifi", Tipo.VARCHAR, 100, false,
				null, "Responsável pelo patrimonio");
		Variavel situacao = new Variavel("situacao", Tipo.VARCHAR, 100, true,
				null, "Responsável pelo patrimonio");

		Variavel data_cadastro = new Variavel("data_cadastro", Tipo.VARCHAR, 100, true,
				null, "Responsável pelo patrimonio");

		Variavel observacao = new Variavel("observacao", Tipo.TEXTO, 100, false,
				null, "Responsável pelo patrimonio");

		ChaveEstrangeira chResponsavel = new ChaveEstrangeira(responsavel,
				getPessoa(), Relacionamento.MUITOS_UM);
		ChaveEstrangeira chLocalizacao = new ChaveEstrangeira(localizacao,
				getLocais(), Relacionamento.UM_UM);

		responsavel.setChaveEstrangeira(chResponsavel);
		localizacao.setChaveEstrangeira(chLocalizacao);

		r.addChave(cod_patrimonio);
		r.addVariavel(empresa);
		r.addVariavel(responsavel);
		r.addVariavel(localizacao);
		r.addVariavel(tipo);
		r.addVariavel(marca);
		r.addVariavel(modelo);
		r.addVariavel(mac_wifi);
		r.addVariavel(mac_eth0);
		r.addVariavel(ip_wifi);
		r.addVariavel(ip_eth0);
		r.addVariavel(situacao);
		r.addVariavel(data_cadastro);
		r.addVariavel(observacao);

		return r;
	}

	private Classe getChamado() {
		Classe r = new Classe("Chamado");

		Variavel cod_chamado = new Variavel("cod_chamado", Tipo.INT, 100, true,
				null, "Código do chamado");
		Variavel pessoa = new Variavel("pessoa", Tipo.VARCHAR, 100, true, null,
				"Código do chamado");
		Variavel inicio = new Variavel("inicio", Tipo.DATETIME, 100, true,
				null, "Código do chamado");
		Variavel fim = new Variavel("fim", Tipo.DATETIME, 100, false, null,
				"Código do chamado");
		Variavel chamado = new Variavel("chamado", Tipo.TEXTO, 100, true, null,
				"Código do chamado");
		Variavel conclusao = new Variavel("conclusao", Tipo.TEXTO, 100, true,
				null, "Código do chamado");
		Variavel patrimonio = new Variavel("patrimonio", Tipo.INT, 100, true,
				null, "Código do chamado");
		Variavel servico = new Variavel("servico", Tipo.INT, 100, true, null,
				"Código do chamado");

		ChaveEstrangeira chPessoa = new ChaveEstrangeira(pessoa, getPessoa(),
				Relacionamento.UM_MUITOS);
		pessoa.setChaveEstrangeira(chPessoa);
		ChaveEstrangeira chPatrimonio = new ChaveEstrangeira(patrimonio,
				getPatrimonio(), Relacionamento.UM_MUITOS);
		patrimonio.setChaveEstrangeira(chPatrimonio);
		ChaveEstrangeira chServico = new ChaveEstrangeira(servico,
				getServico(), Relacionamento.UM_MUITOS);
		servico.setChaveEstrangeira(chServico);

		r.addChave(cod_chamado);

		r.addVariavel(pessoa);
		r.addVariavel(inicio);
		r.addVariavel(fim);
		r.addVariavel(chamado);
		r.addVariavel(conclusao);
		r.addChave(patrimonio);
		r.addVariavel(servico);

		return r;
	}

}
