package com.br.phpSiteCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.br.phpSiteCreator.control.maker.MakeControl;
import com.br.phpSiteCreator.control.maker.MakeDBScript;
import com.br.phpSiteCreator.control.maker.MakeDatabase;
import com.br.phpSiteCreator.control.maker.MakeModel;
import com.br.phpSiteCreator.control.maker.MakeView;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.SiteInfo;
import com.br.phpSiteCreator.model.Tipo;
import com.br.phpSiteCreator.model.Variavel;
import com.br.phpSiteCreator.model.View;

public class Main {

	private List<Classe> classes;

	public Main() {
		String path = System.getProperty("user.home") + File.separator
				+ "Desktop";
		String site = "nipp_teste";
		new SiteInfo(path, site);

		classes = new ArrayList<Classe>();
		this.carregarClasses();
		this.makeModel();
		this.makeDatabase();
		this.makeDBScript();
		this.makeTable();
		this.makeControl();

	}

	private void carregarClasses() {
		classes.add(getPessoa());
		classes.add(getUsuario());
		classes.add(getCargo());
		classes.add(getAtividade());
		classes.add(getServico());
		classes.add(getLocais());
		classes.add(getPatrimonio());
		classes.add(getChamado());
		classes.add(getTramite());
		classes.add(getTesesEDissertacoes());
		classes.add(getRelacaoAtividadePessoa());
		classes.add(getRelacaoCargoPessoa());
	}

	private void makeControl() {
		for (Classe classe : this.classes) {
			new MakeControl(classe);
		}
	}

	private void makeTable() {
		for (Classe classe : this.classes) {
			new MakeView(classe);
			View tv = new View(classe);
			if (tv.getClasseFinal() != null) {
				System.out.println("A classe " + classe.getNome()
						+ " tem referências");
				new MakeView(tv.getClasseFinal());
			}
		}
	}

	private void makeDBScript() {
		MakeDBScript d = new MakeDBScript();
		for (Classe classe : this.classes) {
			d.addClasses(classe);
		}
		d.criar();
	}

	private void makeDatabase() {
		for (Classe classe : this.classes) {
			new MakeDatabase(classe);
			View tv = new View(classe);
			if (tv.getClasseFinal() != null) {
				System.out.println("A classe " + classe.getNome()
						+ " tem referências");
				new MakeDatabase(tv.getClasseFinal());
			}
		}
	}

	private void makeModel() {
		for (Classe classe : this.classes) {
			new MakeModel(classe);
			View tv = new View(classe);
			if (tv.getClasseFinal() != null) {
				System.out.println("A classe " + classe.getNome()
						+ " tem referências");
				new MakeModel(tv.getClasseFinal());
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

	private Classe getPessoa() {
		Variavel cod_pessoa = new Variavel("cod_pessoa", Tipo.INTEIRO, true);

		Variavel nome = new Variavel("nome", Tipo.STRING, 200, true);
		Variavel sexo = new Variavel("sexo", Tipo.STRING, true);
		Variavel data_nascimento = new Variavel("data_nascimento", Tipo.DATA);
		Variavel email = new Variavel("email", Tipo.STRING, 100);
		Variavel cpf = new Variavel("cpf", Tipo.STRING, 14);
		Variavel curriculo_lattes = new Variavel("curriculo_lattes",
				Tipo.STRING, 200);
		Variavel numero_funcional = new Variavel("numero_funcional",
				Tipo.STRING, 200);
		Variavel foto = new Variavel("foto", Tipo.STRING, 200);

		Classe pessoa = new Classe("Pessoa");
		pessoa.setChavePrimaria(cod_pessoa);
		pessoa.add(nome);
		pessoa.add(sexo);
		pessoa.add(data_nascimento);
		pessoa.add(email);
		pessoa.add(cpf);
		pessoa.add(curriculo_lattes);
		pessoa.add(numero_funcional);
		pessoa.add(foto);

		return pessoa;
	}

	private Classe getUsuario() {
		Variavel cod_usuario = new Variavel("cod_usuario", Tipo.INTEIRO, true);
		Variavel login = new Variavel("login", Tipo.STRING, 50, true);
		Variavel senha = new Variavel("senha", Tipo.STRING, 50, true);
		Variavel funcionario = new Variavel("funcionario", Tipo.INTEIRO, true);
		funcionario.setChaveEstrangeira(this.getPessoa());

		Classe usuario = new Classe("Usuario");
		usuario.setChavePrimaria(cod_usuario);
		usuario.add(login);
		usuario.add(senha);
		usuario.add(funcionario);
		return usuario;
	}

	private Classe getCargo() {

		Variavel cod_cargo = new Variavel("cod_cargo", Tipo.INTEIRO, true);
		Variavel varCargo = new Variavel("cargo", Tipo.STRING, 50, true);
		Variavel varPrioridade = new Variavel("prioridade", Tipo.INTEIRO);

		Classe cargo = new Classe("Cargo");
		cargo.setChavePrimaria(cod_cargo);
		cargo.add(varCargo);
		cargo.add(varPrioridade);

		return cargo;
	}

	private Classe getAtividade() {
		Variavel cod_atividade = new Variavel("cod_atividade", Tipo.INTEIRO,
				true);
		Variavel atividade = new Variavel("atividade", Tipo.STRING, 70, true);

		Classe usuario = new Classe("Atividade");
		usuario.setChavePrimaria(cod_atividade);
		usuario.add(atividade);

		return usuario;
	}

	private Classe getServico() {
		Classe servico = new Classe("Servico");

		Variavel cod_servico = new Variavel("cod_cargo", Tipo.INTEIRO, true);
		Variavel varServico = new Variavel("servico", Tipo.STRING, 50, true);

		servico.setChavePrimaria(cod_servico);
		servico.add(varServico);
		return servico;
	}

	private Classe getLocais() {
		Classe locais = new Classe("Locais");
		Variavel cod_local = new Variavel("cod_local", Tipo.INTEIRO, true);
		Variavel local = new Variavel("local", Tipo.STRING, 100, true);
		locais.setChavePrimaria(cod_local);
		locais.add(local);
		return locais;
	}

	private Classe getPatrimonio() {
		Classe pat = new Classe("Patrimonio");
		Variavel cod_patrimonio = new Variavel("cod_patrimonio", Tipo.INTEIRO,
				true);
		Variavel empresa = new Variavel("empresa", Tipo.STRING, 20, true);
		Variavel patrimonio = new Variavel("patrimonio", Tipo.STRING, 20, true);
		Variavel responsavel = new Variavel("responsavel", Tipo.INTEIRO, true);
		Variavel localizacao = new Variavel("localizacao", Tipo.INTEIRO, true);
		Variavel marca = new Variavel("marca", Tipo.STRING, 100);
		Variavel modelo = new Variavel("modelo", Tipo.STRING, 100);
		Variavel situacao = new Variavel("situacao", Tipo.STRING, 20, true);
		Variavel dataCadastro = new Variavel("data_cadastro", Tipo.DATA_TEMPO,
				true);
		Variavel observacoes = new Variavel("observacoes", Tipo.TEXT, true);

		responsavel.setChaveEstrangeira(this.getPessoa());
		localizacao.setChaveEstrangeira(this.getLocais());

		pat.setChavePrimaria(cod_patrimonio);
		pat.add(empresa);
		pat.add(patrimonio);
		pat.add(responsavel);
		pat.add(localizacao);
		pat.add(marca);
		pat.add(modelo);
		pat.add(situacao);
		pat.add(dataCadastro);
		pat.add(observacoes);
		return pat;
	}

	private Classe getChamado() {
		Classe usuario = new Classe("Chamado");
		Variavel cod_chamado = new Variavel("cod_chamado", Tipo.INTEIRO, true);
		Variavel pessoa = new Variavel("pessoa", Tipo.INTEIRO, true);
		Variavel inicio = new Variavel("inicio", Tipo.DATA_TEMPO, true);
		Variavel fim = new Variavel("fim", Tipo.DATA_TEMPO);
		Variavel descricao = new Variavel("descricao", Tipo.TEXT, true);
		Variavel conclusao = new Variavel("conclusao", Tipo.TEXT);
		Variavel patrimonio = new Variavel("patrimonio", Tipo.INTEIRO);
		Variavel servico = new Variavel("servico", Tipo.INTEIRO, true);

		pessoa.setChaveEstrangeira(this.getPessoa());
		patrimonio.setChaveEstrangeira(this.getPatrimonio());
		servico.setChaveEstrangeira(this.getServico());

		usuario.setChavePrimaria(cod_chamado);
		usuario.add(pessoa);
		usuario.add(inicio);
		usuario.add(fim);
		usuario.add(descricao);
		usuario.add(conclusao);
		usuario.add(patrimonio);
		usuario.add(servico);

		return usuario;
	}

	private Classe getTramite() {
		Classe usuario = new Classe("Tramite");
		Variavel cod_tramite = new Variavel("cod_tramite", Tipo.INTEIRO, true);
		Variavel ocorrencia = new Variavel("ocorrencia", Tipo.DATA_TEMPO, true);
		Variavel chamado = new Variavel("chamado", Tipo.INTEIRO, true);
		Variavel descricao = new Variavel("descricao", Tipo.TEXT, true);

		chamado.setChaveEstrangeira(this.getChamado());

		usuario.setChavePrimaria(cod_tramite);
		usuario.add(ocorrencia);
		usuario.add(chamado);
		usuario.add(descricao);

		return usuario;
	}

	private Classe getTesesEDissertacoes() {
		Classe teses = new Classe("TesesEDissertacoes");

		Variavel cod_teses_e_dissertacoes = new Variavel(
				"cod_teses_e_dissertacoes", Tipo.INTEIRO, true);

		Variavel curso = new Variavel("curso", Tipo.STRING, 3, true);
		Variavel aluno = new Variavel("aluno", Tipo.STRING, 150, true);
		Variavel titulo = new Variavel("titulo", Tipo.STRING, 500, true);
		Variavel orientador = new Variavel("orientador", Tipo.INTEIRO, true);
		Variavel local_defesa = new Variavel("local_defesa", Tipo.STRING, 100,
				true);
		Variavel data_defesa = new Variavel("data_defesa", Tipo.DATA_TEMPO, 3,
				true);

		orientador.setChaveEstrangeira(this.getPessoa());

		teses.setChavePrimaria(cod_teses_e_dissertacoes);
		teses.add(curso);
		teses.add(aluno);
		teses.add(titulo);
		teses.add(orientador);
		teses.add(local_defesa);
		teses.add(data_defesa);

		return teses;
	}

	private Classe getRelacaoAtividadePessoa() {
		Classe resultado = new Classe("relacao_atividade_pessoa");
		Variavel cod_rel_ati_pess = new Variavel("cod_rel_ati_pess",
				Tipo.INTEIRO, true);
		Variavel pessoa = new Variavel("pessoa", Tipo.INTEIRO, true);
		Variavel atividade = new Variavel("atividade", Tipo.INTEIRO, true);
		Variavel data_cadastro = new Variavel("data_cadastro", Tipo.DATA_TEMPO);
		Variavel validade = new Variavel("validade", Tipo.DATA_TEMPO);
		pessoa.setChaveEstrangeira(this.getPessoa());
		atividade.setChaveEstrangeira(this.getAtividade());

		resultado.setChavePrimaria(cod_rel_ati_pess);
		resultado.add(pessoa);
		resultado.add(atividade);
		resultado.add(data_cadastro);
		resultado.add(validade);
		return resultado;
	}

	private Classe getRelacaoCargoPessoa() {
		Classe resultado = new Classe("relacao_pessoa_cargo");
		Variavel cod_rel_car_pess = new Variavel("cod_rel_car_pess",
				Tipo.INTEIRO, true);
		Variavel cod_pessoa = new Variavel("pessoa", Tipo.INTEIRO, true);
		Variavel cod_cargo = new Variavel("cargo", Tipo.INTEIRO, true);
		Variavel data_cadastro = new Variavel("data_cadastro", Tipo.DATA_TEMPO);
		Variavel validade = new Variavel("validade", Tipo.DATA_TEMPO);
		cod_pessoa.setChaveEstrangeira(this.getPessoa());
		cod_cargo.setChaveEstrangeira(this.getCargo());

		resultado.setChavePrimaria(cod_rel_car_pess);
		resultado.add(cod_pessoa);
		resultado.add(cod_cargo);
		resultado.add(data_cadastro);
		resultado.add(validade);
		return resultado;
	}

}
