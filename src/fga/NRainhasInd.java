package fga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NRainhasInd implements Individuo {
	private int[] genes;
	private double txMutacao;
	private double avaliacao;
	private int nRainhas;
	private Random rand;

	public NRainhasInd(Individuo ind) {
		this.genes = ind.getGenes();
	}

	public int[] getGenes() {
		return this.genes;
	}

	public boolean setGene(int i, int value) {
		if (i < this.genes.length) {
			this.genes[i] = value;
			return true;
		}
		return false;
	}

	public double getAvaliacao() {
		if (this.avaliacao < 0) {
			this.avaliacao = 0;
			for (int i = 0; i < this.nRainhas; i++) {
				for (int j = i; j < this.nRainhas; j++) {
					if (j != i) {
						int d = j - i;
						if (this.genes[i] == this.genes[j] || (this.genes[j] - d) == this.genes[i]
								|| (this.genes[j] + d) == this.genes[i]) {
							this.avaliacao++;
						}
					}
				}
			}
		}
		return this.avaliacao;
	}

	public NRainhasInd(int nRainhas, double txMutacao) {
		this.nRainhas = nRainhas;
		this.genes = new int[nRainhas];
		this.txMutacao = txMutacao;
		this.rand = new Random();
		this.avaliacao = -1.0;
		for (int i = 0; i < genes.length; i++) {
			genes[i] = rand.nextInt(nRainhas);
		}
	}

	public Individuo mutar() {
		NRainhasInd mut = new NRainhasInd(this.nRainhas, this.txMutacao);
		for (int i = 0; i < this.nRainhas; i++) {
			double r = Math.random();
			if (r > txMutacao) {
				mut.genes[i] = this.genes[i];
			} else {
				mut.genes[i] = rand.nextInt(this.nRainhas);// entre 0 e nRainhas-1
			}
		}
		return mut;
	}

	public List<Individuo> recombinar(Individuo ind) {
		List<Individuo> list = new ArrayList<Individuo>(2);
		// Gerar uma lista com 2 filhos usando o this e o ind.
		// Fazer a recombinação com 2 cortes aleatorios.

		int tamanhoCorte = nRainhas / 3;
		Individuo f1 = new NRainhasInd(ind);
		Individuo f2 = new NRainhasInd(this);
		boolean cortou = true;
		do {
			int n = rand.nextInt(nRainhas);// Inicio do corte.
			if ((n + tamanhoCorte) <= nRainhas) {
				for (int i = n; i < (n + tamanhoCorte); i++) {
					f1.setGene(i, this.getGenes()[i]);
					f2.setGene(i, ind.getGenes()[i]);
				}
				cortou = false;
			}
		} while (cortou);
		return list;
	}
}
