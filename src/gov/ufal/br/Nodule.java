package gov.ufal.br;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nodule {

	String id;
	List<Double> features;
	Set<Nodule> nearbyNodules;
	String malignance;
	Double distance = 1000.0;
	
	public Nodule(String id, List<Double> features, String malignance) {
		super();
		this.id = id;
		this.features = features;
		this.malignance = malignance;
		this.nearbyNodules = new HashSet<>();
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Double> getFeatures() {
		return features;
	}

	public void setFeatures(List<Double> features) {
		this.features = features;
	}
	
	public Set<Nodule> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(Set<Nodule> allNodules) {
		this.nearbyNodules = allNodules;
	}

	public String getMalignance() {
		return malignance;
	}

	public void setMalignance(String malignance) {
		this.malignance = malignance;
	}

	@Override
	public String toString() {
		return "Nodule [id=" + id + ", malignance=" + malignance + ", distance=" +distance + "] \n";
	}
}
