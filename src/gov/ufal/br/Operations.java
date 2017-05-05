package gov.ufal.br;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Operations {
	
	public static BigDecimal sqrt(BigDecimal value) {
	    return BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
	}
	
	public static List<String> getSelectedFeatures(Nodule nodule, List<GroupFeaturesEnum> features) {
		List<String> selectedFeatures = new ArrayList<>();
		for(GroupFeaturesEnum feature : features) {
			selectedFeatures.addAll(nodule.getFeatures().subList(feature.getInicialIndex(), feature.getFinalIndex()));
		}
		return selectedFeatures;
	}


	public static BigDecimal euclidianDistance(Nodule primaryNodule, Nodule observedNodule, List<GroupFeaturesEnum> features) {
		BigDecimal sumFeaturesDifs = new BigDecimal("0");
		List<String> strPrimaryNoduleFeatures = getSelectedFeatures(primaryNodule, features);
		List<String> strObservedNoduleFeatures = getSelectedFeatures(observedNodule, features);
				
		List<BigDecimal> primaryNoduleFeatures = strPrimaryNoduleFeatures.stream().map(BigDecimal::new)
				.collect(Collectors.toList());

		List<BigDecimal> observedNoduleFeatures = strObservedNoduleFeatures.stream().map(BigDecimal::new)
				.collect(Collectors.toList());

		if (strPrimaryNoduleFeatures.size() == strObservedNoduleFeatures.size()) {
			BigDecimal featureDif;

			for (int i = 0; i < primaryNoduleFeatures.size(); ++i) {
				featureDif = primaryNoduleFeatures.get(i).subtract(observedNoduleFeatures.get(i));
				sumFeaturesDifs = sumFeaturesDifs.add(featureDif.multiply(featureDif));
			}
		}

		return sqrt(sumFeaturesDifs);
	}
	
	public static Double sum(List<Double> elements) {
		return elements.stream().collect(Collectors.summingDouble(d -> d));
	}
	
	public static Double arithmeticMean(List<Double> elements) {
		Double n = (double) elements.size();
		return (1.0/n)*sum(elements);
	}
	
	public static Double sampleVariance(List<Double> elements) {
		int n = elements.size();
		Double am = arithmeticMean(elements);
		return (1.0/(n-1))*IntStream.range(0, n)
	             .mapToDouble(i -> (elements.get(i) - am)*(elements.get(i) - am))
	             .sum();
	}
	
	public Double standardDeviation(Double sampleVariance) {
		return Math.sqrt(sampleVariance);
	}


	public static Double standardDeviation(List<Double> elements) {
		return Math.sqrt(sampleVariance(elements));
	}
}