package gov.ufal.br;

public enum GroupFeaturesEnum {

	//fazer um p10 só com nódulo, outro só com parenquima e outro com todo mundo
	//usar relief para pegar as features para cada um desses conj.
	ALL_FEATURES("Todas features",0,120),
	NODULE_INTENSITY("Intensidade do Nódulo",0,13),
	NODULE_SHAPE("Forma do Nódulo", 14, 22),
	NODULE_TEXTURE("Textura do Nódulo", 23, 58),
	PARANCHYMA_INTENSITY("Intensidade do Parênquima", 59, 72),
	PARENCHYMA_TEXTURE("Textura do Parênquima", 73, 108),
	EDGE_SHARPNESS("Nitidez de Borda", 109, 120), //colocar nos dois ou em um dos dois ou nos 3 conj?
	MALIGNANCE("Malignancia", 122, 123);
	
	String featureName;
	int inicialIndex;
	int finalIndex;
	
	private GroupFeaturesEnum(String featureName, int inicialIndex, int finalIndex) {
		this.featureName = featureName;
		this.inicialIndex = inicialIndex;
		this.finalIndex = finalIndex;
	}
	
	public String getFeatureName(){
		return featureName;
	}

	public int getInicialIndex() {
		return inicialIndex;
	}

	public int getFinalIndex() {
		return finalIndex;
	}
}
