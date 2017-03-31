package gov.ufal.br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ListNearestNodules {

	Nodule primaryNodule;
	List<Nodule> nearbyNodules;
	List<Double> precision;
	List<Double> recall;
	Double averagePrecision;

	public ListNearestNodules(Nodule primaryNodule, Set<Nodule> allNodules, Distances distance,
			GroupFeaturesEnum features, int qntAllNodulesByMalignance, int qntRanking) {
		super();
		this.primaryNodule = primaryNodule;
		List<Nodule> listAllNodules = new ArrayList<>(allNodules);
		setNearbyNodules(listAllNodules, distance, features, qntRanking);
		this.setPrecision();
		this.setRecall(qntAllNodulesByMalignance);
		this.setAveragePrecision();
	}

	public StringBuilder showNearbyNodules() {
		StringBuilder str = new StringBuilder("-------------- ListNearestNodules --------------- \n");
		for (Nodule nn : nearbyNodules) {
			str.append(nn.toString() + "");
		}
		return str;
	}

	public List<Nodule> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(List<Nodule> nearbyNodules, Distances distanceFormula, GroupFeaturesEnum features,
			int qntRanking) {
		BigDecimal distance = new BigDecimal("0");
		for (Nodule nodule : nearbyNodules) {
			if (distanceFormula == Distances.EUCLIDIAN) {
				distance = Operations.euclidianDistance(primaryNodule, nodule, features);
			}
			// } else if (distanceFormula == Distances.)
			nodule.setDistance(distance);
		}
		Collections.sort(nearbyNodules, Comparator.comparing(Nodule::getDistance));

		this.nearbyNodules = nearbyNodules.subList(0,
				nearbyNodules.size() > qntRanking ? qntRanking : nearbyNodules.size());
	}

	public List<Double> getPrecision() {
		return precision;
	}

	public void setPrecision() {
		Double sumMalignances = 0.0;
		List<Double> partialPrecision = new ArrayList<>();
		int noduleQnt = 0;
		boolean primaryNoduleMalignance = isMalignant(primaryNodule.getMalignance());
		boolean nearbyNoduleMalignance;
		for (Nodule nodule : nearbyNodules) {
			noduleQnt++;
			nearbyNoduleMalignance = isMalignant(nodule.getMalignance());
			if (primaryNoduleMalignance == nearbyNoduleMalignance) {
				sumMalignances++;
			}
			// System.out.println(sumMalignances + "/" + noduleQnt + " = " +
			// sumMalignances/noduleQnt);
			partialPrecision.add(sumMalignances / noduleQnt);
		}
		this.precision = partialPrecision;
	}

	public boolean isMalignant(int malignance) {
		if (malignance == 5 || malignance == 4) {
			return true;
		}
		return false;
	}

	public boolean isBenign(int malignance) {
		if (malignance == 1 || malignance == 2) {
			return true;
		}
		return false;
	}

	public Double getAveragePrecision() {
		return averagePrecision;
	}

	public void setAveragePrecision() {
		this.averagePrecision = precision.stream().mapToDouble(a -> a).average().orElse(0);
	}

	public List<Double> getRecall() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRecall(int qntAllNodulesByMalignance) {
		List<Double> partialRecall = new ArrayList<>();
		int qntAleatoryNodulesByMalignance = 0;
		boolean primaryNoduleMalignance = isMalignant(primaryNodule.getMalignance());
		boolean nearbyNoduleMalignance;
		for (Nodule nodule : nearbyNodules) {
			nearbyNoduleMalignance = isMalignant(nodule.getMalignance());
			if (primaryNoduleMalignance == nearbyNoduleMalignance) {
				qntAleatoryNodulesByMalignance++;
			}
			partialRecall.add((double) (qntAleatoryNodulesByMalignance) / qntAllNodulesByMalignance);
		}
		this.recall = partialRecall;

	}

}