package fga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Ag {
	Random rand = new Random();

	public Individuo execute(IndFactory factory, int nPop, int elitismo, int nGen) {
		List<Individuo> popIni = new ArrayList<Individuo>(nPop);
		for (int i = 0; i < nPop; i++) {
			popIni.add(factory.getNewIndividuo());
		}

		for (int i = 0; i < nGen; i++) {
			List<Individuo> filhos = new ArrayList<Individuo>(nPop);
			List<Individuo> aux = new ArrayList<Individuo>(nPop);

			aux.addAll(popIni);
			for (int j = 0; j < aux.size() / 2; j++) {
				Individuo p1 = aux.remove(rand.nextInt(aux.size()));
				Individuo p2 = aux.remove(rand.nextInt(aux.size()));
				filhos.addAll(p1.recombinar(p2));
			}
			List<Individuo> mutantes = new ArrayList<Individuo>(nPop);

			for (int j = 0; j < popIni.size(); j++) {
				Individuo p = popIni.get(j);
				mutantes.add(p.mutar());
			}

			List<Individuo> join = new ArrayList<Individuo>(nPop * 3);
			join.addAll(popIni);
			join.addAll(filhos);
			join.addAll(mutantes);

			List<Individuo> newPop = new ArrayList<Individuo>(nPop);
			newPop.addAll(elitismo(join, elitismo));
			newPop.addAll(roleta(join, (nPop - elitismo)));

			popIni = newPop;
		}
		// Ordena em ordem crescente usando avaliação como critério
		sort(popIni);
		// Retorna o melhor
		return popIni.get(0);
	}

	private List<Individuo> elitismo(List<Individuo> join, int n) {
		// Ordena em ordem crescente usando a avaliação como critério
		sort(join);
		List<Individuo> elite = new ArrayList<Individuo>(n);
		// Pega os n melhores.
		for (int i = 0; i < n; i++) {
			elite.add(join.remove(i));
		}
		return elite;
	}

	private List<Individuo> roleta(List<Individuo> join, int n) {
		List<Individuo> escolhidos = new ArrayList<Individuo>(n);
		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (Individuo individuo : join) {
				sum += (1 / individuo.getAvaliacao());
			}

			double r = rand.nextDouble() * sum;
			sum = 0;
			for (Individuo individuo : join) {
				if (sum >= r) {
					escolhidos.add(individuo);
					join.remove(individuo);
					break;
				} else {
					sum += (1 / individuo.getAvaliacao());
				}
			}
		}
		return escolhidos;
	}

	private void sort(List<Individuo> list) {
		Collections.sort(list, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo p1, Individuo p2) {
				return Double.compare(p1.getAvaliacao(), p2.getAvaliacao());
			}
		});
	}
}
