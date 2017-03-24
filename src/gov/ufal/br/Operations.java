package gov.ufal.br;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Operations {

	private static final BigDecimal SQRT_DIG = new BigDecimal(150);
	private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

	/**
	 * Private utility method used to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	private static BigDecimal sqrtNewtonRaphson(BigDecimal c, BigDecimal xn, BigDecimal precision) {
		BigDecimal fx = xn.pow(2).add(c.negate());
		BigDecimal fpx = xn.multiply(new BigDecimal(2));
		BigDecimal xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
		xn1 = xn.add(xn1.negate());
		BigDecimal currentSquare = xn1.pow(2);
		BigDecimal currentPrecision = currentSquare.subtract(c);
		currentPrecision = currentPrecision.abs();
		if (currentPrecision.compareTo(precision) <= -1) {
			return xn1;
		}
		return sqrtNewtonRaphson(c, xn1, precision);
	}

	/**
	 * Uses Newton Raphson to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	 */
	public static BigDecimal bigSqrt(BigDecimal c) {
		return sqrtNewtonRaphson(c, new BigDecimal(1), new BigDecimal(1).divide(SQRT_PRE));
	}

	public static BigDecimal euclidianDistance(Nodule primaryNodule, Nodule observedNodule, int inicialIndex, int finalIndex) {
		BigDecimal sumFeaturesDifs = new BigDecimal("0");
		List<String> strPrimaryNoduleFeatures = primaryNodule.getFeatures().subList(inicialIndex, finalIndex);
		List<String> strObservedNoduleFeatures = observedNodule.getFeatures().subList(inicialIndex, finalIndex);

		List<BigDecimal> primaryNoduleFeatures = strPrimaryNoduleFeatures.stream().map(BigDecimal::new)
				.collect(Collectors.toList());

		List<BigDecimal> observedNoduleFeatures = strObservedNoduleFeatures.stream().map(BigDecimal::new)
				.collect(Collectors.toList());

		if (strPrimaryNoduleFeatures.size() == strObservedNoduleFeatures.size()) {
			BigDecimal featureDif;

			for (int i = 0; i < primaryNoduleFeatures.size(); ++i) {
				featureDif = primaryNoduleFeatures.get(i).subtract(observedNoduleFeatures.get(i));
				sumFeaturesDifs.add((featureDif).multiply(featureDif));
			}
		}

		return bigSqrt(sumFeaturesDifs);
	}

	public void setCloserNodulesByAll(Nodule primaryNodule, List<Nodule> nodules) {

		List<NearbyNodules> listNearbyNodules = new LinkedList<>();
		for (Nodule nodule : nodules) {

			BigDecimal distance = euclidianDistance(primaryNodule, nodule, 0, 120);
			NearbyNodules nearbyNodule = new NearbyNodules(nodule, distance);
			listNearbyNodules.add(nearbyNodule);
		}
	}
}