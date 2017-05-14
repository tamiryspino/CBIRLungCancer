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
	Precision precisionOfNearestNodules;

	public NearestNodules(Nodule primaryNodule, Set<Nodule> allNodules, Distances distance,
			List<GroupFeaturesEnum> features, int qntRanking) {
		super();
		setCharacteristic(features);
		this.primaryNodule = primaryNodule;
		List<Nodule> listAllNodules = new ArrayList<>(allNodules);
		setNearbyNodules(listAllNodules, distance, features, qntRanking);
		this.setPrecisionOfNearestNodules();
	}

	public StringBuilder showNearbyNodules() {
		StringBuilder str = new StringBuilder("-------------- ListNearestNodules by " + this.getCharacteristic() + "--------------- \n");
		for (Nodule nn : nearbyNodules) {
			str.append(nn.toString() + "");
		}
		str.append("Precisão: " + this.getPrecisionOfNearestNodules());
		return str;
	}

	public List<Nodule> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(List<Nodule> nearbyNodules, Distances distanceFormula, List<GroupFeaturesEnum> features,
			int qntRanking) {
		BigDecimal distance = new BigDecimal("0.0");
		for (Nodule nodule : nearbyNodules) {
			if (distanceFormula == Distances.EUCLIDIAN) {
				distance = Operations.euclidianDistance(primaryNodule, nodule, features);
			}
			//TODO else if (distanceFormula == Distances.)
			nodule.setDistance(distance);
		}
		Collections.sort(nearbyNodules, Comparator.comparing(Nodule::getDistance));

		this.nearbyNodules = nearbyNodules.subList(0,
				nearbyNodules.size() > qntRanking ? qntRanking : nearbyNodules.size());
	}

	public Precision getPrecisionOfNearestNodules() {
		return precisionOfNearestNodules;
	}

	public void setPrecisionOfNearestNodules() {
		this.precisionOfNearestNodules = new Precision(this);
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
	
	private String setCharacteristic(List<GroupFeaturesEnum> features) {
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
	
	public String getCharacteristic() {
		return characteristic;
	}
}