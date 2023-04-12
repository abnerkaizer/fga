package fga;

public class Main {

	public static void main(String[] args) {
		double txMutacao = 0.15;
		int nPop = 20;
		int elitismo = 4;
		int nGen = 10000;
		IndFactory factory = new NRainhasIndFactory(4, txMutacao);
		Ag ag = new Ag();
		Individuo melhor = ag.execute(factory, nPop, elitismo, nGen);
		int[] genes = melhor.getGenes();
		System.out.print("Genes: ");
		for (int i = 0; i < genes.length; i++) {
			System.out.print(genes[i] + " ");
		}
		System.out.println();
		System.out.println("Avaliação: " + melhor.getAvaliacao());
	}

}
