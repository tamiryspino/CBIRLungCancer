package gov.ufal.br;

public enum FeaturesEnum {

	ENERGY_N("energy_N",0,1),
	ENTROPY_N("entropy_N",1,2),
	KURTOSIS_N("kurtosis_N",2,3),
	MAXIMUM_N("maximum_N",3,4),
	MEAN_N("mean_N",4,5),
	MEANABSOLUTEDEVIATION_N("meanAbsoluteDeviation_N",5,6),
	MEDIAN_N("median_N",6,7),
	MINIMUM_N("minimum_N",7,8),
	RANGE_N("range_N",8,9),
	ROOTMEANSQUARE_N("rootMeanSquare_N",9,10),
	SKEWNESS_N("skewness_N",10,11),
	STANDARDDEVIATION_N("standardDeviation_N",11,12),
	UNIFORMITY_N("uniformity_N",12,13),
	VARIANCE_N("variance_N",13,14),
	ENERGY_P("energy_P",14,15),
	ENTROPY_P("entropy_P",15,16),
	KURTOSIS_P("kurtosis_P",16,17),
	MAXIMUM_P("maximum_P",17,18),
	MEAN_P("mean_P",18,19),
	MEANABSOLUTEDEVIATION_P("meanAbsoluteDeviation_P",19,20),
	MEDIAN_P("median_P",20,21),
	MINIMUM_P("minimum_P",21,22),
	RANGE_P("range_P",22,23),
	ROOTMEANSQUARE_P("rootMeanSquare_P",23,24),
	SKEWNESS_P("skewness_P",24,25),
	STANDARDDEVIATION_P("standardDeviation_P",25,26),
	UNIFORMITY_P("uniformity_P",26,27),
	VARIANCE_P("variance_P",27,28),
	COMPACTNESS1("compactness1",28,29),
	COMPACTNESS2("compactness2",29,30),
	SPHERICALDISPROPORTION("sphericalDisproportion",30,31),
	SPHERICITY("sphericity",31,32),
	AREA("area",32,33),
	SURFACEAREA("surfaceArea",33,34),
	SURFACEVOLUMERATIO("surfaceVolumeRatio",34,35),
	VOLUME("volume",35,36),
	DIAMETER("diameter",36,37),
	ENERGY0_N("energy0_N",37,38),
	ENTROPY0_N("entropy0_N",38,39),
	INERTIA0_N("inertia0_N",39,40),
	HOMOGENEITY0_N("homogeneity0_N",40,41),
	CORRELATION0_N("correlation0_N",41,42),
	SHADE0_N("shade0_N",42,43),
	PROMENANCE0_N("promenance0_N",43,44),
	VARIANCE0_N("variance0_N",44,45),
	IDM0_N("idm0_N",45,46),
	ENERGY135_N("energy135_N",46,47),
	ENTROPY135_N("entropy135_N",47,48),
	INERTIA135_N("inertia135_N",48,49),
	HOMOGENEITY135_N("homogeneity135_N",49,50),
	CORRELATION135_N("correlation135_N",50,51),
	SHADE135_N("shade135_N",51,52),
	PROMENANCE135_N("promenance135_N",52,53),
	VARIANCE135_N("variance135_N",53,54),
	IDM135_N("idm135_N",54,55),
	ENERGY45_N("energy45_N",55,56),
	ENTROPY45_N("entropy45_N",56,57),
	INERTIA45_N("inertia45_N",57,58),
	HOMOGENEITY45_N("homogeneity45_N",58,59),
	CORRELATION45_N("correlation45_N",59,60),
	SHADE45_N("shade45_N",60,61),
	PROMENANCE45_N("promenance45_N",61,62),
	VARIANCE45_N("variance45_N",62,63),
	IDM45_N("idm45_N",63,64),
	ENERGY90_N("energy90_N",64,65),
	ENTROPY90_N("entropy90_N",65,66),
	INERTIA90_N("inertia90_N",66,67),
	HOMOGENEITY90_N("homogeneity90_N",67,68),
	CORRELATION90_N("correlation90_N",68,69),
	SHADE90_N("shade90_N",69,70),
	PROMENANCE90_N("promenance90_N",70,71),
	VARIANCE90_N("variance90_N",71,72),
	IDM90_N("idm90_N",72,73),
	ENERGY0_P("energy0_P",73,74),
	ENTROPY0_P("entropy0_P",74,75),
	INERTIA0_P("inertia0_P",75,76),
	HOMOGENEITY0_P("homogeneity0_P",76,77),
	CORRELATION0_P("correlation0_P",77,78),
	SHADE0_P("shade0_P",78,79),
	PROMENANCE0_P("promenance0_P",79,80),
	VARIANCE0_P("variance0_P",80,81),
	IDM0_P("idm0_P",81,82),
	ENERGY135_P("energy135_P",82,83),
	ENTROPY135_P("entropy135_P",83,84),
	INERTIA135_P("inertia135_P",84,85),
	HOMOGENEITY135_P("homogeneity135_P",85,86),
	CORRELATION135_P("correlation135_P",86,87),
	SHADE135_P("shade135_P",87,88),
	PROMENANCE135_P("promenance135_P",88,89),
	VARIANCE135_P("variance135_P",89,90),
	IDM135_P("idm135_P",90,91),
	ENERGY45_P("energy45_P",91,92),
	ENTROPY45_P("entropy45_P",92,93),
	INERTIA45_P("inertia45_P",93,94),
	HOMOGENEITY45_P("homogeneity45_P",94,95),
	CORRELATION45_P("correlation45_P",95,96),
	SHADE45_P("shade45_P",96,97),
	PROMENANCE45_P("promenance45_P",97,98),
	VARIANCE45_P("variance45_P",98,99),
	IDM45_P("idm45_P",99,100),
	ENERGY90_P("energy90_P",100,101),
	ENTROPY90_P("entropy90_P",101,102),
	INERTIA90_P("inertia90_P",102,103),
	HOMOGENEITY90_P("homogeneity90_P",103,104),
	CORRELATION90_P("correlation90_P",104,105),
	SHADE90_P("shade90_P",105,106),
	PROMENANCE90_P("promenance90_P",106,107),
	VARIANCE90_P("variance90_P",107,108),
	IDM90_P("idm90_P",108,109),
	DIFFERENCEENDS("differenceends",109,110),
	SUMVALUES("sumvalues",110,111),
	SUMSQUARES("sumsquares",111,112),
	SUMLOGS("sumlogs",112,113),
	AMEAN("amean",113,114),
	GMEAN("gmean",114,115),
	PVARIANCE("pvariance",115,116),
	SVARIANCE("svariance",116,117),
	SD("sd",117,118),
	KURTOSIS("kurtosis",118,119),
	SKEWNESS("skewness",119,120),
	SCM("scm",120,121),;

	
	String featureName;
	int inicialIndex;
	int finalIndex;
	
	private FeaturesEnum(String featureName, int inicialIndex, int finalIndex) {
		this.featureName = featureName;
		this.inicialIndex = inicialIndex;
		this.finalIndex = finalIndex;
	}
	
	public String getFeatureName(){
		return featureName;
	}

	public int getInicialIndex(){
		return inicialIndex;
	}

	public int getFinalIndex(){
		return finalIndex;
	}
}
