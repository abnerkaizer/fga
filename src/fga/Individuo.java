package fga;

import java.util.List;

public interface Individuo {
	List<Individuo> recombinar(Individuo ind);
	
	Individuo mutar();
	int[] getGenes();
	boolean setGene(int i, int value);
	double getAvaliacao();
}
