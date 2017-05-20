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
		//System.out.println(aleatoryNodules);
		this.aleatoryNodules = aleatoryNodules;
		this.featureName = featureName;
		setPrecisionsByRanking();
		setAverageOfPrecisionByRanking();
		setAveragePrecision();
	}
	
	public PrecisionByFeatures(String featureName) {
		super();
		//System.out.println(aleatoryNodules);
		this.featureName = featureName;
		this.precisionsByRanking = new ArrayList<>();
	}
	
	public void addListOfPrecisionsByFeature(List<Double> listOfPrecisionsByRanking) {
		this.precisionsByRanking.add(listOfPrecisionsByRanking);
		setAverageOfPrecisionByRanking();
		setAveragePrecision();
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	
	public void showPrecisionsByRanking(){
		System.out.println("PRECISOES POR RANKING:");
		for(List<Double> precision : precisionsByRanking) {
			System.out.println(precision);
		}
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
		int index = -1;
		for (int j = 0; j < qntOfNoduleNearestNodules; j++) {
			if(aleatoryNodules.iterator().next().getNearbyNodules().get(j).getCharacteristic().equals(featureName)) {
				index = j;
			}
		}
			//System.out.println(aleatoryNodules);
			/**
			 * Para cada nódulo aleatório, para cada tipo de nódulos vizinhos
			 * por feature, adiciona a lista das precisões dos n nódulos
			 * vizinhos retornados em uma lista de listas
			 **/
		//System.out.println("Calculando a média das precisões de " + featureName + " por ranking");
		for (Nodule n : aleatoryNodules) {
			precisionForAllNodules.add(n.getNearbyNodules().get(index).getPrecisions());
			// System.out.println(n.getNearbyNodules().get(j).getPrecision());
			
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
		//showPrecisionsByRanking();
		List<List<Double>> allAveragePrecisionsOfAllAleatoryNodules = getPrecisionsByRanking();
		System.out.println(allAveragePrecisionsOfAllAleatoryNodules.size());
		int qntRanking = allAveragePrecisionsOfAllAleatoryNodules.get(0).size();
		for(int j = 0; j < qntRanking; j++) {
			precisionByNoduleRanking = new ArrayList<>();
			for(int k=0; k<allAveragePrecisionsOfAllAleatoryNodules.size(); k++){
				List<Double> precision = allAveragePrecisionsOfAllAleatoryNodules.get(k);
				precisionByNoduleRanking.add(precision.get(j));
				//System.out.println("Precisoes do nodulo " + k + " lugar " + j + ": " + precision.get(j));
			}
			//System.out.println("Ok, proximo lugar no ranking...");
			//System.out.println(precisionByNoduleRanking);
			averagePrecisionForAllNodules
			.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			//System.out.println("Media do lugar: "+ precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
		}
		System.out.println("Media geral por ranking para leave one out: " + averagePrecisionForAllNodules);
		this.averageOfPrecisionByRanking = averagePrecisionForAllNodules;
		setAveragePrecision();
		System.out.println("Média da média por ranking: " + averagePrecision);
	}

	public Double getAveragePrecision() {
		return averagePrecision;
	}

	public void setAveragePrecision() {
		this.averagePrecision = averageOfPrecisionByRanking.stream().mapToDouble(a -> a).average().orElse(0);
	}

}
