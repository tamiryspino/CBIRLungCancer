package gov.ufal.br;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Operations {
	
	public static BigDecimal sqrt(BigDecimal value) {
	    return BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
	}


	public static BigDecimal euclidianDistance(Nodule primaryNodule, Nodule observedNodule, GroupFeaturesEnum features) {
		BigDecimal sumFeaturesDifs = new BigDecimal("0");
		List<String> strPrimaryNoduleFeatures = primaryNodule.getFeatures().subList(features.getInicialIndex(), features.getFinalIndex());
		List<String> strObservedNoduleFeatures = observedNodule.getFeatures().subList(features.getInicialIndex(), features.getFinalIndex());

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
}