package gov.ufal.br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NearestNodules {

	String characteristic;
	Nodule primaryNodule;
	List<Nodule> nearbyNodules;
	List<Double> precisions;
	Double averagePrecision;

	public NearestNodules(Nodule primaryNodule, Set<Nodule> allNodules, Distances distance,
			List<GroupFeaturesEnum> features, int qntRanking) {
		super();
		setCharacteristic(features);
		this.primaryNodule = primaryNodule;
		setNearbyNodules(allNodules, distance, features, qntRanking);
		setPrecisions();
	}

	public Double getAveragePrecision() {
		return averagePrecision;
	}

	public void setAveragePrecision() {
		this.averagePrecision = precisions.stream().mapToDouble(a -> a).average().orElse(0);
	}

	public StringBuilder showNearbyNodules() {
		StringBuilder str = new StringBuilder("-------------- ListNearestNodules by " + this.getCharacteristic() + "--------------- \n");
		for (Nodule nn : nearbyNodules) {
			str.append(nn.toString() + "");
		}
		str.append("Precisão: " + this.getPrecisions());
		return str;
	}

	public List<Nodule> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(Set<Nodule> nearbyNodules, Distances distanceFormula, List<GroupFeaturesEnum> features,
			int qntRanking) {
		List<Nodule> nearestNodules = new ArrayList<>(nearbyNodules);
		BigDecimal distance = new BigDecimal("0.0");
		for (Nodule nodule : nearestNodules) {
			if (distanceFormula == Distances.EUCLIDIAN) {
				distance = Operations.euclidianDistance(primaryNodule, nodule, features);
			}
			//TODO else if (distanceFormula == Distances.)
			nodule.setDistance(distance);
		}
		//TODO Verificar pq não está ordenando
		Collections.sort(nearestNodules, Comparator.comparing(Nodule::getDistance));
		
		this.nearbyNodules = nearestNodules.subList(0,
				nearestNodules.size() > qntRanking ? qntRanking : nearestNodules.size());
		showNearbyNodules();
	}

	public Nodule getPrimaryNodule() {
		return primaryNodule;
	}

	public void setPrimaryNodule(Nodule primaryNodule) {
		this.primaryNodule = primaryNodule;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	
	private void setCharacteristic(List<GroupFeaturesEnum> features) {
		StringBuilder featureName = new StringBuilder();
		for(int i = 0; i < features.size(); i++) {
			featureName.append(features.get(i).getFeatureName());
			if (i == features.size()-2 && features.size() > 1) {
				featureName.append(" e ");
			} else if (i != features.size() -1 && features.size() > 1) {
				featureName.append(", ");
			}
		}
		if(features.size() > 1) {
			featureName.append(" integrados");
		}
		this.characteristic = featureName.toString();
	}
	
	public String getCharacteristic() {
		return characteristic;
	}

	public void setPrecisions() {
		Double sumMalignances = 0.0;
		List<Double> partialPrecision = new ArrayList<>();
		int noduleQnt = 0;
		boolean primaryNoduleMalignance = isMalignant(this.getPrimaryNodule().getMalignance());
		boolean nearbyNoduleMalignance;
		for (Nodule nodule : this.getNearbyNodules()) {
			noduleQnt++;
			nearbyNoduleMalignance = isMalignant(nodule.getMalignance());
			if (primaryNoduleMalignance == nearbyNoduleMalignance) {
				sumMalignances++;
			}
			partialPrecision.add(sumMalignances / noduleQnt);
		}
		this.precisions = partialPrecision;
		setAveragePrecision();
	}
	
	public List<Double> getPrecisions() {
		return precisions;
	}

	private boolean isMalignant(String malignance) {
		return "MALIGNANT".equals(malignance) ? true : false;
	}
}