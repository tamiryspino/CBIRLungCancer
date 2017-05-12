package gov.ufal.br;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Random;

public class Evaluator {

	private Set<Nodule> aleatoryMalignantNodules = new HashSet<>();
	private Set<Nodule> aleatoryBenignNodules = new HashSet<>();
	private Set<Nodule> allNodules;
	private int qntEvaluatedNodules;
	private int qntAllBenignNodules;
	private int qntAllMalignantNodules;
	Map<String, List<Double>> averageRankingPrecisionByFeature = new HashMap<>();

	Map<String, List<Double>> averageRankingRecallByFeature = new HashMap<>();

	public Evaluator(Set<Nodule> allNodules, int qntEvaluatedNodules) {
		super();
		this.allNodules = allNodules;
		this.qntEvaluatedNodules = qntEvaluatedNodules;
	}
	
	public Map<String, List<Double>> getAverageRankingPrecisionByFeature() {
		return averageRankingPrecisionByFeature;
	}
	
	public Map<String, List<Double>> getAverageRankingRecallByFeature() {
		return averageRankingRecallByFeature;
	}

	public Set<Nodule> getAleatoryMalignantNodules() {
		this.aleatoryMalignantNodules = setAleatoryNodules(4);
		this.aleatoryMalignantNodules.addAll(setAleatoryNodules(5));
		return this.aleatoryMalignantNodules;
	}
	
	public Set<Nodule> getAleatoryBenignNodules() {
		this.aleatoryBenignNodules = setAleatoryNodules(1);
		this.aleatoryBenignNodules.addAll(setAleatoryNodules(2));
		return this.aleatoryBenignNodules;
	}

	public int getQntEvaluatedNodules() {
		return qntEvaluatedNodules;
	}

	public void setQntEvaluatedNodules(int qntEvaluatedNodules) {
		this.qntEvaluatedNodules = qntEvaluatedNodules;
	}

	public Set<Nodule> setAleatoryNodules(int malignanceProbability) {

		Random rnd;
		Set<Nodule> nodules = new HashSet<>();
		Nodule nodule = null;
		int n;
		int noduleMalignance;
		int qnt = qntEvaluatedNodules / 4;
		while (nodules.size() < qnt) {
			noduleMalignance = 0;
			while (malignanceProbability != noduleMalignance) {
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
	public void setAveragePrecisionByNoduleRanking(Set<Nodule> nodules) {
		int qntListNearestNodulesByFeature = 0;
		/**Verifica a quantidade de tipos de nódulos vizinhos por feature
		 * e assume que todos os nódulos aleatórios tem a mesma quantidade de tipos vizinhos **/
		qntListNearestNodulesByFeature = nodules.iterator().next().getNearbyNodules().size();
		List<List<Double>> precisionForAllNodules;
		List<Double> averagePrecisionForAllNodules;
		String featureName = "";
		String featureNameAux = "";
		for (int j = 0; j< qntListNearestNodulesByFeature; j++){
			precisionForAllNodules = new ArrayList<>();
			averagePrecisionForAllNodules = new ArrayList<>();
			featureName = nodules.iterator().next().getNearbyNodules().get(j).getIdentification();
			/**Para cada nódulo aleatório,
			 * para cada tipo de nódulos vizinhos por feature, 
			 * adiciona a lista das precisões dos n nódulos vizinhos retornados em uma lista de listas**/
			System.out.println("Calculando a média das precisões de " + featureName + " por ranking");
			for(Nodule n : nodules) {
				featureNameAux = n.getNearbyNodules().get(j).getIdentification();
				if(featureName.equals(featureNameAux)) {
					precisionForAllNodules.add(n.getNearbyNodules().get(j).getPrecision());	
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

	public void setAverageRecallForAllNodules(Set<Nodule> nodules) {
		//TODO get(0) deverá ser listNearestNodules para cada um da lista
		int qntListNearestNodulesByFeature = 0;
		for (Nodule n : nodules) {
			qntListNearestNodulesByFeature = n.getNearbyNodules().size();
		}
		List<List<Double>> recallForAllNodules;
		List<Double> averageRecallForAllNodules;
		String featureName = "";
		String featureNameAux = "";
		for (int j = 0; j< qntListNearestNodulesByFeature; j++){
			recallForAllNodules = new ArrayList<>();
			averageRecallForAllNodules = new ArrayList<>();
			featureName = nodules.iterator().next().getNearbyNodules().get(j).getIdentification();
			System.out.println("Calculando a média das revocações de " + featureName + " por ranking");
			for(Nodule n : nodules) {
				featureNameAux = n.getNearbyNodules().get(j).getIdentification();
				if(featureName.equals(featureNameAux)) {
					recallForAllNodules.add(n.getNearbyNodules().get(j).getRecall());	
					//System.out.println(n.getNearbyNodules().get(j).getRecall());
				}
				else {
					System.err.println("Ordenação da lista de nódulos vizinhos por feature não confere para todos os nódulos aleatórios");
				}
			}
			List<Double> recallByNoduleRanking;
			for (int i = 0; i < nodules.size(); i++) {
				recallByNoduleRanking = new ArrayList<>();
				for (List<Double> recall : recallForAllNodules) {
					recallByNoduleRanking.add(recall.get(i));
				}
				averageRecallForAllNodules
				.add(recallByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));

				//System.out.println("Array por index: " + recallByNoduleRanking + "Media: " + recallByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			}
			System.out.println("Array das médias das recall: " + averageRecallForAllNodules);
			this.averageRankingRecallByFeature.put(featureName, averageRecallForAllNodules);
			
		}
	}
/*
	public void setNearbyNodulesByAllFeatures(Set<Nodule> aleatoryNodules, Set<Nodule> allNodules, int qntRanking) {
		for (Nodule nodule : aleatoryNodules) {
			int qntAllNodulesByMalignance = isMalignant(nodule.getMalignance()) ? this.getQntAllMalignantNodules()
					: this.getQntAllBenignNodules();
			List<GroupFeaturesEnum> features = new ArrayList<>();
			features.add(GroupFeaturesEnum.ALL_FEATURES);
			ListNearestNodules listNearestNodules = new ListNearestNodules(nodule, allNodules, Distances.EUCLIDIAN,
					features, qntAllNodulesByMalignance, qntRanking);
			nodule.addListNearestNodules(listNearestNodules);
			// System.out.println(listNearestNodules.showNearbyNodules());
			// System.out.println(listNearestNodules.getPrecision());
		}
	}*/
	
	/** Cria uma lista de nódulos vizinhos para cada feature 
	 * e uma lista de nódulos vizinhos com as features integradas (caso haja mais de uma feature) **/
	public void setNearbyNodulesByFeatures(List<GroupFeaturesEnum> features, Distances distanceType, Set<Nodule> aleatoryNodules, Set<Nodule> allNodules, int qntRanking) {
		for (Nodule nodule : aleatoryNodules) {
			int qntAllNodulesByMalignance = isMalignant(nodule.getMalignance()) ? this.getQntAllMalignantNodules()
					: this.getQntAllBenignNodules();
			String identification = "";
			
			//Lista de nódulos vizinhos para features integradas
			if(features.size() > 1) {
				identification = getFeaturesNames(features);
			} else {
				identification = features.get(0).getFeatureName();
			}	
			ListNearestNodules listNearestNodulesByIntegratedFeatures = new ListNearestNodules(identification, nodule, allNodules, distanceType,
					features, qntAllNodulesByMalignance, qntRanking);
			/*System.out.println("Identificação: "+ identification + "\n"
						+ "Precisão: " + listNearestNodulesByIntegratedFeatures.getPrecision() + "\n"
						+ "Recall: " + listNearestNodulesByIntegratedFeatures.getRecall());
			*/	
			nodule.addListNearestNodules(listNearestNodulesByIntegratedFeatures);
			
		}
	}

	private String getFeaturesNames(List<GroupFeaturesEnum> features) {
		String featuresNames = "";
		for(int i = 0; i < features.size(); i++) {
			featuresNames += features.get(i).getFeatureName();
			if (i == features.size()-2 && features.size() > 1) {
				featuresNames += " e ";
			} else if (i != features.size() -1 && features.size() > 1) {
				featuresNames += ", ";
			}
		}
		if(features.size() > 1) {
			featuresNames += " integrados";
		}
		return featuresNames;
	}

	public boolean isMalignant(int malignance) {
		if (malignance == 5 || malignance == 4) {
			return true;
		}
		return false;
	}

	public int getQntAllBenignNodules() {
		int qnt = 0;
		Set<Nodule> nodules = this.getAllNodules();
		int noduleMalignance;
		for (Nodule n : nodules) {
			noduleMalignance = n.getMalignance();
			if (!isMalignant(noduleMalignance)) {
				qnt++;
			}
		}
		this.qntAllBenignNodules = qnt;
		return qntAllBenignNodules;
	}

	public int getQntAllMalignantNodules() {
		int qnt = 0;
		Set<Nodule> nodules = this.getAllNodules();
		int noduleMalignance;
		for (Nodule n : nodules) {
			noduleMalignance = n.getMalignance();
			if (isMalignant(noduleMalignance)) {
				qnt++;
			}
		}
		this.qntAllMalignantNodules = qnt;
		return qntAllMalignantNodules;
	}

}
