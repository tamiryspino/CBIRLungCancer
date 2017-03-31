package gov.ufal.br;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class Evaluator {

	private Set<Nodule> aleatoryMalignantNodules = new HashSet<>();
	private Set<Nodule> aleatoryBenignNodules = new HashSet<>();
	private Set<Nodule> allNodules;
	private int qntEvaluatedNodules;
	private int qntAllBenignNodules;
	private int qntAllMalignantNodules;

	public Evaluator(Set<Nodule> allNodules, int qntEvaluatedNodules) {
		super();
		this.allNodules = allNodules;
		this.qntEvaluatedNodules = qntEvaluatedNodules;
	}

	public Set<Nodule> getAleatoryMalignantNodules() {
		this.aleatoryMalignantNodules = setAleatoryNodules(4);
		this.aleatoryMalignantNodules.addAll(setAleatoryNodules(5));
		return this.aleatoryMalignantNodules;
	}

	public void setAleatoryBenignNodules(Set<Nodule> aleatoryBenignNodules) {
		this.aleatoryBenignNodules = aleatoryBenignNodules;
	}

	public int getQntEvaluatedNodules() {
		return qntEvaluatedNodules;
	}

	public void setQntEvaluatedNodules(int qntEvaluatedNodules) {
		this.qntEvaluatedNodules = qntEvaluatedNodules;
	}

	public Set<Nodule> setAleatoryMalignantNodules() {
		this.aleatoryMalignantNodules = setAleatoryNodules(5);
		this.aleatoryMalignantNodules.addAll(setAleatoryNodules(4));
		return this.aleatoryMalignantNodules;
	}

	public Set<Nodule> getAleatoryBenignNodules() {
		this.aleatoryBenignNodules = setAleatoryNodules(1);
		this.aleatoryBenignNodules.addAll(setAleatoryNodules(2));
		return this.aleatoryBenignNodules;
	}

	public Set<Nodule> setAleatoryNodules(int malignanceProbability) {

		Random rnd;
		Set<Nodule> nodules = new HashSet<>();
		Nodule nodule = null;
		int n;
		int noduleMalignance;
		int qnt = qntEvaluatedNodules / 4;
		for (int i = 0; i < qnt; i++) {
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

	public List<Double> setAveragePrecisionForAllNodules(Set<Nodule> nodules) {
		List<List<Double>> precisionForAllNodules = new ArrayList<>();
		List<Double> averagePrecisionForAllNodules = new ArrayList<>();
		for (Nodule n : nodules) {
			precisionForAllNodules.add(n.getNearbyNodulesByAll().getPrecision());
		}
		List<Double> precisionByNoduleRanking = new ArrayList<>();
		for (int i = 0; i < nodules.size(); i++) {
			for (List<Double> precision : precisionForAllNodules) {
				precisionByNoduleRanking.add(precision.get(i));
			}
			averagePrecisionForAllNodules
					.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
		}

		return averagePrecisionForAllNodules;
	}

	public List<Double> setAverageRecallForAllNodules(Set<Nodule> aleatoryMalignantNodules) {
		List<List<Double>> recallForAllNodules = new ArrayList<>();
		List<Double> averageRecallForAllNodules = new ArrayList<>();
		for (Nodule n : aleatoryMalignantNodules) {
			recallForAllNodules.add(n.getNearbyNodulesByAll().getRecall());
		}
		List<Double> recallByNoduleRanking = new ArrayList<>();
		for (int i = 0; i < aleatoryMalignantNodules.size(); i++) {
			for (List<Double> recall : recallForAllNodules) {
				recallByNoduleRanking.add(recall.get(i));
			}
			averageRecallForAllNodules.add(recallByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
		}

		return averageRecallForAllNodules;

	}

	public void setNearbyNodulesByAllFeatures(Set<Nodule> aleatoryNodules, Set<Nodule> allNodules, int qntRanking) {
		for (Nodule nodule : aleatoryNodules) {
			int qntAllNodulesByMalignance = isMalignant(nodule.getMalignance()) ? this.getQntAllMalignantNodules()
					: this.getQntAllBenignNodules();
			ListNearestNodules listNearestNodules = new ListNearestNodules(nodule, allNodules, Distances.EUCLIDIAN,
					GroupFeaturesEnum.ALL_FEATURES, qntAllNodulesByMalignance, qntRanking);
			nodule.setNearbyNodulesByAll(listNearestNodules);
			// System.out.println(listNearestNodules.showNearbyNodules());
			// System.out.println(listNearestNodules.getPrecision());
		}
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
