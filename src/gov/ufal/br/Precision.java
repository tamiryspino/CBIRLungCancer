package gov.ufal.br;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Precision {

	List<Double> precisions;
	Double averagePrecision;

	public Precision(NearestNodules nearbyNodules) {
		super();
		setPrecisions(nearbyNodules);
		setAveragePrecision();
	}

	public List<Double> getPrecisions() {
		return precisions;
	}

	public void setPrecisions(NearestNodules nearbyNodules) {
		Double sumMalignances = 0.0;
		List<Double> partialPrecision = new ArrayList<>();
		int noduleQnt = 0;
		boolean primaryNoduleMalignance = isMalignant(nearbyNodules.getPrimaryNodule().getMalignance());
		boolean nearbyNoduleMalignance;
		for (Nodule nodule : nearbyNodules.getNearbyNodules()) {
			noduleQnt++;
			nearbyNoduleMalignance = isMalignant(nodule.getMalignance());
			if (primaryNoduleMalignance == nearbyNoduleMalignance) {
				sumMalignances++;
			}
			partialPrecision.add(sumMalignances / noduleQnt);
		}
		this.precisions = partialPrecision;
	}

	private boolean isMalignant(String malignance) {
		return ("MALIGNANT".equals(malignance)) ? true : false;
	}

	public Double getAveragePrecision() {
		return averagePrecision;
	}

	public void setAveragePrecision() {
		this.averagePrecision = precisions.stream().mapToDouble(a -> a).average().orElse(0);
	}
}
