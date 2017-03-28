package gov.ufal.br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ListNearestNodules {
	
	Nodule primaryNodule;
	List<Nodule> nearbyNodules;
	List<Double> precision;
	Double averagePrecision;

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
			BigDecimal distance = Operations.euclidianDistance(primaryNodule, nodule, 0, 120);
			nodule.setDistance(distance);
		}
		// TODO Ordenar nodulos por distancia
		this.nearbyNodules = nearbyNodules;
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

	public void setAveragePrecision(Double averagePrecision) {
		this.averagePrecision = averagePrecision;
	}

}