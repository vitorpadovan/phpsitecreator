package com.br.phpSiteCreator.control.maker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.br.phpSiteCreator.control.util.StringMananger;
import com.br.phpSiteCreator.model.Arquivo;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.SiteInfo;
import com.br.phpSiteCreator.model.Tipo;
import com.br.phpSiteCreator.model.Variavel;

public class MakeView {

	private Classe classe;
	private Arquivo arquivo;
	private List<Classe> referencias;

	public MakeView(Classe classe) {
		super();
		
		this.referencias = classe.getReferencias();
		this.classe = new Classe(classe.getNome());
		this.classe.setChavePrimaria(classe.getChavePrimaria());
		for(Variavel v : classe.getVariaveis())
		{
			this.classe.add(v);
		}
		this.classe.add(classe.getChavePrimaria());
		String fileName = "VW_" + classe.getNome() + ".php";
		String pathName = SiteInfo.getPathName() + File.separator
				+ SiteInfo.getSiteName() + File.separator + "system"
				+ File.separator + "view";
		this.arquivo = new Arquivo(fileName, pathName);
		this.iniciar();
		this.arquivo.gravar();
	}
	
	private void iniciar() {
		this.arquivo.addLinha("<?php");
		this.arquivo.addLinha("class VW_" + classe.getNome(), 1);
		this.arquivo.addLinha("{", 1);
		this.declararVariaveis();
		this.construct();
		this.setters();
		this.makeSetCmb();
		this.makeCmb();
		this.makeChk();
		this.makeTable();
		this.makeCadastroForm();
		this.arquivo.addLinha("}", 1);
		this.arquivo.addLinha("?>");
	
	}

	private void declararVariaveis()
	{
		for (Variavel v : classe.getVariaveis()) {
			arquivo.proximaLinha();
			arquivo.addLinha("private $show_" + v.getNome() + " = false;", 2);
			arquivo.addLinha("private $nome_" + v.getNome() + " = \"\";", 2);
		}
		arquivo.proximaLinha();
		
		for(Classe c : referencias)
		{
			arquivo.addLinha("private $cmb_" + c.getNome()+";", 2);
		}
	}

	private void construct()
	{
		arquivo.proximaLinha();
		arquivo.addLinha("public function __construct()",2);
		arquivo.addLinha("{",2);
			for(Classe ref : referencias)
			{
				arquivo.addLinha("$this->cmb_"+ref.getNome()+" = new VW_"+ref.getNome()+"();",3);
			}
		arquivo.addLinha("}",2);
	}

	private void setters() {
		for (Variavel v : classe.getVariaveis()) {
			arquivo.proximaLinha();
			arquivo.addLinha(
					"public function set"
							+ StringMananger.capitalize(v.getNome())
							+ "($"+v.getNome()+")", 2);
			arquivo.addLinha("{", 2);
			arquivo.addLinha("$this->show_" + v.getNome() + " = true;", 3);
			arquivo.addLinha("$this->nome_" + v.getNome() + " = $"+v.getNome()+";", 3);
			arquivo.addLinha("}", 2);
		}
	}

	private void makeSetCmb()
	{
		for(Classe classe : this.referencias)
		{
			for(Variavel v:classe.getVariaveis())
			{
				arquivo.addLinha("public function setCmb"+classe.getNome()+"($"+v.getNome()+")",2);
				arquivo.addLinha("{",2);
				arquivo.addLinha("$this->cmb_"+classe.getNome()+"->set"+StringMananger.capitalize(v.getNome())+"($"+v.getNome()+");",3);
				//arquivo.addLinha("$this->cmb_"+classe.getNome()+"->set"+StringMananger.capitalize(v.getNome())+"("+v.getNome()+");",3);
				arquivo.addLinha("}",2);				
			}
		}
	}
	
	private void makeCmb() {
		this.arquivo.proximaLinha();
		this.arquivo.addLinha(
				"public function getCmb($nome = \"" + classe.getNome() + "\")",
				2);
		this.arquivo.addLinha("{", 2);
			this.arquivo.addLinha("$d = new DB_" + classe.getNome() + "();",3);
			this.arquivo.addLinha("$l = $d->getLista();",3);
			this.arquivo.addLinha("$s = '<select name=\"cmb_"+classe.getNome()+"\" id=\"cmb_"+classe.getNome()+"\">';",3);
			this.arquivo.addLinha("for($i =0; $i<$l->getSize();$i++)",3);
			this.arquivo.addLinha("{",3);
				arquivo.addLinha("$item = $l->get($i);",4);
				String nome = StringMananger.capitalize(this.classe.getChavePrimaria().getNome());
				this.arquivo.addLinha("$s .= '<option value=\"'.$item->get"+nome+"().'\">';",4);
				for(Variavel v : this.classe.getVariaveis())
				{
					arquivo.addLinha("if($show_"+v.getNome()+")",4);
					arquivo.addLinha("{",4);
						arquivo.addLinha("$s .= $item->get"+nome+"();",5);
					arquivo.addLinha("}",4);
					arquivo.addLinha("$s .= \" \";",4);
					arquivo.addLinha("");
					//TODO parei aqui 10/12/2014 - 17:41
				}
				arquivo.addLinha("$s .= '</option>';",4);
			this.arquivo.addLinha("}",3);
			this.arquivo.addLinha("$s .= \"</select>\";",3);
			this.arquivo.addLinha("return $s;",3);
		this.arquivo.addLinha("}", 2);
		for (Variavel v : classe.getVariaveis()) {
		}
	}

	private void makeChk()
	{
		//TODO implementar
	}
	
	private void makeTable() {
		arquivo.proximaLinha();
		arquivo.addLinha("public function getTabela", 2);
		arquivo.addFrase("(");
		/*
		 * for(int i = 0; i< classe.getVariaveis().size();i++) { Variavel v =
		 * classe.getVariaveis().get(i); if(i>0) { arquivo.addFrase(","); }
		 * arquivo.addFrase("$show_"+v.getNome()+" = NULL"); }
		 */
		arquivo.addFrase(")");
		arquivo.addLinha("{", 2);
		// iniciando o banco
		arquivo.addLinha("$d = new DB_" + classe.getNome() + "();", 3);
		arquivo.addLinha("$l = $d->getLista();", 3);
		arquivo.addLinha("$s = \"<table>\";",3);
		// Colocando os titulos
		arquivo.addLinha("$s .= \"<tr>\";", 3);
		for (int i = 0; i < classe.getVariaveis().size(); i++) {
			Variavel v = classe.getVariaveis().get(i);
			arquivo.addLinha("if($this->show_" + v.getNome() + ")", 4);
			arquivo.addLinha("{", 4);
			arquivo.addLinha("$s .= \"<th>\";", 5);
			arquivo.addLinha("$s .= $this->nome_" + v.getNome() + ";", 6);
			arquivo.addLinha("$s .= \"</th>\";", 5);
			arquivo.addLinha("}", 4);
		}
		arquivo.addLinha("$s .= \"</tr>\";", 3);
		arquivo.addLinha("for($i = 0; $i<$l->getSize();$i++)", 3);
		arquivo.addLinha("{", 3);
		arquivo.addLinha("$o =  $l->get($i);", 4);
		arquivo.addLinha("$s .=  '<tr>';", 4);
		for (int i = 0; i < classe.getVariaveis().size(); i++) {
			Variavel v = classe.getVariaveis().get(i);
			arquivo.addLinha("if($this->show_" + v.getNome() + ")", 4);
			arquivo.addLinha("{", 4);
			arquivo.addLinha("$s .= \"<td>\";", 5);
			arquivo.addLinha(
					"$s .= $o->get" + StringMananger.capitalize(v.getNome())
							+ "();", 7);
			arquivo.addLinha("$s .= \"</td>\";", 5);
			arquivo.addLinha("}", 4);
		}
		arquivo.addLinha("$s .=  '</tr>';", 4);
		arquivo.addLinha("}", 3);
		arquivo.addLinha("$s .= \"</table>\";", 3);
		arquivo.addLinha("return $s;", 3);
		arquivo.addLinha("}", 2);
	}

	private void makeCadastroForm() {
		arquivo.proximaLinha();
		arquivo.addLinha("public function getCadastro", 2);
		arquivo.addFrase("(");
			arquivo.addFrase("$action=NULL,$method=\"post\"");
		arquivo.addFrase(")");
		arquivo.addLinha("{", 2);
		arquivo.addLinha("$s = '<form action=\"'.$action.'\" method=\"'.$method.'\">';",3);
			arquivo.addLinha("$s .= '<table>';",3);
				for(Variavel v : classe.getVariaveis())
				{
					arquivo.addLinha("if($this->show_"+v.getNome()+")",4);
					arquivo.addLinha("{",4);
						arquivo.addLinha("$s .= '<tr>';",5);
							arquivo.addLinha("$s .= '<td>';",6);
								arquivo.addLinha("$s .= $this->nome_"+v.getNome()+";",7);
							arquivo.addLinha("$s .= '</td>';",6);
							arquivo.addLinha("$s .= '<td>';",6);
							if(v.isExisteChaveEstrangeira())
							{
								arquivo.addLinha("$cmb = new VW_"+v.getChaveEstrangeira().getNome()+"();",7);
								arquivo.addLinha("$s .= $cmb->getCmb();",7);
							}
							else
							{
								switch(v.getTipo())
								{
								case Tipo.STRING:
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
									arquivo.addFrase("input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\"");
									if(v.isRequerido())
									{
										arquivo.addFrase(" required");
									}
									arquivo.addFrase(" class=\"maxsize\"");
									arquivo.addFrase(">");
									arquivo.addFrase("';");
									break;
								case Tipo.TEXT:
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
										arquivo.addFrase("textarea name=\""+v.getNome()+"\" id=\""+v.getNome()+"\"");
										if(v.isRequerido())
										{
											arquivo.addFrase(" required");
										}
									arquivo.addFrase(">");
									arquivo.addFrase("</textarea>");
									arquivo.addFrase("';");
									break;
								case Tipo.DATA:
									//arquivo.addLinha("$s .= "'<input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\" />'",7);
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
										arquivo.addFrase("input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\" ");	
										if(v.isRequerido())
										{
											arquivo.addFrase(" required");
										}
									arquivo.addFrase("/>");
									arquivo.addFrase("';");
									break;
								case Tipo.EMAIL:
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
										arquivo.addFrase("input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\" ");
										if(v.isRequerido())
										{
											arquivo.addFrase(" required");
										}
									arquivo.addFrase("/>");
									arquivo.addFrase("';");
									break;
								case Tipo.INTEIRO:
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
										arquivo.addFrase("input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\" ");
										if(v.isRequerido())
										{
											arquivo.addFrase(" required");
										}
									arquivo.addFrase("/>");
									arquivo.addFrase("';");
									break;
								case Tipo.DATA_TEMPO:
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
										arquivo.addFrase("input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\" ");
										if(v.isRequerido())
										{
											arquivo.addFrase(" required");
										}
									arquivo.addFrase("/>");
									arquivo.addFrase("';");
									break;
								case Tipo.DINHEIRO:
									arquivo.addLinha("$s .= ",7);
									arquivo.addFrase("'");
									arquivo.addFrase("<");
										arquivo.addFrase("input type=\"text\" name=\""+v.getNome()+"\" id=\""+v.getNome()+"\" ");
										if(v.isRequerido())
										{
											arquivo.addFrase(" required");
										}
									arquivo.addFrase("/>");
									arquivo.addFrase("';");
									break;
								default:
									arquivo.addFrase("<H1>ERROR</H1>");
									break;
								}
							}
							arquivo.addLinha("$s .= '</td>';",6);
						arquivo.addLinha("$s .= '</tr>';",5);
					arquivo.addLinha("}",4);
				}
			arquivo.addLinha("$s .= '</table>';",3);
		arquivo.addLinha("$s .= '</form>';",3);
		arquivo.addLinha("return $s;",3);
		arquivo.addLinha("}", 2);
	}
}
