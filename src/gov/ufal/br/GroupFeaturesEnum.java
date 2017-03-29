package gov.ufal.br;

public enum GroupFeaturesEnum {

	ALL_FEATURES(0,120),
	NODULE_INTENSITY(0,13),
	PARANCHYMA_INTENSITY(14,27),
	NODULE_SHAPE(28,36),
	NODULE_TEXTURE(37, 72),
	PARENCHYMA_TEXTURE(73, 108),
	EDGE_SHARPNESS(109, 120);
	
	int inicialIndex;
	int finalIndex;
	
	private GroupFeaturesEnum(int inicialIndex, int finalIndex) {
		this.inicialIndex = inicialIndex;
		this.finalIndex = finalIndex;
	}

	public int getInicialIndex() {
		return inicialIndex;
	}

	public int getFinalIndex() {
		return finalIndex;
	}
}
