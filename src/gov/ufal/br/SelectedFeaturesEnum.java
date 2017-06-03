package gov.ufal.br;

import java.util.Arrays;
import java.util.List;

public enum SelectedFeaturesEnum {
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de intensidade do nódulo.
	 * Features 0 a 13.
	 * 14 features.
	 **/
	RELIEFF_NODULE_INTENSITY("Relieff for Nodule Intensity",
			Arrays.asList(FeaturesEnum.MAXIMUM_N, FeaturesEnum.SKEWNESS_N, FeaturesEnum.MINIMUM_N,
					FeaturesEnum.ROOTMEANSQUARE_N, FeaturesEnum.MEDIAN_N, FeaturesEnum.STANDARDDEVIATION_N,
					FeaturesEnum.KURTOSIS_N, FeaturesEnum.VARIANCE_N, FeaturesEnum.ENERGY_N, FeaturesEnum.RANGE_N,
					FeaturesEnum.UNIFORMITY_N, FeaturesEnum.ENTROPY_N, FeaturesEnum.MEANABSOLUTEDEVIATION_N,
					FeaturesEnum.MEAN_N)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de forma do nódulo.
	 * Features 14 a 22.
	 * 9 features.
	 * 
	 **/
	RELIEFF_NODULE_SHAPE("Relieff for Nodule Shape", Arrays.asList(FeaturesEnum.DIAMETER, FeaturesEnum.AREA, FeaturesEnum.SPHERICALDISPROPORTION, FeaturesEnum.SURFACEVOLUMERATIO, FeaturesEnum.SURFACEAREA, FeaturesEnum.COMPACTNESS2, FeaturesEnum.COMPACTNESS1, FeaturesEnum.VOLUME, FeaturesEnum.SPHERICITY)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de textura do nódulo.
	 * Features 23 a 58.
	 * 36 features.
	 **/
	RELIEFF_NODULE_TEXTURE("Relieff for Nodule Texture", Arrays.asList(FeaturesEnum.ENERGY135_N, FeaturesEnum.ENERGY90_N, FeaturesEnum.ENERGY0_N, FeaturesEnum.IDM90_N, FeaturesEnum.IDM45_N, FeaturesEnum.ENERGY45_N, FeaturesEnum.IDM135_N, FeaturesEnum.IDM0_N, FeaturesEnum.HOMOGENEITY0_N, FeaturesEnum.HOMOGENEITY45_N, FeaturesEnum.CORRELATION45_N, FeaturesEnum.CORRELATION135_N, FeaturesEnum.CORRELATION0_N,  FeaturesEnum.HOMOGENEITY90_N, FeaturesEnum.HOMOGENEITY135_N, FeaturesEnum.ENTROPY90_N, FeaturesEnum.ENTROPY45_N, FeaturesEnum.ENTROPY135_N, FeaturesEnum.ENTROPY0_N, FeaturesEnum.CORRELATION90_N, FeaturesEnum.VARIANCE90_N, FeaturesEnum.VARIANCE45_N, FeaturesEnum.VARIANCE135_N, FeaturesEnum.INERTIA0_N, FeaturesEnum.VARIANCE0_N, FeaturesEnum.SHADE90_N, FeaturesEnum.INERTIA135_N, FeaturesEnum.SHADE45_N, FeaturesEnum.INERTIA90_N, FeaturesEnum.INERTIA45_N, FeaturesEnum.PROMENANCE90_N, FeaturesEnum.SHADE135_N, FeaturesEnum.PROMENANCE45_N, FeaturesEnum.SHADE0_N, FeaturesEnum.PROMENANCE135_N, FeaturesEnum.	PROMENANCE0_N)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de intensidade e forma do nódulo.
	 * Features 0 a 22.
	 * 23 features.
	 **/
	RELIEFF_NODULE_INTENSITY_SHAPE("Relieff for Nodule Intensity and Shape", Arrays.asList(FeaturesEnum.MAXIMUM_N, FeaturesEnum.DIAMETER, FeaturesEnum.SKEWNESS_N, FeaturesEnum.AREA, FeaturesEnum.ROOTMEANSQUARE_N, FeaturesEnum.MEDIAN_N, FeaturesEnum.MINIMUM_N, FeaturesEnum.SPHERICALDISPROPORTION, FeaturesEnum.SURFACEAREA, FeaturesEnum.COMPACTNESS2, FeaturesEnum.COMPACTNESS1, FeaturesEnum.STANDARDDEVIATION_N, FeaturesEnum.ENTROPY_N, FeaturesEnum.VARIANCE_N, FeaturesEnum.ENERGY_N, FeaturesEnum.RANGE_N, FeaturesEnum.KURTOSIS_N, FeaturesEnum.SURFACEVOLUMERATIO, FeaturesEnum.UNIFORMITY_N, FeaturesEnum.MEANABSOLUTEDEVIATION_N, FeaturesEnum.VOLUME, FeaturesEnum.MEAN_N, FeaturesEnum.SPHERICITY)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de intensidade e textura do nódulo.
	 * Features 0 a 13 e 23 a 58.
	 * 50 features.
	 **/
	RELIEFF_NODULE_INTENSITY_TEXTURE("Relieff for Nodule Intensity and Texture", Arrays.asList(FeaturesEnum.MAXIMUM_N, 
			FeaturesEnum.SKEWNESS_N, FeaturesEnum.ROOTMEANSQUARE_N, 
			FeaturesEnum.MINIMUM_N, FeaturesEnum.STANDARDDEVIATION_N, 
			FeaturesEnum.ENTROPY_N, FeaturesEnum.VARIANCE_N, 
			FeaturesEnum.MEDIAN_N, FeaturesEnum.ENERGY135_N, 
			FeaturesEnum.ENERGY0_N, FeaturesEnum.ENERGY45_N, 
			FeaturesEnum.INERTIA90_N, FeaturesEnum.RANGE_N, 
			FeaturesEnum.ENERGY_N, FeaturesEnum.ENERGY90_N, 
			FeaturesEnum.INERTIA45_N, FeaturesEnum.INERTIA0_N, 
			FeaturesEnum.IDM90_N, FeaturesEnum.UNIFORMITY_N, 
			FeaturesEnum.IDM45_N, FeaturesEnum.IDM135_N, 
			FeaturesEnum.INERTIA135_N, FeaturesEnum.IDM0_N, 
			FeaturesEnum.KURTOSIS_N, FeaturesEnum.HOMOGENEITY90_N, 
			FeaturesEnum.HOMOGENEITY0_N, FeaturesEnum.HOMOGENEITY45_N, 
			FeaturesEnum.HOMOGENEITY135_N, FeaturesEnum.ENTROPY90_N, 
			FeaturesEnum.ENTROPY45_N, FeaturesEnum.VARIANCE90_N, 
			FeaturesEnum.ENTROPY135_N, FeaturesEnum.VARIANCE45_N, 
			FeaturesEnum.ENTROPY0_N, FeaturesEnum.VARIANCE135_N, 
			FeaturesEnum.VARIANCE0_N, FeaturesEnum.CORRELATION0_N, 
			FeaturesEnum.CORRELATION45_N, FeaturesEnum.CORRELATION135_N, 
			FeaturesEnum.CORRELATION90_N, FeaturesEnum.SHADE90_N, 
			FeaturesEnum.SHADE45_N, FeaturesEnum.SHADE135_N, 
			FeaturesEnum.SHADE0_N, FeaturesEnum.MEANABSOLUTEDEVIATION_N, 
			FeaturesEnum.PROMENANCE90_N, FeaturesEnum.PROMENANCE45_N, 
			FeaturesEnum.PROMENANCE135_N, FeaturesEnum.PROMENANCE0_N, 
			FeaturesEnum.MEAN_N)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de forma e textura do nódulo.
	 * Features 14 a 58.
	 * 45 features.
	 **/
	RELIEFF_NODULE_SHAPE_TEXTURE("Relieff for Nodule Shape and Texture", Arrays.asList(
			FeaturesEnum.DIAMETER, FeaturesEnum.AREA, 
			FeaturesEnum.SPHERICALDISPROPORTION, FeaturesEnum.ENERGY135_N, 
			FeaturesEnum.SURFACEAREA, FeaturesEnum.ENERGY90_N, 
			FeaturesEnum.ENERGY0_N, FeaturesEnum.COMPACTNESS1, 
			FeaturesEnum.SURFACEVOLUMERATIO, FeaturesEnum.ENERGY45_N, 
			FeaturesEnum.IDM45_N, FeaturesEnum.IDM90_N, 
			FeaturesEnum.COMPACTNESS2, FeaturesEnum.IDM135_N, 
			FeaturesEnum.CORRELATION0_N, FeaturesEnum.IDM0_N, 
			FeaturesEnum.CORRELATION135_N, FeaturesEnum.CORRELATION45_N, 
			FeaturesEnum.HOMOGENEITY90_N, FeaturesEnum.INERTIA0_N, 
			FeaturesEnum.HOMOGENEITY45_N, FeaturesEnum.INERTIA90_N, 
			FeaturesEnum.HOMOGENEITY0_N, FeaturesEnum.INERTIA45_N, 
			FeaturesEnum.INERTIA135_N, FeaturesEnum.HOMOGENEITY135_N, 
			FeaturesEnum.ENTROPY90_N, FeaturesEnum.ENTROPY45_N, 
			FeaturesEnum.ENTROPY135_N, FeaturesEnum.ENTROPY0_N, 
			FeaturesEnum.CORRELATION90_N, FeaturesEnum.VARIANCE90_N, 
			FeaturesEnum.VARIANCE45_N, FeaturesEnum.VARIANCE135_N, 
			FeaturesEnum.VARIANCE0_N, FeaturesEnum.VOLUME, 
			FeaturesEnum.SHADE90_N, FeaturesEnum.SHADE45_N, 
			FeaturesEnum.PROMENANCE90_N, FeaturesEnum.SHADE135_N, 
			FeaturesEnum.PROMENANCE45_N, FeaturesEnum.SHADE0_N, 
			FeaturesEnum.PROMENANCE135_N, FeaturesEnum.PROMENANCE0_N, 
			FeaturesEnum.SPHERICITY)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de intensidade do parenquima.
	 * Features 59 a 72.
	 * 14 features.
	 **/
	RELIEFF_PARANCHYMA_INTENSITY("Relieff for Parenchyma Intensity", Arrays.asList(
			FeaturesEnum.MINIMUM_P, FeaturesEnum.ENERGY_P, 
			FeaturesEnum.MEAN_P, FeaturesEnum.MEDIAN_P, 
			FeaturesEnum.ROOTMEANSQUARE_P, FeaturesEnum.ENTROPY_P, 
			FeaturesEnum.RANGE_P, FeaturesEnum.MAXIMUM_P, 
			FeaturesEnum.VARIANCE_P, FeaturesEnum.KURTOSIS_P, 
			FeaturesEnum.UNIFORMITY_P, FeaturesEnum.STANDARDDEVIATION_P, 
			FeaturesEnum.MEANABSOLUTEDEVIATION_P, FeaturesEnum.SKEWNESS_P)),
	
	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * features de textura do parenquima.
	 * Features 73 a 108.
	 * 36 features.
	 **/
	RELIEFF_PARENCHYMA_TEXTURE("Relieff for Parenchyma Texture", Arrays.asList(
			FeaturesEnum.ENERGY90_P, FeaturesEnum.ENERGY45_P, 
			FeaturesEnum.ENERGY0_P, FeaturesEnum.ENERGY135_P, 
			FeaturesEnum.INERTIA90_P, FeaturesEnum.INERTIA45_P, 
			FeaturesEnum.INERTIA135_P, FeaturesEnum.INERTIA0_P, 
			FeaturesEnum.CORRELATION135_P, FeaturesEnum.CORRELATION45_P, 
			FeaturesEnum.CORRELATION0_P, FeaturesEnum.IDM90_P,  
			FeaturesEnum.IDM0_P, FeaturesEnum.IDM45_P, 
			FeaturesEnum.IDM135_P, FeaturesEnum.HOMOGENEITY90_P, 
			FeaturesEnum.HOMOGENEITY45_P, FeaturesEnum.HOMOGENEITY135_P, 
			FeaturesEnum.HOMOGENEITY0_P, FeaturesEnum.ENTROPY90_P, 
			FeaturesEnum.ENTROPY45_P, FeaturesEnum.ENTROPY135_P, 
			FeaturesEnum.CORRELATION90_P, FeaturesEnum.ENTROPY0_P, 
			FeaturesEnum.PROMENANCE45_P, FeaturesEnum.PROMENANCE90_P, 
			FeaturesEnum.PROMENANCE135_P, FeaturesEnum.VARIANCE90_P, 
			FeaturesEnum.PROMENANCE0_P, FeaturesEnum.VARIANCE45_P, 
			FeaturesEnum.VARIANCE135_P, FeaturesEnum.SHADE90_P, 
			FeaturesEnum.VARIANCE0_P, FeaturesEnum.SHADE45_P, 
			FeaturesEnum.SHADE135_P, FeaturesEnum.SHADE0_P)),


	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * todas as 121 features (nódulo, parênquima e nitidez de borda).
	 * Features 0 a 120.
	 * 121 features.
	 **/
	RELIEFF_BY_ALL("Relieff for All Features", Arrays.asList(
			FeaturesEnum.DIAMETER, FeaturesEnum.AREA, FeaturesEnum.MAXIMUM_N, 
			FeaturesEnum.SKEWNESS_N, FeaturesEnum.SPHERICALDISPROPORTION, FeaturesEnum.DIFFERENCEENDS, 
			FeaturesEnum.ENERGY90_P, FeaturesEnum.ROOTMEANSQUARE_N, FeaturesEnum.MINIMUM_N, 
			FeaturesEnum.ENERGY45_P, FeaturesEnum.SKEWNESS, FeaturesEnum.MINIMUM_P, 
			FeaturesEnum.SURFACEAREA, FeaturesEnum.MEDIAN_N, FeaturesEnum.SD, 
			FeaturesEnum.ENTROPY_N, FeaturesEnum.STANDARDDEVIATION_N, FeaturesEnum.SVARIANCE, 
			FeaturesEnum.PVARIANCE, FeaturesEnum.RANGE_P, FeaturesEnum.ENERGY0_P, 
			FeaturesEnum.VARIANCE_N, FeaturesEnum.COMPACTNESS1, FeaturesEnum.INERTIA0_N, 
			FeaturesEnum.ENERGY135_P, FeaturesEnum.ENTROPY_P, FeaturesEnum.COMPACTNESS2, 
			FeaturesEnum.IDM45_N, FeaturesEnum.IDM90_N, FeaturesEnum.IDM135_N, 
			FeaturesEnum.MEAN_P, FeaturesEnum.INERTIA90_N, FeaturesEnum.IDM0_N, 
			FeaturesEnum.UNIFORMITY_P, FeaturesEnum.RANGE_N, FeaturesEnum.INERTIA45_N, 
			FeaturesEnum.INERTIA135_N, FeaturesEnum.ENERGY_N, FeaturesEnum.ENERGY45_N, 
			FeaturesEnum.MEDIAN_P, FeaturesEnum.CORRELATION45_P,  FeaturesEnum.ROOTMEANSQUARE_P, 
			FeaturesEnum.CORRELATION45_N, FeaturesEnum.CORRELATION0_N,  FeaturesEnum.ENERGY0_N, 
			FeaturesEnum.CORRELATION0_P, FeaturesEnum.CORRELATION135_P, FeaturesEnum.CORRELATION135_N, 
			FeaturesEnum.UNIFORMITY_N, FeaturesEnum.KURTOSIS_N, FeaturesEnum.ENERGY135_N, FeaturesEnum.INERTIA90_P, 
			FeaturesEnum.IDM90_P, FeaturesEnum.SUMSQUARES, FeaturesEnum.KURTOSIS, 
			FeaturesEnum.IDM0_P, FeaturesEnum.IDM135_P, FeaturesEnum.INERTIA135_P, 
			FeaturesEnum.HOMOGENEITY90_P, FeaturesEnum.INERTIA0_P, FeaturesEnum.INERTIA45_P, 
			FeaturesEnum.IDM45_P, FeaturesEnum.HOMOGENEITY45_P, FeaturesEnum.GMEAN, 
			FeaturesEnum.HOMOGENEITY135_P, FeaturesEnum.HOMOGENEITY0_P, FeaturesEnum.HOMOGENEITY0_N, 
			FeaturesEnum.HOMOGENEITY90_N, FeaturesEnum.HOMOGENEITY45_N, 
			FeaturesEnum.ENERGY90_N, FeaturesEnum.HOMOGENEITY135_N, 
			FeaturesEnum.ENERGY_P, FeaturesEnum.SUMVALUES, 
			FeaturesEnum.ENTROPY90_P, FeaturesEnum.ENTROPY45_P, 
			FeaturesEnum.ENTROPY135_P, FeaturesEnum.SUMLOGS, 
			FeaturesEnum.STANDARDDEVIATION_P, FeaturesEnum.ENTROPY0_P, 
			FeaturesEnum.ENTROPY90_N, FeaturesEnum.ENTROPY45_N, 
			FeaturesEnum.ENTROPY135_N, FeaturesEnum.ENTROPY0_N, 
			FeaturesEnum.SCM, FeaturesEnum.CORRELATION90_N, FeaturesEnum.CORRELATION90_P, 
			FeaturesEnum.SPHERICITY, FeaturesEnum.MEANABSOLUTEDEVIATION_P, 
			FeaturesEnum.MAXIMUM_P, FeaturesEnum.VARIANCE90_N, 
			FeaturesEnum.VARIANCE45_N, FeaturesEnum.VARIANCE135_N, 
			FeaturesEnum.VARIANCE0_N, FeaturesEnum.VARIANCE_P, 
			FeaturesEnum.SHADE90_N, FeaturesEnum.SHADE45_N, 
			FeaturesEnum.SHADE135_N, FeaturesEnum.SHADE0_N, 
			FeaturesEnum.PROMENANCE90_N, FeaturesEnum.PROMENANCE45_N, 
			FeaturesEnum.PROMENANCE135_N, FeaturesEnum.PROMENANCE0_N, 
			FeaturesEnum.VOLUME, FeaturesEnum.PROMENANCE45_P, 
			FeaturesEnum.PROMENANCE135_P, FeaturesEnum.PROMENANCE0_P, 
			FeaturesEnum.MEANABSOLUTEDEVIATION_N, FeaturesEnum.PROMENANCE90_P, 
			FeaturesEnum.SHADE90_P, FeaturesEnum.AMEAN, 
			FeaturesEnum.SURFACEVOLUMERATIO, FeaturesEnum.SHADE45_P, 
			FeaturesEnum.SHADE135_P, FeaturesEnum.SHADE0_P, 
			FeaturesEnum.VARIANCE90_P, FeaturesEnum.VARIANCE45_P, 
			FeaturesEnum.VARIANCE135_P, FeaturesEnum.SKEWNESS_P, 
			FeaturesEnum.VARIANCE0_P, FeaturesEnum.KURTOSIS_P, 
			FeaturesEnum.MEAN_N)),

	/**
	 * Lista de atributos selecionados utilizando Relieff para
	 * features de nódulo e nitidez de borda.
	 * Features de 0 a 58 + 109 a 120.
	 * 71 features.
	 **/
	RELIEFF_NODULE_WITH_EDGE_SHARPNESS("Relieff for Nodule And Edge Sharpness Features", Arrays.asList(
			FeaturesEnum.DIAMETER, FeaturesEnum.MAXIMUM_N, 
			FeaturesEnum.AREA, FeaturesEnum.SKEWNESS_N, 
			FeaturesEnum.ROOTMEANSQUARE_N, FeaturesEnum.DIFFERENCEENDS, 
			FeaturesEnum.MEDIAN_N, FeaturesEnum.SD, FeaturesEnum.MINIMUM_N, 
			FeaturesEnum.SPHERICALDISPROPORTION, FeaturesEnum.SVARIANCE, 
			FeaturesEnum.STANDARDDEVIATION_N, FeaturesEnum.SURFACEAREA, 
			FeaturesEnum.PVARIANCE, FeaturesEnum.ENTROPY_N, 
			FeaturesEnum.VARIANCE_N, FeaturesEnum.COMPACTNESS1, 
			FeaturesEnum.RANGE_N, FeaturesEnum.SKEWNESS, 
			FeaturesEnum.INERTIA90_N, FeaturesEnum.ENERGY_N, 
			FeaturesEnum.INERTIA0_N, FeaturesEnum.INERTIA45_N, 
			FeaturesEnum.ENERGY45_N, FeaturesEnum.INERTIA135_N, 
			FeaturesEnum.IDM90_N, FeaturesEnum.ENERGY135_N, 
			FeaturesEnum.IDM45_N, FeaturesEnum.IDM135_N, 
			FeaturesEnum.COMPACTNESS2, FeaturesEnum.UNIFORMITY_N, 
			FeaturesEnum.GMEAN, FeaturesEnum.IDM0_N, 
			FeaturesEnum.SUMLOGS, FeaturesEnum.ENERGY0_N, 
			FeaturesEnum.CORRELATION0_N, FeaturesEnum.CORRELATION135_N, 
			FeaturesEnum.CORRELATION45_N, FeaturesEnum.SUMSQUARES,
			FeaturesEnum.KURTOSIS, FeaturesEnum.KURTOSIS_N, 
			FeaturesEnum.ENERGY90_N, FeaturesEnum.HOMOGENEITY0_N, 
			FeaturesEnum.HOMOGENEITY90_N, FeaturesEnum.HOMOGENEITY45_N, 
			FeaturesEnum.SUMVALUES, FeaturesEnum.CORRELATION90_N, 
			FeaturesEnum.HOMOGENEITY135_N, FeaturesEnum.VARIANCE90_N, 
			FeaturesEnum.SCM, FeaturesEnum.ENTROPY90_N, 
			FeaturesEnum.ENTROPY45_N, FeaturesEnum.VARIANCE45_N, 
			FeaturesEnum.ENTROPY135_N, FeaturesEnum.VARIANCE135_N, 
			FeaturesEnum.ENTROPY0_N, FeaturesEnum.VARIANCE0_N, 
			FeaturesEnum.VOLUME, FeaturesEnum.SHADE90_N, 
			FeaturesEnum.SHADE45_N, FeaturesEnum.SHADE135_N, 
			FeaturesEnum.SHADE0_N, FeaturesEnum.PROMENANCE90_N, 
			FeaturesEnum.MEANABSOLUTEDEVIATION_N,FeaturesEnum.AMEAN,
			FeaturesEnum.PROMENANCE45_N,  FeaturesEnum.SPHERICITY, 
			FeaturesEnum.PROMENANCE135_N, FeaturesEnum.PROMENANCE0_N, 
			FeaturesEnum.SURFACEVOLUMERATIO, FeaturesEnum.MEAN_N)),

	/**
	 * Lista de atributos selecionados utilizando Relieff para
	 * features do parênquima e nitidez de borda.
	 * Features 59 a 120.
	 * 62 features.
	 **/
	RELIEFF_PARENCHYMA_WITH_EDGE_SHARPNESS("Relieff for Parênquima and Edge Sharpness", Arrays.asList(
			FeaturesEnum.SKEWNESS, FeaturesEnum.ENERGY90_P, 
			FeaturesEnum.DIFFERENCEENDS, FeaturesEnum.ENERGY0_P, 
			FeaturesEnum.ENERGY45_P, FeaturesEnum.SD, 
			FeaturesEnum.ENERGY135_P, FeaturesEnum.SVARIANCE, 
			FeaturesEnum.PVARIANCE, FeaturesEnum.KURTOSIS, 
			FeaturesEnum.MINIMUM_P, FeaturesEnum.MEAN_P, 
			FeaturesEnum.IDM90_P, FeaturesEnum.MEDIAN_P,
			FeaturesEnum.INERTIA90_P, FeaturesEnum.IDM135_P, 
			FeaturesEnum.INERTIA0_P, FeaturesEnum.RANGE_P, 
			FeaturesEnum.IDM0_P, FeaturesEnum.IDM45_P, 
			FeaturesEnum.INERTIA135_P, FeaturesEnum.ENERGY_P, 
			FeaturesEnum.INERTIA45_P, FeaturesEnum.ROOTMEANSQUARE_P, 
			FeaturesEnum.UNIFORMITY_P, FeaturesEnum.SUMLOGS, 
			FeaturesEnum.GMEAN, FeaturesEnum.SUMVALUES, 
			FeaturesEnum.HOMOGENEITY135_P, FeaturesEnum.ENTROPY_P, 
			FeaturesEnum.SUMSQUARES, FeaturesEnum.HOMOGENEITY0_P, 
			FeaturesEnum.CORRELATION135_P,FeaturesEnum.HOMOGENEITY45_P, 
			FeaturesEnum.HOMOGENEITY90_P, FeaturesEnum.CORRELATION45_P, 
			FeaturesEnum.CORRELATION0_P, FeaturesEnum.ENTROPY90_P, 
			FeaturesEnum.ENTROPY45_P, FeaturesEnum.ENTROPY135_P, 
			FeaturesEnum.ENTROPY0_P, FeaturesEnum.STANDARDDEVIATION_P, 
			FeaturesEnum.CORRELATION90_P, FeaturesEnum.SCM, 
			FeaturesEnum.VARIANCE_P, FeaturesEnum.VARIANCE90_P, 
			FeaturesEnum.MEANABSOLUTEDEVIATION_P, 
			FeaturesEnum.PROMENANCE45_P, FeaturesEnum.VARIANCE45_P, 
			FeaturesEnum.PROMENANCE135_P, FeaturesEnum.VARIANCE135_P, 
			FeaturesEnum.PROMENANCE0_P, FeaturesEnum.PROMENANCE90_P, 
			FeaturesEnum.VARIANCE0_P, FeaturesEnum.MAXIMUM_P, 
			FeaturesEnum.SHADE90_P, FeaturesEnum.SHADE45_P, 
			FeaturesEnum.AMEAN, FeaturesEnum.SHADE135_P, 
			FeaturesEnum.SHADE0_P, FeaturesEnum.SKEWNESS_P, 
			FeaturesEnum.KURTOSIS_P)),

	/** Lista de atributos selecionados utilizando Relieff para
	 * features do nódulo e parênquima.
	 * Features 0 a 108.
	 * 109 features.
	 **/
	RELIEFF_NODULE_WITH_PARENCHYMA("Relieff for Nodule and Parenchyma Features", Arrays.asList(
			FeaturesEnum.DIAMETER,
			FeaturesEnum.AREA, FeaturesEnum.MAXIMUM_N, FeaturesEnum.SKEWNESS_N, FeaturesEnum.SPHERICALDISPROPORTION,
			FeaturesEnum.ENERGY90_P, FeaturesEnum.ROOTMEANSQUARE_N, FeaturesEnum.MINIMUM_P, FeaturesEnum.ENERGY45_P,
			FeaturesEnum.STANDARDDEVIATION_N, FeaturesEnum.MEAN_P, FeaturesEnum.MINIMUM_N, FeaturesEnum.MEDIAN_P,
			FeaturesEnum.ROOTMEANSQUARE_P, FeaturesEnum.ENTROPY_N, FeaturesEnum.SURFACEAREA, FeaturesEnum.VARIANCE_N,
			FeaturesEnum.ENERGY0_P, FeaturesEnum.ENERGY0_N, FeaturesEnum.MEDIAN_N, FeaturesEnum.ENERGY135_N,
			FeaturesEnum.INERTIA0_N, FeaturesEnum.RANGE_P, FeaturesEnum.ENERGY135_P, FeaturesEnum.IDM45_N,
			FeaturesEnum.IDM90_N, FeaturesEnum.INERTIA90_N, FeaturesEnum.IDM135_N, FeaturesEnum.INERTIA135_N,
			FeaturesEnum.INERTIA45_N, FeaturesEnum.COMPACTNESS1, FeaturesEnum.IDM0_N, FeaturesEnum.ENERGY45_N,
			FeaturesEnum.CORRELATION45_N, FeaturesEnum.CORRELATION0_P, FeaturesEnum.CORRELATION45_P, FeaturesEnum.CORRELATION0_N,
			FeaturesEnum.CORRELATION135_P, FeaturesEnum.CORRELATION135_N, FeaturesEnum.COMPACTNESS2,
			FeaturesEnum.INERTIA90_P, FeaturesEnum.UNIFORMITY_N, FeaturesEnum.RANGE_N,
			FeaturesEnum.INERTIA45_P, FeaturesEnum.INERTIA135_P, FeaturesEnum.INERTIA0_P, FeaturesEnum.ENERGY90_N,
			FeaturesEnum.HOMOGENEITY90_P, FeaturesEnum.ENERGY_N, FeaturesEnum.HOMOGENEITY45_P, FeaturesEnum.IDM90_P,
			FeaturesEnum.UNIFORMITY_P, FeaturesEnum.HOMOGENEITY135_P, FeaturesEnum.ENTROPY_P,
			FeaturesEnum.HOMOGENEITY0_P, FeaturesEnum.IDM0_P, FeaturesEnum.HOMOGENEITY0_N, FeaturesEnum.HOMOGENEITY45_N,
			FeaturesEnum.IDM135_P, FeaturesEnum.HOMOGENEITY90_N, FeaturesEnum.IDM45_P, FeaturesEnum.HOMOGENEITY135_N,
			FeaturesEnum.ENERGY_P, FeaturesEnum.ENTROPY90_P, FeaturesEnum.ENTROPY45_P, FeaturesEnum.KURTOSIS_N,
			FeaturesEnum.ENTROPY135_P, FeaturesEnum.ENTROPY0_P, FeaturesEnum.MAXIMUM_P, FeaturesEnum.ENTROPY90_N,
			FeaturesEnum.ENTROPY45_N, FeaturesEnum.ENTROPY135_N, FeaturesEnum.ENTROPY0_N, FeaturesEnum.CORRELATION90_N,
			FeaturesEnum.CORRELATION90_P, FeaturesEnum.STANDARDDEVIATION_P, FeaturesEnum.VARIANCE90_N,
			FeaturesEnum.VARIANCE45_N, FeaturesEnum.VARIANCE135_N, FeaturesEnum.SPHERICITY, FeaturesEnum.VARIANCE0_N,
			FeaturesEnum.VARIANCE_P, FeaturesEnum.SHADE90_N, FeaturesEnum.SHADE45_N,
			FeaturesEnum.MEANABSOLUTEDEVIATION_P, FeaturesEnum.SHADE135_N, FeaturesEnum.PROMENANCE90_N,
			FeaturesEnum.SHADE0_N, FeaturesEnum.PROMENANCE45_N, FeaturesEnum.SURFACEVOLUMERATIO,
			FeaturesEnum.PROMENANCE135_N, FeaturesEnum.PROMENANCE0_N, FeaturesEnum.PROMENANCE45_P,
			FeaturesEnum.PROMENANCE135_P, FeaturesEnum.PROMENANCE0_P, FeaturesEnum.PROMENANCE90_P,
			FeaturesEnum.MEANABSOLUTEDEVIATION_N, FeaturesEnum.SHADE90_P, FeaturesEnum.SHADE45_P,
			FeaturesEnum.SHADE135_P, FeaturesEnum.SHADE0_P, FeaturesEnum.VARIANCE90_P, FeaturesEnum.VARIANCE45_P,
			FeaturesEnum.KURTOSIS_P, FeaturesEnum.VARIANCE135_P, FeaturesEnum.VARIANCE0_P, FeaturesEnum.VOLUME,
			FeaturesEnum.SKEWNESS_P, FeaturesEnum.MEAN_N)),

	/**
	 * Lista de atributos selecionados utilizando Relieff para
	 * atributos de Nódulo.
	 * Features 0 a 58.
	 * 59 features.
	 **/
	RELIEFF_NODULE("Relieff for Nodule Features", Arrays.asList(
			FeaturesEnum.DIAMETER, FeaturesEnum.MAXIMUM_N, 
			FeaturesEnum.AREA, FeaturesEnum.SKEWNESS_N, 
			FeaturesEnum.ROOTMEANSQUARE_N, FeaturesEnum.SPHERICALDISPROPORTION, 
			FeaturesEnum.MINIMUM_N, FeaturesEnum.STANDARDDEVIATION_N, 
			FeaturesEnum.MEDIAN_N, FeaturesEnum.ENTROPY_N, 
			FeaturesEnum.SURFACEAREA, FeaturesEnum.VARIANCE_N, 
			FeaturesEnum.ENERGY135_N, FeaturesEnum.ENERGY45_N, 
			FeaturesEnum.INERTIA90_N, FeaturesEnum.RANGE_N, 
			FeaturesEnum.INERTIA45_N, FeaturesEnum.INERTIA0_N, 
			FeaturesEnum.ENERGY_N, FeaturesEnum.INERTIA135_N, 
			FeaturesEnum.COMPACTNESS1, FeaturesEnum.UNIFORMITY_N, 
			FeaturesEnum.ENERGY0_N, FeaturesEnum.CORRELATION45_N, 
			FeaturesEnum.IDM90_N, FeaturesEnum.CORRELATION0_N, 
			FeaturesEnum.CORRELATION135_N,
			FeaturesEnum.IDM45_N, FeaturesEnum.IDM135_N, 
			FeaturesEnum.IDM0_N, FeaturesEnum.ENERGY90_N, 
			FeaturesEnum.HOMOGENEITY90_N, 
			FeaturesEnum.KURTOSIS_N, FeaturesEnum.HOMOGENEITY0_N, 
			FeaturesEnum.COMPACTNESS2, FeaturesEnum.HOMOGENEITY45_N, 
			FeaturesEnum.HOMOGENEITY135_N, FeaturesEnum.ENTROPY90_N, 
			FeaturesEnum.ENTROPY45_N, FeaturesEnum.ENTROPY135_N, 
			FeaturesEnum.ENTROPY0_N, FeaturesEnum.CORRELATION90_N, 
			FeaturesEnum.VARIANCE90_N, FeaturesEnum.VARIANCE45_N, 
			FeaturesEnum.VARIANCE135_N, FeaturesEnum.VARIANCE0_N, 
			FeaturesEnum.SPHERICITY, FeaturesEnum.SHADE90_N, 
			FeaturesEnum.SURFACEVOLUMERATIO, FeaturesEnum.SHADE45_N, 
			FeaturesEnum.SHADE135_N, FeaturesEnum.VOLUME, 
			FeaturesEnum.SHADE0_N, FeaturesEnum.MEANABSOLUTEDEVIATION_N, 
			FeaturesEnum.PROMENANCE90_N, FeaturesEnum.PROMENANCE45_N, 
			FeaturesEnum.PROMENANCE135_N, FeaturesEnum.PROMENANCE0_N, 
			FeaturesEnum.MEAN_N)),

	/**
	 * Lista de atributos selecionados utilizando Relieff para 
	 * atributos de Parênquima.
	 * Features de 59 a 108.
	 * 50 features.
	 **/
	RELIEFF_PARENCHYMA("Relieff for Parenchyma Features", Arrays.asList(
			FeaturesEnum.ENERGY90_P, FeaturesEnum.ENERGY0_P, 
			FeaturesEnum.ENERGY45_P, FeaturesEnum.ENERGY135_P, 
			FeaturesEnum.MEAN_P, FeaturesEnum.MEDIAN_P, 
			FeaturesEnum.INERTIA90_P, FeaturesEnum.MINIMUM_P, 
			FeaturesEnum.INERTIA45_P, FeaturesEnum.INERTIA135_P, 
			FeaturesEnum.ROOTMEANSQUARE_P, FeaturesEnum.ENERGY_P, 
			FeaturesEnum.INERTIA0_P, 
			FeaturesEnum.IDM90_P,FeaturesEnum.CORRELATION0_P, 
			FeaturesEnum.CORRELATION45_P, 
			FeaturesEnum.CORRELATION135_P, FeaturesEnum.IDM0_P, 
			FeaturesEnum.IDM135_P, FeaturesEnum.IDM45_P, 
			FeaturesEnum.HOMOGENEITY90_P, FeaturesEnum.HOMOGENEITY135_P, 
			FeaturesEnum.HOMOGENEITY45_P, FeaturesEnum.HOMOGENEITY0_P, 
			FeaturesEnum.ENTROPY90_P, FeaturesEnum.ENTROPY45_P, 
			FeaturesEnum.ENTROPY135_P, FeaturesEnum.RANGE_P, 
			FeaturesEnum.ENTROPY0_P, FeaturesEnum.CORRELATION90_P, 
			FeaturesEnum.VARIANCE90_P, FeaturesEnum.VARIANCE45_P, 
			FeaturesEnum.VARIANCE135_P, FeaturesEnum.VARIANCE0_P, 
			FeaturesEnum.VARIANCE_P, FeaturesEnum.PROMENANCE45_P, 
			FeaturesEnum.PROMENANCE90_P, FeaturesEnum.PROMENANCE135_P, 
			FeaturesEnum.MAXIMUM_P, FeaturesEnum.STANDARDDEVIATION_P, 
			FeaturesEnum.PROMENANCE0_P, FeaturesEnum.SHADE90_P, 
			FeaturesEnum.SHADE45_P, FeaturesEnum.SHADE135_P, 
			FeaturesEnum.KURTOSIS_P, FeaturesEnum.SHADE0_P, 
			FeaturesEnum.MEANABSOLUTEDEVIATION_P, FeaturesEnum.ENTROPY_P, 
			FeaturesEnum.UNIFORMITY_P, FeaturesEnum.SKEWNESS_P)),

	/** Lista de atribuitos selecionados utilizando Relieff para
	 * atributos de Nitidez de Borda.
	 * Features 109 a 120.
	 * 12 features.
	 **/
	RELIEFF_EDGE_SHARPNESS("Relieff for Edge Sharpness",
			Arrays.asList(FeaturesEnum.DIFFERENCEENDS, FeaturesEnum.SD, 
					FeaturesEnum.GMEAN, FeaturesEnum.SVARIANCE,
					FeaturesEnum.SKEWNESS, FeaturesEnum.PVARIANCE, 
					FeaturesEnum.SUMSQUARES, FeaturesEnum.KURTOSIS,
					FeaturesEnum.SUMVALUES, FeaturesEnum.SUMLOGS, 
					FeaturesEnum.AMEAN, FeaturesEnum.SCM));

	String groupName;
	List<FeaturesEnum> features;
	Double max_precision;
	Double qntFeatures;

	private SelectedFeaturesEnum(String groupName, List<FeaturesEnum> features) {
		this.groupName = groupName;
		this.features = features;
	}

	public Double getMax_precision() {
		return max_precision;
	}
	
	public void setMax_precision(Double max_precision) {
		this.max_precision = max_precision;
	}
	
	public Double getQntFeatures() {
		return qntFeatures;
	}
	
	public void setQntFeatures(Double qntFeatures) {
		this.qntFeatures = qntFeatures;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<FeaturesEnum> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeaturesEnum> features) {
		this.features = features;
	}

	/*
	 * List<FeaturesEnum> csfSubsetEvalByAll =
	 * Arrays.asList(FeaturesEnum.MAXIMUM_N, FeaturesEnum.SKEWNESS_N,
	 * FeaturesEnum.AREA, FeaturesEnum.DIAMETER, FeaturesEnum.IDM135_N,
	 * FeaturesEnum.ENERGY_P); List<FeaturesEnum>
	 * csfSubsetEvalByParenchymaWithEdgeSharpness =
	 * Arrays.asList(FeaturesEnum.AREA, FeaturesEnum.ENERGY_P,
	 * FeaturesEnum.IDM0_P, FeaturesEnum.SHADE90_P, FeaturesEnum.DIFFERENCEENDS,
	 * FeaturesEnum.PVARIANCE); List<FeaturesEnum> csfSubsetEvalByParenchyma =
	 * Arrays.asList(FeaturesEnum.AREA, FeaturesEnum.ENERGY_P);
	 * List<FeaturesEnum> csfSubsetEvalByNoduleOrNoduleWithEdgeSharpness =
	 * Arrays.asList( FeaturesEnum.MAXIMUM_N, FeaturesEnum.MEAN_N,
	 * FeaturesEnum.SKEWNESS_N, FeaturesEnum.AREA, FeaturesEnum.DIAMETER,
	 * FeaturesEnum.IDM135_N, FeaturesEnum.IDM90_N);
	 */

}
