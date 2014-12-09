package com.br.phpSiteCreator.control.maker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.br.phpSiteCreator.model.Arquivo;
import com.br.phpSiteCreator.model.Classe;
import com.br.phpSiteCreator.model.SiteInfo;
import com.br.phpSiteCreator.model.Tipo;
import com.br.phpSiteCreator.model.Variavel;

public class MakeDBScript {
	
	private List<Classe> classes;
	private Arquivo ar;
	
	public MakeDBScript()
	{
		this.classes = new ArrayList<Classe>();
		String pathName = SiteInfo.getPathName()+File.separator+SiteInfo.getSiteName()+File.separator+"system"+File.separator+"docs";
		String fileName = "mysql.sql";
		this.ar = new Arquivo(fileName, pathName);
	
		ar.addLinha("drop database if exists "+SiteInfo.getSiteName()+";");
		ar.addLinha("create database "+SiteInfo.getSiteName()+";");
		ar.addLinha("use "+SiteInfo.getSiteName()+";");
	}
	
	public void addClasses(Classe classe)
	{
		this.classes.add(classe);
	}
	
	public void criar()
	{
		for(Classe c : this.classes)
		{
			this.criarTable(c);
		}
		
		for(Classe c : this.classes)
		{
			MakeDBView v = new MakeDBView(this.ar, c);
			this.ar = v.getA();
		}
		
		this.ar.gravar();
	}
	
	private void getTipo(Variavel v)
	{
		switch(v.getTipo())
		{
		case Tipo.DATA:
			this.ar.addFrase(" date");
			break;
		case Tipo.STRING:
			this.ar.addFrase(" varchar("+v.getTamanho()+")");
			break;
		case Tipo.INTEIRO:
			this.ar.addFrase(" int");
			break;
		case Tipo.DATA_TEMPO:
			this.ar.addFrase(" datetime");
			break;
		case Tipo.DINHEIRO:
			this.ar.addFrase(" money");
		case Tipo.TEXT:
			this.ar.addFrase(" text");
			break;
		}
	}
	
	private void criarTable(Classe c)
	{
		this.ar.addLinha("");
		this.ar.addLinha("CREATE TABLE "+c.getNome());
		this.ar.addLinha("(");
		
		this.ar.addLinha(c.getChavePrimaria().getNome(),2);
		this.getTipo(c.getChavePrimaria());
		this.ar.addFrase(" not null primary key auto_increment,");
		for(int i = 0; i< c.getVariaveis().size();i++)
		{
			Variavel v = c.getVariaveis().get(i);
			if(i>0)
			{
				this.ar.addFrase(",");
			}
			this.ar.addLinha(v.getNome(),2);
			this.getTipo(v);
			
			if(v.isRequerido())
			{
				this.ar.addFrase(" not null");
			}
		}
		this.ar.addLinha(")engine = innodb;");
	}
}
