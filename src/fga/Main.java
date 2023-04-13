package fga;

public class Main {

	public static void main(String[] args) {
		double txMutacao = 0.02;
		int nPop = 20;
		int elitismo = 4;
		int nGen = 10000;
		IndFactory factory = new NRainhasIndFactory(20, txMutacao);
		FGA ag = new FGA();
		double startTime = System.currentTimeMillis();
		Individuo melhor = ag.execute(factory, nPop, elitismo, nGen);
		double endTime = System.currentTimeMillis();
		int[] genes = melhor.getGenes();
		System.out.print("Genes: ");
		for (int i = 0; i < genes.length; i++) {
			System.out.print(genes[i] + " ");
		}
		System.out.println();
		System.out.println("Avaliação: " + melhor.getAvaliacao());
		double tempo = (endTime-startTime)/1000;
		System.out.printf("Tempo: %.2f s\n",tempo);
	}

}
