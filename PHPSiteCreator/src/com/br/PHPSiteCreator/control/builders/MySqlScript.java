/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.util.ArrayList;
import java.util.List;

import com.br.PHPSiteCreator.model.Arquivo;
import com.br.PHPSiteCreator.model.ChaveEstrangeira;
import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.SiteInfo;
import com.br.PHPSiteCreator.model.Tipo;
import com.br.PHPSiteCreator.model.Variavel;
import com.br.PHPSiteCreator.util.Debug;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class MySqlScript {

	private List<Classe> classes;
	private Arquivo arquivo;

	public MySqlScript() {
		this.classes = new ArrayList<Classe>();
		this.arquivo = new Arquivo("MySql", "docs", ".sql");
		//this.iniciar();
		//this.finalizar();
	}
	
	public void addClasse(Classe classe)
	{
		this.classes.add(classe);
	}
	
	public void executar()
	{
		this.iniciar();
		this.finalizar();
	}
	
	private void iniciar()
	{
		this.iniciarArquivo();
			this.montarTable();
		this.finalizarArquivo();
	}
	
	
	private void iniciarArquivo()
	{
		arquivo.addFrase("drop database if exists "+SiteInfo.getSiteName()+";");
		arquivo.addLinha("create database "+SiteInfo.getSiteName()+";");
		arquivo.addLinha("use "+SiteInfo.getSiteName()+";");
	}
	
	private void montarTable()
	{
		for(Classe c : classes)
		{
			arquivo.addLinha("create table "+c.getNome());
			arquivo.addLinha("(");
			arquivo.addLinha(c.getChavePrimaria().getNome() + " "+this.selecionarTipo(c.getChavePrimaria())+" primary key auto_increment,", 1);
			for(Variavel v: c.getVariaveis())
			{
				arquivo.addLinha(v.getNome() + " "+this.selecionarTipo(v), 1);
				if(v.isIndexado())
				{
					arquivo.addFrase(",");
					arquivo.addFrase("index("+v.getNome()+")");
				}
				if(c.getVariaveis().indexOf(v)<c.getVariaveis().size()-1)
				{
					arquivo.addFrase(",");
				}
			}
		
			arquivo.addLinha(")");
			arquivo.addLinha("engine innodb;");
			arquivo.proximaLinha();
			if(c.isChaveEstrangeira())
			{
				makeView(c);
			}
		}
	}
	
	private void makeView(Classe c)
	{
		List<Classe> classes = new ArrayList<Classe>();
		List<ChaveEstrangeira> chaves = new ArrayList<ChaveEstrangeira>();
		arquivo.addLinha("create view view_"+c.getNome()+" as ");
		arquivo.addLinha("select ");
		for(Variavel v: c.getVariaveis())
		{
			arquivo.addLinha(c.getNome()+"."+v.getNome() + " as "+c.getNome()+"_"+v.getNome() );
			if(c.getVariaveis().indexOf(v)<c.getVariaveis().size())
			{
				arquivo.addFrase(",");
			}
			if(v.isTemChaveEstrangeira())
			{
				classes.add(v.getChaveEstrangeira().getClasse());
				chaves.add(v.getChaveEstrangeira());
			}
		}
		
		for(Classe c2 : classes)
		{
			for(Variavel v : c2.getVariaveis())
			{
				arquivo.addLinha(c2.getNome()+"."+v.getNome() + " as "+c2.getNome()+"_"+v.getNome() );
				if(c2.getVariaveis().indexOf(v)<c2.getVariaveis().size()-1)
				{
					arquivo.addFrase(",");
				}
			}
		}
		arquivo.addLinha("from "+c.getNome());
		arquivo.addLinha("left join(");
		for(Classe c2:classes)
		{
			arquivo.addFrase(c2.getNome());
			if(classes.indexOf(c2)<classes.size()-1)
			{
				arquivo.addFrase(",");
			}
		}
		arquivo.addFrase(")");
		arquivo.addLinha("on");
		arquivo.addLinha("(");
		/*for(Classe c2:classes)
		{
			
			//arquivo.addFrase(c2.getNome()+"."+c2.getChavePrimaria().getNome()+ "="+c.getNome()+"."+c.getChavePrimaria().getNome());
			if(classes.indexOf(c2)<classes.size()-1)
			{
				arquivo.addFrase(",");
			}
		}*/
	
		for(ChaveEstrangeira c2 : chaves)
		{
			arquivo.addFrase(c.getNome()+"."+c2.getVariavel().getNome()+"="+c2.getClasse().getNome()+"."+c2.getClasse().getChavePrimaria().getNome());
			if(chaves.indexOf(c2)<chaves.size()-1)
			{
				arquivo.addFrase(" and ");
			}
		}
		
		arquivo.addFrase(")");
		
		arquivo.addFrase(";");
		arquivo.proximaLinha();
	}
	
	private String selecionarTipo(Variavel var)
	{
		String resultado = "";
		switch(var.getTipo())
		{
		case Tipo.DATE:
			resultado = resultado + "date";
			break;
		case Tipo.DATETIME:
			resultado = resultado + "datetime";
			break;
		case Tipo.DINHEIRO:
			resultado = resultado + "money";
			break;
		case Tipo.EMAIL:
			resultado = resultado + "varchar("+var.getTamanho()+")";
			break;
		case Tipo.INT:
			resultado = resultado + "int";
			break;
		case Tipo.TEXTO:
			resultado = resultado + "text";
			break;
		case Tipo.VARCHAR:
			resultado = resultado + "varchar("+var.getTamanho()+")";
			break;
		}
		
		if(var.isRequerido())
			resultado = resultado + " not null";
		if(var.isUnico())
			resultado = resultado + " unique";
		return resultado;
	}
	
	private void finalizarArquivo()
	{
	}
	
	private void finalizar()
	{
		this.arquivo.gravar();
	}

}
