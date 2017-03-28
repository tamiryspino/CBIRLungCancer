package gov.ufal.br;

import java.math.BigDecimal;
import java.util.List;

public class Nodule {

	String id;
	List<String> features;
	int malignance;
	List<Nodule> nearbyNodules;
	BigDecimal distance = new BigDecimal("0");
	
	public Nodule(String id, List<String> features, int malignance) {
		super();
		this.id = id;
		this.features = features;
		this.malignance = malignance;
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

	public int getMalignance() {
		return malignance;
	}

	public void setMalignance(int malignance) {
		this.malignance = malignance;
	}

	public List<Nodule> getNearbyNodules() {
		return nearbyNodules;
	}

	public void setNearbyNodules(List<Nodule> nearbyNodules) {
		this.nearbyNodules = nearbyNodules;
	}

	@Override
	public String toString() {
		return "Nodule [id=" + id + ", malignance=" + malignance + ", distance=" + distance + "] \n";
	}
	
	public String showNearbys(){
		String str = "--------------------- NEARBY NODULES ------------------------";
		for (Nodule n : nearbyNodules) {
			str += n.toString();
		}
		return str;
	}
	
	
}
