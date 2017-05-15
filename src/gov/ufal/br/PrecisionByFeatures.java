package gov.ufal.br;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrecisionByFeatures {

	Set<Nodule> aleatoryNodules;
	String featureName;
	List<List<Double>> precisionsByRanking;
	List<Double> averageOfPrecisionByRanking;
	Double averagePrecision;

	public PrecisionByFeatures(Set<Nodule> aleatoryNodules, String featureName) {
		super();
		this.aleatoryNodules = aleatoryNodules;
		this.featureName = featureName;
		setPrecisionsByRanking();
		setAverageOfPrecisionByRanking();
		setAveragePrecision();
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public List<List<Double>> getPrecisionsByRanking() {
		return precisionsByRanking;
	}

	/**
	 * Para cada nódulo dos n nódulos aleatórios escolhidos, cálcula para cada
	 * lista de nódulos vizinhos (definidos por features) a média das precisões
	 * por ranking dos m nódulos vizinhos
	 **/
	public void setPrecisionsByRanking() {
		/**
		 * Verifica a quantidade de tipos de nódulos vizinhos por feature e
		 * assume que todos os nódulos aleatórios tem a mesma quantidade de
		 * tipos vizinhos
		 **/
		int qntOfNoduleNearestNodules = aleatoryNodules.iterator().next().getNearbyNodules().size();
		List<List<Double>> precisionForAllNodules = new ArrayList<>();
		String featureNameAux = "";
		for (int j = 0; j < qntOfNoduleNearestNodules; j++) {
			precisionForAllNodules = new ArrayList<>();
			setFeatureName(aleatoryNodules.iterator().next().getNearbyNodules().get(j).getCharacteristic());
			/**
			 * Para cada nódulo aleatório, para cada tipo de nódulos vizinhos
			 * por feature, adiciona a lista das precisões dos n nódulos
			 * vizinhos retornados em uma lista de listas
			 **/
			System.out.println("Calculando a média das precisões de " + featureName + " por ranking");
			for (Nodule n : aleatoryNodules) {
				featureNameAux = n.getNearbyNodules().get(j).getCharacteristic();
				if (featureName.equals(featureNameAux)) {
					precisionForAllNodules.add(n.getNearbyNodules().get(j).getPrecisions());
					// System.out.println(n.getNearbyNodules().get(j).getPrecision());
				} else {
					System.err.println(
							"Ordenação da lista de nódulos vizinhos por feature não confere para todos os nódulos aleatórios");
				}
			}
		}
		this.precisionsByRanking = precisionForAllNodules;
	}

	public Set<Nodule> getAleatoryNodules() {
		return aleatoryNodules;
	}

	public void setAleatoryNodules(Set<Nodule> aleatoryNodules) {
		this.aleatoryNodules = aleatoryNodules;
	}

	public List<Double> getAverageOfPrecisionByRanking() {
		return averageOfPrecisionByRanking;
	}

	public void setAverageOfPrecisionByRanking() {
		/**
		 * Faz a média das precisões pelo index de cada lista de precisões, ou
		 * seja, faz a média por ranking dos nódulos vizinhos por feature Ex.:
		 * 1/1, 1/2, 2/3, ... m nódulos vizinhos por feature 1/1, 2/2, 3/3, ...
		 * . n nódulos aleatórios . . média: (1/1+1/1)/n, (1/2+2/2)/n, ...
		 **/
		List<Double> averagePrecisionForAllNodules = new ArrayList<>();
		List<Double> precisionByNoduleRanking;
		for (int i = 0; i < aleatoryNodules.size(); i++) {
			precisionByNoduleRanking = new ArrayList<>();
			for (List<Double> precision : getPrecisionsByRanking()) {
				precisionByNoduleRanking.add(precision.get(i));
			}
			averagePrecisionForAllNodules
					.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			// System.out.println("Array por index: " + precisionByNoduleRanking
			// + " Media: " + precisionByNoduleRanking.stream().mapToDouble(a ->
			// a).average().orElse(0));
		}
		System.out.println("Array das medias das precisoes: " + averagePrecisionForAllNodules);
		this.averageOfPrecisionByRanking = averagePrecisionForAllNodules;
	}

	public Double getAveragePrecision() {
		return averagePrecision;
	}

	public void setAveragePrecision() {
		this.averagePrecision = averageOfPrecisionByRanking.stream().mapToDouble(a -> a).average().orElse(0);
	}

}
