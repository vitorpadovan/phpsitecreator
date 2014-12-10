package com.br.phpSiteCreator.control.maker;

import java.io.File;
import java.util.List;

import com.br.phpSiteCreator.control.util.StringMananger;
import com.br.phpSiteCreator.model.Arquivo;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.SiteInfo;
import com.br.phpSiteCreator.model.Tipo;
import com.br.phpSiteCreator.model.Variavel;

public class MakeDatabase {

	private Classe classe;
	private String s;
	private Arquivo a;
	public MakeDatabase(Classe classe)
	{
		this.classe = classe;
		this.s = "";
		//C:\Users\vitor_000\Desktop\nipp\model
		String pathName = SiteInfo.getPathName()+File.separator+SiteInfo.getSiteName()+File.separator+"system"+File.separator+"database";
		String fileName = "DB_"+classe.getNome()+".class.php";
		this.a = new Arquivo(fileName, pathName);
		this.a.iniciarPHP();
		this.a.addLinha("require_once(\"MySql.class.php\");", 1);
		this.a.addLinha("class DB_"+classe.getNome()+" extends MySql", 1);
		this.a.addLinha("{", 1);
		
		this.makeConstruct();
		this.makeExtrair();
		this.makeInserirItem();
		this.makeSalvar();
		this.makePesquisa();
		this.makePesquisaPorID();
		this.makePesquisaPorIndex();
		this.makeBackup();
		this.makePesquisaPorCampos();
		this.makeDestruct();
		
		
		this.a.addLinha("}", 1);
		this.a.finalizarPHP();
		this.a.gravar();
	}
	
	private void makeConstruct()
	{
		a.addLinha("");
		a.addLinha("public function __construct()",2);
		a.addLinha("{",2);
		a.addLinha("parent::__construct();",3);
		a.addLinha("}",2);
	}
	
	private void makeBackup()
	{
		a.addLinha("public function getBackup()",2);
		a.addLinha("{",2);
		a.addLinha("$s = \"\";",3);
		a.addLinha("$d = new DB_"+classe.getNome()+"();",3);
		a.addLinha("$l = $d->getLista(\""+classe.getChavePrimaria().getNome()+"\");",3);
		a.addLinha("for($i=0;$i<$l->getSize();$i++)",3);
		a.addLinha("{",3);
			a.addLinha("$t = $l->get($i);",4);
			a.addLinha("$sql = new DML_SQL(\""+classe.getNome()+"\");",4);
			for(Variavel v : classe.getVariaveis())
			{
				//$sql->addInsert("cod",$t->getCod(),DML_SQL::Numeric());
				a.addLinha("$sql->addInsert(\""+v.getNome()+"\",$t->get"+StringMananger.capitalize(v.getNome())+"()",4);
				if(v.getTipo()==Tipo.INTEIRO)
				{
					a.addFrase(",DML_SQL::Numeric()");
				}
				a.addFrase(");");
				//+",DML_SQL::Numeric());");
			}
			a.addLinha("$s .= $sql->getInsert();",4);
			a.addLinha("$s .= \"<br />\";",4);
		a.addLinha("}",3);
		a.addLinha("return $s;",3);
		a.addLinha("}",2);
	}
	
	private void makeDestruct()
	{
		a.addLinha("public function __destruct()",2);
		a.addLinha("{",2);
		a.addLinha("parent::__destruct();",3);
		a.addLinha("}",2);
	}
	
	private void makeExtrair()
	{
		a.addLinha("");
		a.addLinha("public function extrair_item($o)",2);
		a.addLinha("{",2);
		List<Variavel> variaveis = classe.getVariaveis();
		a.addLinha("$r = new "+classe.getNome()+"();",3);
		for(Variavel v : variaveis)
		{
			a.addLinha("$r->set"+StringMananger.capitalize(v.getNome())+"($o->"+v.getNome()+");",3);
		}
		a.addLinha("return $r;",3);
		a.addLinha("}",2);
	}
	
	private void makeInserirItem()
	{
		a.addLinha("");
		a.addLinha("public function inserir_item($o)",2);
		a.addLinha("{",2);
		
		a.addLinha("}",2);
	}
	
	private void makePesquisa()
	{
		a.proximaLinha();
		a.addLinha("public function getLista($orderBy = \""+classe.getChavePrimaria().getNome()+"\")",2);
		a.addLinha("{",2);
			a.addLinha("$string_sql = ",3);
			a.addFrase("\"");
				a.addFrase("select * from "+classe.getNome()+" order by $orderBy");
			a.addFrase("\";");
				a.addLinha("return $this->pesquisa($string_sql);",3);
			
		a.addLinha("}",2);
		
		//TODO implementar
	}
	
	private void makePesquisaPorID()
	{
		a.proximaLinha();
		a.addLinha("public function getListaPorID($id)",2);
		a.addLinha("{",2);
			a.addLinha("$sql = \"select * from "+classe.getNome()+" where "+classe.getChavePrimaria().getNome()+" = ",3);
			if(classe.getChavePrimaria().getTipo() != Tipo.INTEIRO)
			{
				a.addFrase("'");
			}
			a.addFrase("$id");
			if(classe.getChavePrimaria().getTipo() != Tipo.INTEIRO)
			{
				a.addFrase("'");
			}
			a.addFrase("\";");
			a.addLinha("return $this->pesquisa($sql);",3);
		a.addLinha("}",2);
	}
	
	private void makeSalvar()
	{
		a.proximaLinha();
		a.addLinha("public function salvar($obj)",2);
		a.addLinha("{",2);
			a.addLinha("$sql = new DML_SQL(\""+classe.getNome()+"\");",3);
			for(Variavel v : classe.getVariaveis())
			{
				a.addLinha("$sql->addInsert(\""+v.getNome()+"\",$obj->get"+StringMananger.capitalize(v.getNome())+"());",3);
			}
			a.addLinha("return $this->inserir($sql);",3);
		a.addLinha("}",2);
	}
	
	private void makePesquisaPorCampos()
	{
		for(Variavel v : classe.getVariaveis())
		{
			a.proximaLinha();
			a.addLinha("public function getListaPor"+StringMananger.capitalize(v.getNome())+"($"+v.getNome()+")",2);
			a.addLinha("{",2);
				a.addLinha("$string_sql = \"select * from "+classe.getNome()+" where "+v.getNome(),3);
				switch(v.getTipo())
				{
				case Tipo.STRING:
					a.addFrase(" like '"+v.getNome()+"%'");
					a.addFrase(" or ");
					a.addFrase(" like '%"+v.getNome()+"'");
					
					break;
					
				}
				a.addFrase("\";");
				a.addLinha("Debug::s(\"a pesquisa foi \".$string_sql);",3);
				a.addLinha("return $this->pesquisa($string_sql);",3);
			a.addLinha("}",2);
		}
	}
	
	private void makeBasico()
	{
		a.proximaLinha();
		a.addLinha("public function getListaPorID($id)",2);
		a.addLinha("{",2);
			
		a.addLinha("}",2);
	}
	
	private void makePesquisaPorIndex()
	{
		//TODO implementar
		
	}
}

