package gov.ufal.br;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Random;

public class EvaluatedNodules {

	private String malignance;
	private Set<Nodule> aleatoryNodulesByMalignance = new HashSet<>();
	private Set<Nodule> allNodules;
	private Precision precisionByRanking;
	
	public EvaluatedNodules(String malignance, Set<Nodule> allNodules, int qntAleatoryNodules) {
		super();
		this.malignance = malignance;
		this.allNodules = allNodules;
		setAleatoryNodulesByMalignance(malignance, qntAleatoryNodules);
	}
	
	public Precision getPrecisionByRanking() {
		return precisionByRanking;
	}
	
	public Set<Nodule> getAleatoryNodulesByMalignance() {
		return aleatoryNodulesByMalignance;
	}
	
	public Set<Nodule> setAleatoryNodulesByMalignance(String malignance, int qntAleatoryNodules) {

		Random rnd;
		Set<Nodule> nodules = new HashSet<>();
		Nodule nodule = null;
		int n;
		String noduleMalignance;
		while (nodules.size() < qntAleatoryNodules) {
			noduleMalignance = "";
			while (malignance.equals(noduleMalignance)) {
				rnd = new Random();
				n = rnd.nextInt(this.allNodules.size());
				nodule = (Nodule) allNodules.toArray()[n];
				noduleMalignance = nodule.getMalignance();
			}
			nodules.add(nodule);
		}
		return nodules;
	}

	public Set<Nodule> getAllNodules() {
		return allNodules;
	}

	public void setAllNodules(Set<Nodule> allNodules) {
		this.allNodules = allNodules;
	}

	/** Para cada nódulo dos n nódulos aleatórios escolhidos,
	 * cálcula para cada lista de nódulos vizinhos (definidos por features)
	 * a média das precisões por ranking dos m nódulos vizinhos
	 **/
	public void setPrecisionByRanking(Set<Nodule> nodules) {
		/**Verifica a quantidade de tipos de nódulos vizinhos por feature
		 * e assume que todos os nódulos aleatórios tem a mesma quantidade de tipos vizinhos **/
		int qntOfNoduleNearestNodules = nodules.iterator().next().getNearbyNodules().size();
		List<List<Double>> precisionForAllNodules;
		List<Double> averagePrecisionForAllNodules;
		String featureName = "";
		String featureNameAux = "";
		for (int j = 0; j< qntOfNoduleNearestNodules; j++){
			precisionForAllNodules = new ArrayList<>();
			averagePrecisionForAllNodules = new ArrayList<>();
			featureName = nodules.iterator().next().getNearbyNodules().get(j).getCharacteristic();
			/**Para cada nódulo aleatório,
			 * para cada tipo de nódulos vizinhos por feature, 
			 * adiciona a lista das precisões dos n nódulos vizinhos retornados em uma lista de listas**/
			System.out.println("Calculando a média das precisões de " + featureName + " por ranking");
			for(Nodule n : nodules) {
				featureNameAux = n.getNearbyNodules().get(j).getCharacteristic();
				if(featureName.equals(featureNameAux)) {
					precisionForAllNodules.add(n.getNearbyNodules().get(j).getPrecisionOfNearestNodules().getPrecisions());	
					//System.out.println(n.getNearbyNodules().get(j).getPrecision());
				}
				else {
					System.err.println("Ordenação da lista de nódulos vizinhos por feature não confere para todos os nódulos aleatórios");
				}
			}
			/** Faz a média das precisões pelo index de cada lista de precisões,
			 * ou seja, faz a média por ranking dos nódulos vizinhos por feature
			 * Ex.: 					1/1, 1/2, 2/3, ... m nódulos vizinhos por feature
			 * 							1/1, 2/2, 3/3, ...
			 * 			 						.
			 *n nódulos aleatórios				.
			 * 									.
			 * média: 				(1/1+1/1)/n, (1/2+2/2)/n, ...**/
			List<Double> precisionByNoduleRanking;
			for (int i = 0; i < nodules.size(); i++) {
				precisionByNoduleRanking = new ArrayList<>();
				for (List<Double> precision : precisionForAllNodules) {
					precisionByNoduleRanking.add(precision.get(i));
				}
				averagePrecisionForAllNodules
				.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
				//System.out.println("Array por index: " + precisionByNoduleRanking + " Media: " + precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			}	
			System.out.println("Array das medias das precisoes: " + averagePrecisionForAllNodules);
			this.averageRankingPrecisionByFeature.put(featureName, averagePrecisionForAllNodules);
		}
	}
	
	/** Cria uma lista de nódulos vizinhos para cada feature 
	 * e uma lista de nódulos vizinhos com as features integradas (caso haja mais de uma feature) **/
	public void setNearbyNodulesByFeatures(List<GroupFeaturesEnum> features, Distances distanceType, Set<Nodule> aleatoryNodules, Set<Nodule> allNodules, int qntRanking) {
		for (Nodule nodule : aleatoryNodulesByMalignance) {			
			NearestNodules listNearestNodulesByIntegratedFeatures = new NearestNodules(nodule, allNodules, distanceType, features, qntRanking);
			nodule.addNearestNodules(listNearestNodulesByIntegratedFeatures);
			/*System.out.println("Identificação: "+ identification + "\n"
						+ "Precisão: " + listNearestNodulesByIntegratedFeatures.getPrecision() + "\n"
						+ "Recall: " + listNearestNodulesByIntegratedFeatures.getRecall());
			*/	
			
		}
	}
}
