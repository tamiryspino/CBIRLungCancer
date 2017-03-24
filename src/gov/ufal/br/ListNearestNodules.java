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

	public Nodule getPrimaryNodule() {
		return primaryNodule;
	}

	public void setPrimaryNodule(Nodule primaryNodule) {
		this.primaryNodule = primaryNodule;
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
		if (isMaligne(primaryNodule.getMalignance())) {
			for (Nodule nodule : nearbyNodules) {
				noduleQnt++;
				if (isMaligne(nodule.getMalignance())) {
					sumMalignances++;
				}
				partialPrecision.add(sumMalignances / noduleQnt);
			}
		} else if (isBenigne(primaryNodule.getMalignance())) {
			for (Nodule nodule : nearbyNodules) {
				noduleQnt++;
				if (isBenigne(nodule.getMalignance())) {
					sumMalignances++;
				}
				partialPrecision.add(sumMalignances / noduleQnt);
			}
		}
		this.precision = partialPrecision;
	}

	public boolean isMaligne(int malignance) {
		if (malignance == 5 || malignance == 4) {
			return true;
		}
		return false;
	}

	public boolean isBenigne(int malignance) {
		if (malignance == 1 || malignance == 2) {
			return true;
		}
		return false;
	}

	public void setPrecisionForBenigneNodule() {
		Double sumMalignances = 0.0;
		List<Double> partialPrecision = new ArrayList<>();
		int noduleQnt = 0;
		for (Nodule nodule : nearbyNodules) {
			noduleQnt++;
			if (nodule.getMalignance() == 1 || nodule.getMalignance() == 2) {
				sumMalignances++;
			}
			partialPrecision.add(sumMalignances / noduleQnt);
		}
		this.precision = partialPrecision;
	}

	public Double getAveragePrecision() {
		return averagePrecision;
	}

	public void setAveragePrecision(Double averagePrecision) {
		this.averagePrecision = averagePrecision;
	}

}