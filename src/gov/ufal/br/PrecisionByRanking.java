package gov.ufal.br;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrecisionByRanking {

	int qntFeatures;
	String groupName;
	List<FeaturesEnum> selectedFeatures;
	List<List<Double>> precisionByLeaveOneOut;
	List<Double> precisionOfAll;
	Double precisionMean;
	Double standartDeviation;
	
	public PrecisionByRanking(int qntFeatures, String groupName, List<FeaturesEnum> selectedFeatures,
			List<List<Double>> precisionByLeaveOneOut) {
		super();
		this.qntFeatures = qntFeatures;
		this.groupName = groupName;
		this.selectedFeatures = selectedFeatures;
		this.precisionByLeaveOneOut = precisionByLeaveOneOut;
		setPrecisionOfAll();
		setPrecisionMean();
		setStandartDeviation();
	}
	public int getQntFeatures() {
		return qntFeatures;
	}
	public void setQntFeatures(int qntFeatures) {
		this.qntFeatures = qntFeatures;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<FeaturesEnum> getSelectedFeatures() {
		return selectedFeatures;
	}
	public void setSelectedFeatures(List<FeaturesEnum> selectedFeatures) {
		this.selectedFeatures = selectedFeatures;
	}
	public List<List<Double>> getPrecisionByLeaveOneOut() {
		return precisionByLeaveOneOut;
	}
	public void setPrecisionByLeaveOneOut(List<List<Double>> precisionByLeaveOneOut) {
		this.precisionByLeaveOneOut = precisionByLeaveOneOut;
	}
	public List<Double> getPrecisionOfAll() {
		return precisionOfAll;
	}
	public void setPrecisionOfAll() {
		int qntRanking = precisionByLeaveOneOut.get(0).size();
		List<Double> allPrecisionByNoduleRanking = new ArrayList<>();
		List<Double> precisionByNoduleRanking;
		
		for(int j = 0; j < qntRanking; j++) {
			precisionByNoduleRanking = new ArrayList<>();
			for(int k=0; k< precisionByLeaveOneOut.size(); k++){
				List<Double> precision = precisionByLeaveOneOut.get(k);
				precisionByNoduleRanking.add(precision.get(j));
				//System.out.println("Precisoes do nodulo " + k + " lugar " + j + ": " + precision.get(j));
			}
			//System.out.println("Ok, proximo lugar no ranking...");
			//System.out.println(precisionByNoduleRanking);
			allPrecisionByNoduleRanking.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			//System.out.println("Media do lugar: "+ precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
		}
		this.precisionOfAll = allPrecisionByNoduleRanking;
	}
	public Double getPrecisionMean() {
		return precisionMean;
	}
	public void setPrecisionMean() {
		Double d = precisionOfAll.stream().mapToDouble(a -> a).average().orElse(0);
		this.precisionMean = Math.round(d*10000)/10000.0d;
	}
	public Double getStandartDeviation() {
		return standartDeviation;
	}
	public void setStandartDeviation() {
		Double d = Operations.standardDeviation(precisionOfAll);
		this.standartDeviation = Math.round(d*10000)/10000.0d ;
	}
	public void showResults() {
		System.out.println("Qnt Features selecionadas: " + this.getQntFeatures() +
				"\n Nome do grupo: " + this.getGroupName() + 
				"\n Media: " + this.getPrecisionMean() + " +- " + this.getStandartDeviation());
		
	}
}
