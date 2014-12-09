package com.br.phpSiteCreator.control.maker;

import java.io.File;

import com.br.phpSiteCreator.control.util.StringMananger;
import com.br.phpSiteCreator.model.Arquivo;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.SiteInfo;
import com.br.phpSiteCreator.model.Tipo;
import com.br.phpSiteCreator.model.Variavel;


public class MakeModel {
	
	private Classe classe;
	private String s;
	private Arquivo a;
	
	public MakeModel(Classe classe)
	{
		this.classe = classe;
		this.s = "";
		//C:\Users\vitor_000\Desktop\nipp\model
		String pathName = SiteInfo.getPathName()+File.separator+SiteInfo.getSiteName()+File.separator+"system"+File.separator+"model";
		String fileName = classe.getNome()+".class.php";
		this.a = new Arquivo(fileName, pathName);
		this.a.iniciarPHP();
		this.a.addLinha("class "+classe.getNome(), 1);
		this.a.addLinha("{", 1);
		this.makeVars();
		this.makeConstruct();
		this.makeMasterSetter();
		this.makeJSON();
		this.makeGetsAndSets();
		this.a.addLinha("}", 1);
		this.a.finalizarPHP();
		this.a.gravar();
	}
	
	private void makeVars()
	{
		for(Variavel v : this.classe.getVariaveis())
		{
			this.a.addLinha("private $"+v.getNome()+";",2);
		}		
	}
	
	private void makeGetsAndSets()
	{
		for(Variavel v : this.classe.getVariaveis())
		{
			this.a.addLinha("");
			
			this.a.addLinha("public function get"+StringMananger.capitalize(v.getNome())+"()",2);
			this.a.addLinha("{",2);
				this.a.addLinha("return $this->"+v.getNome()+";",3);
			this.a.addLinha("}",2);
			
			this.a.addLinha("");
			
			this.a.addLinha("public function set"+StringMananger.capitalize(v.getNome())+"($"+v.getNome()+")",2);
			this.a.addLinha("{",2);
				this.a.addLinha("$this->"+v.getNome()+" = $"+v.getNome()+";",3);
			this.a.addLinha("}",2);
		}
	}
	
	private void makeJSON()
	{
		this.a.addLinha("");
		this.a.addLinha("public function getJSON()",2);
		this.a.addLinha("{",2);
		this.a.addLinha("$s = \"{\";",3);
		for(int i = 0; i<this.classe.getVariaveis().size(); i++)
		{
			Variavel v = this.classe.getVariaveis().get(i);
			String s = "";
			s += "$s .= ";
			s += "'";
				s += "\""+v.getNome()+"\"";
					s += ":";
				
				if(v.getTipo() != Tipo
						.INTEIRO)
				{
					s += "\"";
				}
				s += "'.";
				s += "$this->"+v.getNome();
				s += ".'";
				if(v.getTipo() != Tipo.INTEIRO)
				{
					s += "\"";					
				}
			s += "'";
			s += ";";
			a.addLinha(s,3);
		}
		this.a.addLinha("$s .= \"}\";",3);
		this.a.addLinha("return $s;",3);
		this.a.addLinha("}",2);
	}
	
	private void makeConstruct()
	{
		this.a.addLinha("");
		this.a.addLinha("public function __construct",2);
		this.a.addFrase("(");
			for(int i = 0; i < this.classe.getVariaveis().size(); i++)
			{
				Variavel v = classe.getVariaveis().get(i);
				if(i>0)
				{
					a.addFrase(",");
				}
				a.addFrase("$"+v.getNome()+" = NULL");
			}
		this.a.addFrase(")");
		this.a.addLinha("{",2);
			for(int i = 0; i < this.classe.getVariaveis().size(); i++)
			{
				Variavel v = this.classe.getVariaveis().get(i);
				a.addLinha("$this->"+v.getNome()+" = $"+v.getNome()+";", 3);
			}
		this.a.addLinha("}",2);
		
	}
	
	private void makeMasterSetter()
	{
		
	}
	
}
