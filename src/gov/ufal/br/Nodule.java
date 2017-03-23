package gov.ufal.br;

public class Nodule {

	Integer id;
	String[] features;
	int malignance;
	
	ListNearestNodules nearestByAll;
	ListNearestNodules nearestByColor;
	ListNearestNodules nearestByForm;
	ListNearestNodules nearestByTexture;
	ListNearestNodules nearestByEdgeSharpness;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String[] getFeatures() {
		return features;
	}
	public void setFeatures(String[] features) {
		this.features = features;
	}
	public int getMalignance() {
		return malignance;
	}
	public void setMalignance(int malignance) {
		this.malignance = malignance;
	}
	public ListNearestNodules getNearestByAll() {
		return nearestByAll;
	}
	public void setNearestByAll(ListNearestNodules nearestByAll) {
		this.nearestByAll = nearestByAll;
	}
	public ListNearestNodules getNearestByColor() {
		return nearestByColor;
	}
	public void setNearestByColor(ListNearestNodules nearestByColor) {
		this.nearestByColor = nearestByColor;
	}
	public ListNearestNodules getNearestByForm() {
		return nearestByForm;
	}
	public void setNearestByForm(ListNearestNodules nearestByForm) {
		this.nearestByForm = nearestByForm;
	}
	public ListNearestNodules getNearestByTexture() {
		return nearestByTexture;
	}
	public void setNearestByTexture(ListNearestNodules nearestByTexture) {
		this.nearestByTexture = nearestByTexture;
	}
	public ListNearestNodules getNearestByEdgeSharpness() {
		return nearestByEdgeSharpness;
	}
	public void setNearestByEdgeSharpness(ListNearestNodules nearestByEdgeSharpness) {
		this.nearestByEdgeSharpness = nearestByEdgeSharpness;
	}
	
}
