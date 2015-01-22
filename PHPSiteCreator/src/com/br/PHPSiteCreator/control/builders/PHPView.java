/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.io.File;

import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Relacionamento;
import com.br.PHPSiteCreator.model.Tipo;
import com.br.PHPSiteCreator.model.Variavel;
import com.br.PHPSiteCreator.util.Debug;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class PHPView extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	public PHPView(Classe classe) {
		super(classe, "system" + File.separator + "view", "VW_", ".class.php");
		// NOT_USABLE não precisa disto
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#variaveis()
	 */
	@Override
	public void variaveis() {
		for (Variavel v : this.classe.getVariaveis()) {
			arquivo.addLinha("private $show_" + v.getNome() + " = false;", 2);
			arquivo.addLinha("private $" + v.getNome() + "_display_name;", 2);
			arquivo.addLinha("", 2);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.br.PHPSiteCreator.control.builders.ConstrutorBasico#getsAndSets()
	 */
	@Override
	public void getsAndSets() {
		for (Variavel v : this.classe.getVariaveis()) {
			this.addParametroFuncao(v.getNome());
			this.iniciarFuncao("set" + this.capitalize(v.getNome()) + "Name");
			arquivo.addLinha("if(strlen($" + v.getNome() + ")>0)", 3);
			arquivo.addLinha("{", 3);
			arquivo.addLinha("$this->show_" + v.getNome() + " = true;", 4);
			arquivo.addLinha(
					"$this->" + v.getNome() + "_display_name = $" + v.getNome()
							+ ";", 4);
			arquivo.addLinha("}", 3);
			this.finalizarFuncao();
			// arquivo.addLinha("private set"+this.capitalize(v.getNome())+"Name;",2);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.br.PHPSiteCreator.control.builders.ConstrutorBasico#corpo()
	 */
	@Override
	public void corpo() {
		this.clearShows();
		this.comboBox();
		this.checkBox();
		this.tabela();
		this.cadastro();
	}

	public void clearShows() {
		this.iniciarFuncao("clearShow");
		for (Variavel v : classe.getVariaveis()) {
			arquivo.addLinha("$this->" + v.getNome() + "_display_name = null;",
					3);
			arquivo.addLinha("$this->show_" + v.getNome() + " = false;", 3);
			arquivo.proximaLinha();
		}
		this.finalizarFuncao();
	}

	public void comboBox() {
		this.iniciarFuncao("getCmb");
		arquivo.addLinha("$d = new DB_" + classe.getNome() + ";", 3);
		arquivo.addLinha("$l = $d->getLista();", 3);
		arquivo.addLinha("if(is_null($l))", 3);
		arquivo.addLinha("{", 3);
		arquivo.addLinha("return;", 4);
		arquivo.addLinha("}", 3);
		arquivo.addLinha("$l = $d->getLista();", 3);
		arquivo.addLinha("$s = '<select name=\"cmb" + classe.getNome()
				+ "\" id=\"cmb" + classe.getNome() + "\">';", 3);
		arquivo.addLinha("for($i = 0; $i<$l->getSize();$i++)", 3);
		arquivo.addLinha("{", 3);
		arquivo.addLinha("$t = $l->get($i);", 4);
		arquivo.addLinha(
				"$s .= '<option value=\"'.$t->get"
						+ this.capitalize(classe.getChavePrimaria().getNome())
						+ "().'\">';", 4);
		for (Variavel v : classe.getVariaveis()) {
			arquivo.addLinha("if($show_" + v.getNome() + ")", 4);
			arquivo.addLinha("{", 4);
			if (classe.getVariaveis().indexOf(v) > 0) {
				arquivo.addLinha(
						"$s .= \" - \".$t->get" + this.capitalize(v.getNome())
								+ "();", 5);
			} else {
				arquivo.addLinha("$s .= $t->get" + this.capitalize(v.getNome())
						+ "();", 5);
			}
			arquivo.addLinha("}", 4);
		}
		arquivo.addLinha("$s .= '</option>';", 4);
		arquivo.addLinha("}", 3);
		arquivo.addLinha("$s .= '</select>';", 3);
		arquivo.addLinha("$this->clearShow();", 3);
		arquivo.addLinha("return $s;", 3);
		this.finalizarFuncao();
	}

	public void checkBox() {
		this.iniciarFuncao("getChk");
		arquivo.addLinha("$d = new DB_" + classe.getNome() + ";", 3);
		arquivo.addLinha("$l = $d->getLista();", 3);
		arquivo.addLinha("if(is_null($l))", 3);
		arquivo.addLinha("{", 3);
		arquivo.addLinha("return;", 4);
		arquivo.addLinha("}", 3);
		arquivo.addLinha("$l = $d->getLista();", 3);
		arquivo.addLinha("$s = '';",3);
		//arquivo.addLinha("$s .= '<select name=\"cmb" + classe.getNome()+ "\" id=\"cmb" + classe.getNome() + "\">';", 3); NOT_USABLE
		arquivo.addLinha("for($i = 0; $i<$l->getSize();$i++)", 3);
		arquivo.addLinha("{", 3);
		arquivo.addLinha("$t = $l->get($i);", 4);
		//<input name="cargo[]" value="19" type="checkbox"> NOT_USABLE
		arquivo.addLinha("$s .= '<input name=\"cmb" + classe.getNome()+ "[]\" id=\"cmb" + classe.getNome() + "'.$i.'\">';", 4);
		for (Variavel v : classe.getVariaveis()) {
			arquivo.addLinha("if($show_" + v.getNome() + ")", 4);
			arquivo.addLinha("{", 4);
			if (classe.getVariaveis().indexOf(v) > 0) {
				arquivo.addLinha("$s .= \" - \".$t->get" + this.capitalize(v.getNome())+ "();", 5);
			} else {
				arquivo.addLinha("$s .= $t->get" + this.capitalize(v.getNome())	+ "();", 5);
			}
			arquivo.addLinha("}", 4);
		}
		//arquivo.addLinha("$s .= '</option>';", 4);
		arquivo.addLinha("$s .= '<\\br>';",4);
		arquivo.addLinha("}", 3);
		
		//arquivo.addLinha("$s .= '</select>';", 3); NOT_USABLE
		arquivo.addLinha("$this->clearShow();", 3);
		arquivo.addLinha("return $s;", 3);
		this.finalizarFuncao();
	}

	public void tabela() {
		this.iniciarFuncao("getTable");
			arquivo.addLinha("$d = new DB_"+classe.getNome()+";",3);
			arquivo.addLinha("$l = $d->getLista();",3);
			arquivo.addLinha("if(is_null($l))",3);
			arquivo.addLinha("{return;}",3);
			arquivo.addLinha("$s = '<table>';",3);
				arquivo.addLinha("$s .= '<tr>'",4);
				for(Variavel v : classe.getVariaveis())
				{
					arquivo.addLinha("if($show_"+v.getNome()+")",4);
					arquivo.addLinha("{",4);
						arquivo.addLinha("$s .= '<th>';",5);
							arquivo.addLinha("$s .= $this->"+v.getNome()+"_display_name()",6);
							arquivo.addLinha("$s .= '</th>';",5);
					arquivo.addLinha("}",4);
				}
				arquivo.addLinha("$t = $l->get($i);",4);
				arquivo.addLinha("$s .= '</tr>'",4);
				arquivo.addLinha("for($i = 0; $i<$l->getSize();$i++)",4);
				arquivo.addLinha("{",4);
					arquivo.addLinha("$s .= '<tr>';",5);
						for(Variavel v : classe.getVariaveis())
						{
							arquivo.addLinha("if($show_"+v.getNome()+")",5);
							arquivo.addLinha("{",5);
								arquivo.addLinha("$s .= '<td>';",6);
									arquivo.addLinha("$s .= $t->get"+capitalize(v.getNome())+"();",7);
								arquivo.addLinha("$s .= '</td>';",6);
							arquivo.addLinha("}",5);
						}
					arquivo.addLinha("$s .= '</tr>';",5);
				arquivo.addLinha("}",4);
			arquivo.addLinha("$s .= '</table>';",3);
			arquivo.addLinha("$this->clearShow();", 3);
			arquivo.addLinha("return $s;", 3);
		this.finalizarFuncao();
	}

	public void cadastro() {
		iniciarFuncao("cadastro");
			arquivo.addLinha("#s = '<form>';",3);
				arquivo.addLinha("$s .= '<table>';",4);
					for(Variavel v: classe.getVariaveis())
					{
						arquivo.addLinha("if($show_"+v.getNome()+")",5);
						arquivo.addLinha("{",5);
						arquivo.addLinha("$s .= '<tr>';",5);
							arquivo.addLinha("$s .= '<td>';",6);
								arquivo.addLinha("$s .= $this->"+v.getNome()+"_display_name;",7);
							arquivo.addLinha("$s .= '<td>';",6);
							arquivo.addLinha("$s .= '<td>';",6);
								if(v.isTemChaveEstrangeira())
								{
									arquivo.addLinha("$view = new VW_"+v.getChaveEstrangeira().getClasse().getNome()+"();",7);
								}
								arquivo.addLinha("$s .= "+this.campos(v)+";",7);
							arquivo.addLinha("$s .= '<td>';",6);	
						arquivo.addLinha("$s .= '</tr>';",5);
						arquivo.addLinha("}",5);
						arquivo.proximaLinha();
					}
				arquivo.addLinha("$s .= '</table>';",4);
			arquivo.addLinha("$s .= '</form>';",3);
		arquivo.addLinha("$this->clearShow();", 3);
		arquivo.addLinha("return $s;", 3);
		finalizarFuncao();
	}
	
	private String campos(Variavel campo)
	{
		if(campo.isTemChaveEstrangeira())
		{
			switch(campo.getChaveEstrangeira().getRelacionamento())
			{
			case Relacionamento.MUITOS_MUITOS:
				return "$view->getCmb()";
			case Relacionamento.MUITOS_UM:
				return "$view->getCmb()";
				
			case Relacionamento.UM_MUITOS:
				return "$view->getCmb()";
				
			case Relacionamento.UM_UM:
				return "$view->getCmb()";
			}
		}
		String resultado = "";
		String tipo = "";
		String requerido = "";
		switch(campo.getTipo())
		{
		case Tipo.DATE:
			tipo ="text";
			break;
		case Tipo.DATETIME:
			tipo ="text";
			break;
		case Tipo.EMAIL:
			tipo ="text";
			break;
		case Tipo.INT:
			tipo ="text";
			break;
		case Tipo.VARCHAR:
			tipo ="text";
			break;
		case Tipo.TEXTO:
			return "<textarea></textarea>";
		}
		
		if(campo.isRequerido())
		{
			requerido = "required";
		}
		resultado = "<input type\""+tipo+"\" name=\""+campo.getNome()+"\" "+requerido+"></input>";
		return resultado;
	}

}
