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
		for (Nodule n : nodules) {
			qntListNearestNodulesByFeature = n.getNearbyNodules().size();
		}
		List<List<Double>> precisionForAllNodules;
		List<Double> averagePrecisionForAllNodules;
		String featureName = "";
		for (int j = 0; j< qntListNearestNodulesByFeature; j++){
			precisionForAllNodules = new ArrayList<>();
			averagePrecisionForAllNodules = new ArrayList<>();
			for(Nodule n : nodules) {
				precisionForAllNodules.add(n.getNearbyNodules().get(j).getPrecision());
				featureName = n.getNearbyNodules().get(j).getIdentification();
			}
			List<Double> precisionByNoduleRanking = new ArrayList<>();
			for (int i = 0; i < nodules.size(); i++) {
				for (List<Double> precision : precisionForAllNodules) {
					precisionByNoduleRanking.add(precision.get(i));
				}
				averagePrecisionForAllNodules
				.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			}			
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
		for (int j = 0; j< qntListNearestNodulesByFeature; j++){
			recallForAllNodules = new ArrayList<>();
			averageRecallForAllNodules = new ArrayList<>();
			for(Nodule n : nodules) {
				recallForAllNodules.add(n.getNearbyNodules().get(j).getRecall());
				featureName = n.getNearbyNodules().get(j).getIdentification();
			}
			List<Double> recallByNoduleRanking = new ArrayList<>();
			for (int i = 0; i < nodules.size(); i++) {
				for (List<Double> recall : recallForAllNodules) {
					recallByNoduleRanking.add(recall.get(i));
				}
				averageRecallForAllNodules
				.add(recallByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			}
			System.err.println("Nome: " + featureName + " Valor: " + averageRecallForAllNodules);
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
			for(GroupFeaturesEnum feature : features) {
				identification = feature.getFeatureName();
				ListNearestNodules listNearestNodulesByFeature = new ListNearestNodules(identification, nodule, allNodules, distanceType,
						features, qntAllNodulesByMalignance, qntRanking);
				nodule.addListNearestNodules(listNearestNodulesByFeature);		
				System.err.println(identification + " " + listNearestNodulesByFeature);
			}
			
			//Lista de nódulos vizinhos para features integradas
			if(features.size() > 1) {
				identification = getFeaturesNames(features);
				System.out.println(identification);
				ListNearestNodules listNearestNodulesByIntegratedFeatures = new ListNearestNodules(identification, nodule, allNodules, distanceType,
					features, qntAllNodulesByMalignance, qntRanking);
				nodule.addListNearestNodules(listNearestNodulesByIntegratedFeatures);
			}
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
