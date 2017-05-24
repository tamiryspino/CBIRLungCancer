package gov.ufal.br;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mongodb.util.Hash;

public class Nodule {

	String id;
	List<String> features;
	Set<Nodule> nearbyNodules;
	String malignance;
	Double distance = 1000.0;
	
	public Nodule(String id, List<String> features, String malignance) {
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

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
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
