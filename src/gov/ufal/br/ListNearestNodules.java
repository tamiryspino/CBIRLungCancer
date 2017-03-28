package gov.ufal.br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

import javax.xml.namespace.QName;

public class ListNearestNodules {
	
	Nodule primaryNodule;
	List<Nodule> nearbyNodules;
	List<Double> precision;
	Double averagePrecision;

	@Override
	public String toString() {
		String str = "-------------- ListNearestNodules [nearbyNodules= ----------------";
				for (Nodule nn : nearbyNodules) {
					str += nn.toString() + "\n ";
				}
		return str;
	}

	public ListNearestNodules(Nodule primaryNodule, List<Nodule> nearbyNodules) {
		super();
		this.primaryNodule = primaryNodule;
		setNearbyNodules(nearbyNodules);
	}

	public List<Nodule> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(List<Nodule> nearbyNodules) {
		for (Nodule nodule : nearbyNodules) {
			BigDecimal distance = Operations.euclidianDistance(primaryNodule, nodule);
			nodule.setDistance(distance);
		}
		Collections.sort(nearbyNodules, Comparator.comparing(Nodule::getDistance));
		
		this.nearbyNodules = nearbyNodules.subList(0, 10);
	}

	public List<Double> getPrecision() {
		return precision;
	}

	public void setPrecision() {
		Double sumMalignances = 0.0;
		List<Double> partialPrecision = new ArrayList<>();
		int noduleQnt = 0;
		if (isMalignant(primaryNodule.getMalignance())) {
			for (Nodule nodule : nearbyNodules) {
				noduleQnt++;
				if (isMalignant(nodule.getMalignance())) {
					sumMalignances++;
				}
				partialPrecision.add(sumMalignances / noduleQnt);
			}
		} else if (isBenign(primaryNodule.getMalignance())) {
			for (Nodule nodule : nearbyNodules) {
				noduleQnt++;
				if (isBenign(nodule.getMalignance())) {
					sumMalignances++;
				}
				partialPrecision.add(sumMalignances / noduleQnt);
			}
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

}