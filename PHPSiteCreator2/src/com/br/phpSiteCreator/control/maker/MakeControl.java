package com.br.phpSiteCreator.control.maker;

import java.io.File;

import com.br.phpSiteCreator.control.util.StringMananger;
import com.br.phpSiteCreator.model.Arquivo;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.SiteInfo;
import com.br.phpSiteCreator.model.Variavel;

public class MakeControl {

	private Classe classe;
	private Arquivo arq;

	public MakeControl(Classe classe) {
		this.classe = classe;
		String pathName = SiteInfo.getPathName() + File.separator
				+ SiteInfo.getSiteName() + File.separator + "system"
				+ File.separator + "control";
		String fileName = "CTRL_" + classe.getNome() + ".class.php";
		this.arq = new Arquivo(fileName, pathName);
		this.make();
		arq.gravar();
	}

	private void make() {
		this.iniciar();
		arq.addLinha("class CTRL_"+classe.getNome(),1);
		arq.addLinha("{",1);
		this.makeConstruct();
		this.makeCadastrar();
		this.makeEditar();
		this.makeDeletar();
		arq.addLinha("}",1);
		this.finalizar();
	}

	private void iniciar() {
		arq.addFrase("<?php");
	}

	private void finalizar() {
		arq.addLinha("?>");
	}

	private void makeConstruct() {
		arq.addLinha("public function __construct()", 2);
		arq.addLinha("{", 2);
		arq.addLinha("}", 2);
	}

	private void makeCadastrar() {
		int ident = 2;
		arq.addLinha("public function cadastrar()",ident);
		arq.addLinha("{",ident++);
			arq.addLinha("if",ident);
			arq.addFrase("(");
				for(int i = 0; i< classe.getVariaveis().size();i++)
				{
					Variavel v = classe.getVariaveis().get(i);
					if(v.isRequerido())
					{
						if(i>0)
						{
							arq.addFrase(" && ");
						}
						arq.addFrase("isset($_POST['"+v.getNome()+"'])");
					}
				}
			arq.addFrase(")");
			arq.addLinha("{",ident++);
			arq.addLinha("if",ident);
			arq.addFrase("(");
				for(int i = 0; i< classe.getVariaveis().size();i++)
				{
					Variavel v = classe.getVariaveis().get(i);
					if(v.isRequerido())
					{
						if(i>0)
						{
							arq.addFrase(" && ");
						}
						arq.addFrase("(strlen($_POST['"+v.getNome()+"']) > 0)");
					}
				}
			arq.addFrase(")");
			arq.addLinha("{",ident++);
				arq.addLinha("$classe = new "+classe.getNome()+"();",ident);
				for(Variavel v : classe.getVariaveis())
				{
					arq.addLinha("$classe->set"+StringMananger.capitalize(v.getNome()+"($_POST['"+v.getNome()+"']);"),ident);
				}
				ident--;
			arq.addLinha("}",ident--);
			arq.addLinha("}",ident--);
		arq.addLinha("}",ident);
	}

	private void makeEditar() {
		// TODO implementar
	}

	private void makeDeletar() {
		// TODO implementar
	}

}
