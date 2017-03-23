package gov.ufal.br;

import java.util.List;

public class ListNearestNodules {

	List<NearestNodules> nearestNodules;
	List<Double> precision;
	Double averagePrecision;
	
	public List<NearestNodules> getNearestNodules() {
		return nearestNodules;
	}
	public void setNearestNodules(List<NearestNodules> nearestNodules) {
		this.nearestNodules = nearestNodules;
	}
	public List<Double> getPrecision() {
		return precision;
	}
	public void setPrecision(List<Double> precision) {
		this.precision = precision;
	}
	public Double getAveragePrecision() {
		return averagePrecision;
	}
	public void setAveragePrecision(Double averagePrecision) {
		this.averagePrecision = averagePrecision;
	}

}