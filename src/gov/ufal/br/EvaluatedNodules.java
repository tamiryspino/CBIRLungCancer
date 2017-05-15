package gov.ufal.br;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Random;

public class EvaluatedNodules {

	private String malignance;
	private Set<Nodule> aleatoryNodulesByMalignance = new HashSet<>();
	private Set<Nodule> allNodules;
	private List<PrecisionByFeatures> precisions;
	//TODO DESVIO PADRÃO

	public EvaluatedNodules(String malignance, Set<Nodule> allNodules, int qntAleatoryNodules) {
		super();
		this.malignance = malignance;
		this.allNodules = allNodules;
		setAleatoryNodulesByMalignance(qntAleatoryNodules);
	}

	public Set<Nodule> getAleatoryNodulesByMalignance() {
		return aleatoryNodulesByMalignance;
	}

	public Set<Nodule> setAleatoryNodulesByMalignance(int qntAleatoryNodules) {

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

	/**
	 * Cria uma lista de nódulos vizinhos para cada feature e uma lista de
	 * nódulos vizinhos com as features integradas (caso haja mais de uma
	 * feature)
	 **/
	public void setNearbyNodulesByFeatures(List<GroupFeaturesEnum> features, Distances distanceType, int qntRanking) {
		for (Nodule nodule : aleatoryNodulesByMalignance) {
			NearestNodules listNearestNodulesByIntegratedFeatures = new NearestNodules(nodule, allNodules, distanceType,
					features, qntRanking);
			nodule.addNearestNodules(listNearestNodulesByIntegratedFeatures);
			/*
			 * System.out.println("Identificação: "+ identification + "\n" +
			 * "Precisão: " +
			 * listNearestNodulesByIntegratedFeatures.getPrecision() + "\n" +
			 * "Recall: " + listNearestNodulesByIntegratedFeatures.getRecall());
			 */

		}
		this.precisions.add(new PrecisionByFeatures(aleatoryNodulesByMalignance, getFeatureName(features)));
	}
	
	public String getFeatureName(List<GroupFeaturesEnum> features){
		String featureName = "";
		for(int i = 0; i < features.size(); i++) {
			featureName += features.get(i).getFeatureName();
			if (i == features.size()-2 && features.size() > 1) {
				featureName += " e ";
			} else if (i != features.size() -1 && features.size() > 1) {
				featureName += ", ";
			}
		}
		if(features.size() > 1) {
			featureName += " integrados";
		}
		return featureName;
	}

	public String getMalignance() {
		return malignance;
	}

	public void setMalignance(String malignance) {
		this.malignance = malignance;
	}

	public List<PrecisionByFeatures> getPrecisions() {
		return precisions;
	}

	public void setPrecisions(List<PrecisionByFeatures> precisions) {
		this.precisions = precisions;
	}
}
