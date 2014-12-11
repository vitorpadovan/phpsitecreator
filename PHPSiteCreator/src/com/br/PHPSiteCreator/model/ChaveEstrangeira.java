package com.br.PHPSiteCreator.model;

public class ChaveEstrangeira {

	private Classe classe;
	private int relacionamento;

	/**
	 * @param classe
	 * @param relacionamento
	 */
	public ChaveEstrangeira(Classe classe, int relacionamento) {
		super();
		this.classe = classe;
		this.setRelacionamento(relacionamento);

	}

	/**
	 * @return the classe
	 */
	public Classe getClasse() {
		return classe;
	}

	/**
	 * @param classe
	 *            the classe to set
	 */
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	/**
	 * @return the relacionamento
	 */
	public int getRelacionamento() {
		return relacionamento;
	}

	/**
	 * @param relacionamento
	 *            the relacionamento to set
	 */
	public void setRelacionamento(int relacionamento) {
		switch (relacionamento) {
		case Relacionamento.MUITOS_MUITOS:
			this.relacionamento = relacionamento;
			break;
		case Relacionamento.MUITOS_UM:
			this.relacionamento = relacionamento;
			break;
		case Relacionamento.UM_MUITOS:
			this.relacionamento = relacionamento;
			break;
		case Relacionamento.UM_UM:
			this.relacionamento = relacionamento;
			break;
		default:
			this.relacionamento = Relacionamento.UM_UM;
			break;
		}
	}

}
