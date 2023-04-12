package fga;

public class NRainhasIndFactory implements IndFactory {
	private int nRainhas;
	private double txMutacao;
	public NRainhasIndFactory(int nRainhas,double txMutacao) {
		this.nRainhas = nRainhas;
		this.txMutacao = txMutacao;
	}

	public Individuo getNewIndividuo() {
		return new NRainhasInd(this.nRainhas,this.txMutacao);
	}
}
