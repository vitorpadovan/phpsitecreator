package com.br.PHPSiteCreator.model;

public class ChaveEstrangeira {

	private Classe classe;
	private int relacionamento;
	private Variavel v;

	/**
	 * @param classe
	 * @param relacionamento
	 */
	public ChaveEstrangeira(Variavel v, Classe classe, int relacionamento) {
		super();
		this.classe = classe;
		this.setRelacionamento(relacionamento);
		this.v = v;

	}

	/**
	 * @return the v
	 */
	public Variavel getVariavel() {
		return v;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setVariavel(Variavel v) {
		this.v = v;
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
