/**
 * 
 */
package com.br.PHPSiteCreator.control.builders;

import java.io.File;

import com.br.PHPSiteCreator.model.Classe;
import com.br.PHPSiteCreator.model.Variavel;

/**
 * @author vitor.padovan89@gmail.com
 *
 */
public class PHPControl extends ConstrutorBasico {

	/**
	 * @param classe
	 * @param pastaConstrutor
	 * @param iniciaisConstrutor
	 */
	public PHPControl(Classe classe) {
		super(classe, "system" + File.separator + "control", "CTRL_",
				".class.php");
		// NOT_USABLE não precisa disto
	}

	@Override
	public void variaveis() {
		// NOT_USABLE não precisa disto

	}

	@Override
	public void getsAndSets() {
		// NOT_USABLE não precisa disto
	}

	@Override
	public void corpo() {
		this.salvar();
		this.excluir();
		this.atualizar();

	}

	private void salvar() {
		this.iniciarFuncao("cadastrar");
			arquivo.addLinha("if(",3);
			for(Variavel v : this.classe.getVariaveis())
			{
				if(v.isRequerido())
				{
					if(this.classe.getVariaveis().indexOf(v)>0)
					{
						arquivo.addFrase("&&");
					}
					arquivo.addFrase("isset($_POST['"+v.getNome()+"'])");
				}
			}
			arquivo.addFrase(");");
			arquivo.addLinha("{",3);
				arquivo.addLinha("if(",4);
				for(Variavel v : this.classe.getVariaveis())
				{
					if(v.isRequerido())
					{
						if(this.classe.getVariaveis().indexOf(v)>0)
						{
							arquivo.addFrase("&&");
						}
						arquivo.addFrase("!empty($_POST['"+v.getNome()+"'])");
					}
				}
				arquivo.addFrase(");");
				arquivo.addLinha("{",4);
					arquivo.addLinha("$d = new DB_"+this.classe.getNome()+";",5);
					arquivo.addLinha("$id = $d->salvar(",5);
					for(Variavel v : this.classe.getVariaveis())
					{
						if(this.classe.getVariaveis().indexOf(v)>0)
						{
							arquivo.addFrase(", $_POST['"+v.getNome()+"']");
						}
						else
						{
							arquivo.addFrase("$_POST['"+v.getNome()+"']");
						}
					}
					arquivo.addFrase(");");
					arquivo.addLinha("return $id;",5);
				arquivo.addLinha("}",4);
				
			arquivo.addLinha("}",3);
		this.finalizarFuncao();
	}

	private void excluir() {
		// THINK implementar o excluir dos itens
	}

	private void atualizar() {
		// THINK implementar o atualizar os itens
	}

}
