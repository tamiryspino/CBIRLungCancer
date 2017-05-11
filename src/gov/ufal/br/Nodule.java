package gov.ufal.br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Nodule {

	String id;
	List<String> features;
	List<ListNearestNodules> nearbyNodules;
	int malignance;
	BigDecimal distance = new BigDecimal("0");
	
	public Nodule(String id, List<String> features, int malignance) {
		super();
		this.id = id;
		this.features = features;
		this.malignance = malignance;
		this.nearbyNodules = new ArrayList<>();
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
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
	
	public List<ListNearestNodules> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(List<ListNearestNodules> nearbyNodules) {
		this.nearbyNodules = nearbyNodules;
	}


	public int getMalignance() {
		return malignance;
	}

	public void setMalignance(int malignance) {
		this.malignance = malignance;
	}

	@Override
	public String toString() {
		return "Nodule [id=" + id + ", malignance=" + malignance + ", distance=" + distance + "] \n";
	}

	public void addListNearestNodules(ListNearestNodules listNearestNodules) {
		this.nearbyNodules.add(listNearestNodules);
	}
	
	
}
