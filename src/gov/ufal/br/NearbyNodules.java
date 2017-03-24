package gov.ufal.br;

import java.math.BigDecimal;

public class NearbyNodules {
	
	Nodule nodule;
	BigDecimal distance;
	
	public NearbyNodules(Nodule nodule, BigDecimal distance) {
		super();
		this.nodule = nodule;
		this.distance = distance;
	}
	public Nodule getNodule() {
		return nodule;
	}
	public void setNodule(Nodule nodule) {
		this.nodule = nodule;
	}
	public BigDecimal getDistance() {
		return distance;
	}
	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}
	
}
