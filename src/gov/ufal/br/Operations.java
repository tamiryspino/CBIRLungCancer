package gov.ufal.br;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Operations {
	
	public static Double sqrt(Double value) {
	    return Double.valueOf(Math.sqrt(value.doubleValue()));
	}
	
	public static List<String> getFeatures(Nodule nodule, List<FeaturesEnum> features) {
		List<String> selectedFeatures = new ArrayList<>();
		for(FeaturesEnum feature : features) {
			selectedFeatures.addAll(nodule.getFeatures().subList(feature.getInicialIndex(), feature.getFinalIndex()));
		}
		return selectedFeatures;
	}
	
	public static List<String> getFeatures(Nodule nodule, FeaturesEnum feature) {
		List<String> selectedFeatures = new ArrayList<>();
		selectedFeatures.addAll(nodule.getFeatures().subList(feature.getInicialIndex(), feature.getFinalIndex()));
		return selectedFeatures;
	}
	
	public static Double euclidianDistance(Nodule primaryNodule, Nodule observedNodule, List<FeaturesEnum> features) {
		Double sumFeaturesDifs = 0.0;
		List<String> strPrimaryNoduleFeatures = getFeatures(primaryNodule, features);
		List<String> strObservedNoduleFeatures = getFeatures(observedNodule, features);
				
		List<Double> primaryNoduleFeatures = strPrimaryNoduleFeatures.stream().map(Double::new)
				.collect(Collectors.toList());

		List<Double> observedNoduleFeatures = strObservedNoduleFeatures.stream().map(Double::new)
				.collect(Collectors.toList());

		if (strPrimaryNoduleFeatures.size() == strObservedNoduleFeatures.size()) {
			Double featureDif;

			for (int i = 0; i < primaryNoduleFeatures.size(); ++i) {
				featureDif = primaryNoduleFeatures.get(i) - observedNoduleFeatures.get(i);
				sumFeaturesDifs += featureDif * featureDif;
			}
		}

		return Math.sqrt(sumFeaturesDifs);
	}
	
	public static Double euclidianDistance(Nodule primaryNodule, Nodule observedNodule, FeaturesEnum features) {
		Double sumFeaturesDifs = new Double("0");
		List<String> strPrimaryNoduleFeatures = getFeatures(primaryNodule, features);
		List<String> strObservedNoduleFeatures = getFeatures(observedNodule, features);
				
		List<Double> primaryNoduleFeatures = strPrimaryNoduleFeatures.stream().map(Double::new)
				.collect(Collectors.toList());

		List<Double> observedNoduleFeatures = strObservedNoduleFeatures.stream().map(Double::new)
				.collect(Collectors.toList());

		if (strPrimaryNoduleFeatures.size() == strObservedNoduleFeatures.size()) {
			Double featureDif;

			for (int i = 0; i < primaryNoduleFeatures.size(); ++i) {
				featureDif = primaryNoduleFeatures.get(i) - observedNoduleFeatures.get(i);
				sumFeaturesDifs += featureDif * featureDif;
			}
		}

		return Math.sqrt(sumFeaturesDifs);
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