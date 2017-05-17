package gov.ufal.br;

import java.util.ArrayList;
import java.util.List;

public class Nodule {

	String id;
	List<String> features;
	List<NearestNodules> nearbyNodules;
	String malignance;
	Double distance = 0.0;
	
	public Nodule(String id, List<String> features, String malignance) {
		super();
		this.id = id;
		this.features = features;
		this.malignance = malignance;
		this.nearbyNodules = new ArrayList<>();
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

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}
	
	public List<NearestNodules> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(List<NearestNodules> nearbyNodules) {
		this.nearbyNodules = nearbyNodules;
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

	public void addNearestNodules(NearestNodules nearestNodules) {
		this.nearbyNodules.add(nearestNodules);
	}
	
	
}
