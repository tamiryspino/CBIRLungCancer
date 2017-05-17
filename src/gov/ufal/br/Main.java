package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.jfree.ui.RefineryUtilities;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallSolidNodulesFeatures_Tamirys.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ",";
		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		int qntRanking = 10;
		int qntAleatoryNodules = 10;
		
		EvaluatedNodules benignNodules = new EvaluatedNodules("BENIGN", allNodules, qntAleatoryNodules);
		System.out.println(qntAleatoryNodules + " nódulos aleatórios benignos foram escolhidos!");
		EvaluatedNodules malignantNodules = new EvaluatedNodules("MALIGNANT", allNodules, qntAleatoryNodules);
		System.out.println(qntAleatoryNodules + " nódulos aleatórios malignos foram escolhidos!");
		
		Distances distanceType = Distances.EUCLIDIAN;
		
		List<GroupFeaturesEnum> features = new ArrayList<>();
		features.add(GroupFeaturesEnum.ALL_FEATURES);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por todas as features para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		//doPrecisionNChart(benignNodules.getPrecisions(), benignNodules.getMalignance());
		
		//System.out.println("Calculando lista de nódulos vizinhos mais próximos por todas as features para cada nódulos aleatórios MALIGNOS");
		//malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		features.clear();
		
		features.add(GroupFeaturesEnum.NODULE_TEXTURE);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por textura para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		//doPrecisionNChart(benignNodules.getPrecisions(), benignNodules.getMalignance());
		//System.out.println("Calculando lista de nódulos vizinhos mais próximos por textura para cada nódulos aleatórios MALIGNOS");
		//malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		features.clear();
		features.add(GroupFeaturesEnum.NODULE_SHAPE);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		//doPrecisionNChart(benignNodules.getPrecisions(), benignNodules.getMalignance());
		//System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma para cada nódulos aleatórios MALIGNOS");
		//malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		features.clear();
		features.add(GroupFeaturesEnum.NODULE_SHAPE);
		features.add(GroupFeaturesEnum.NODULE_TEXTURE);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma e textura para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		//doPrecisionNChart(benignNodules.getPrecisions(), benignNodules.getMalignance());
		
		//System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma e textura para cada nódulos aleatórios MALIGNOS");
		//malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		/*********************************** FEATURES SELECIONADAS ******************************************/
		//Valor do index csv correspondente é i-1
		features = new ArrayList<>();
		
		/** Lista de 20 atributos selecionados utilizando Relieff para
		 * todas as 121 features (nódulo, parênquima e nitidez de borda) **/
		features = Arrays.asList(GroupFeaturesEnum.MAXIMUM_N, GroupFeaturesEnum.SKEWNESS_N,
				GroupFeaturesEnum.SPHERICALDISPROPORTION, GroupFeaturesEnum.DIFFERENCEENDS,
				GroupFeaturesEnum.ENERGY90_P, GroupFeaturesEnum.ROOTMEANSQUARE_N, GroupFeaturesEnum.MINIMUM_N,
				GroupFeaturesEnum.ENERGY45_P, GroupFeaturesEnum.SKEWNESS, GroupFeaturesEnum.MINIMUM_P,
				GroupFeaturesEnum.SURFACEAREA, GroupFeaturesEnum.MEDIAN_N, GroupFeaturesEnum.SD,
				GroupFeaturesEnum.ENTROPY_N, GroupFeaturesEnum.STANDARDDEVIATION_N, GroupFeaturesEnum.SVARIANCE,
				GroupFeaturesEnum.PVARIANCE, GroupFeaturesEnum.RANGE_P, GroupFeaturesEnum.ENERGY0_P,
				GroupFeaturesEnum.VARIANCE_N);
		System.out.println(features.size());		
		features = new ArrayList<>();
		
		/** Lista de 20 atributos selecionados utilizando Relieff para
		 * features de nódulo e nitidez de borda **/
		//Features de 0 a 58 + 109 a 120
		features = Arrays.asList(GroupFeaturesEnum.DIAMETER, GroupFeaturesEnum.MAXIMUM_N, GroupFeaturesEnum.AREA,
				GroupFeaturesEnum.SKEWNESS_N, GroupFeaturesEnum.ROOTMEANSQUARE_N, GroupFeaturesEnum.DIFFERENCEENDS,
				GroupFeaturesEnum.MEDIAN_N, GroupFeaturesEnum.SD, GroupFeaturesEnum.MINIMUM_N,
				GroupFeaturesEnum.SPHERICALDISPROPORTION, GroupFeaturesEnum.SVARIANCE,
				GroupFeaturesEnum.STANDARDDEVIATION_N, GroupFeaturesEnum.SURFACEAREA, GroupFeaturesEnum.PVARIANCE,
				GroupFeaturesEnum.ENTROPY_N, GroupFeaturesEnum.VARIANCE_N, GroupFeaturesEnum.COMPACTNESS1,
				GroupFeaturesEnum.RANGE_N, GroupFeaturesEnum.SKEWNESS, GroupFeaturesEnum.INERTIA90_N);
		
		System.out.println(features.size());
		features = new ArrayList<>();
		/** Lista de 20 atributos selecionados utilizando Relieff para
		 * atributos do parênquima e nitidez de borda **/
		features = Arrays.asList(GroupFeaturesEnum.SKEWNESS, GroupFeaturesEnum.ENERGY90_P,
				GroupFeaturesEnum.DIFFERENCEENDS, GroupFeaturesEnum.ENERGY0_P, GroupFeaturesEnum.ENERGY45_P,
				GroupFeaturesEnum.SD, GroupFeaturesEnum.ENERGY135_P, GroupFeaturesEnum.SVARIANCE,
				GroupFeaturesEnum.PVARIANCE, GroupFeaturesEnum.KURTOSIS, GroupFeaturesEnum.MINIMUM_P,
				GroupFeaturesEnum.MEAN_P, GroupFeaturesEnum.IDM90_P, GroupFeaturesEnum.MEDIAN_P,
				GroupFeaturesEnum.INERTIA90_P, GroupFeaturesEnum.IDM135_P, GroupFeaturesEnum.INERTIA0_P,
				GroupFeaturesEnum.RANGE_P, GroupFeaturesEnum.IDM0_P, GroupFeaturesEnum.IDM45_P);
		
		System.out.println(features.size());
		features = new ArrayList<>();
		
		//Features 0 a 58
		/** Lista de 20 atributos selecionados por Relieff para
		 * atributos de Nódulo **/
		features = Arrays.asList(GroupFeaturesEnum.MAXIMUM_N, GroupFeaturesEnum.AREA, GroupFeaturesEnum.SKEWNESS_N,
				GroupFeaturesEnum.ROOTMEANSQUARE_N, GroupFeaturesEnum.SPHERICALDISPROPORTION,
				GroupFeaturesEnum.MINIMUM_N, GroupFeaturesEnum.STANDARDDEVIATION_N, GroupFeaturesEnum.MEDIAN_N,
				GroupFeaturesEnum.ENTROPY_N, GroupFeaturesEnum.SURFACEAREA, GroupFeaturesEnum.VARIANCE_N,
				GroupFeaturesEnum.ENERGY135_N, GroupFeaturesEnum.ENERGY45_N, GroupFeaturesEnum.INERTIA90_N,
				GroupFeaturesEnum.RANGE_N, GroupFeaturesEnum.INERTIA45_N, GroupFeaturesEnum.INERTIA0_N,
				GroupFeaturesEnum.ENERGY_N, GroupFeaturesEnum.INERTIA135_N, GroupFeaturesEnum.COMPACTNESS1);
		
		System.out.println(features.size());
		features = new ArrayList<>();
		
		//Features de 59 a 108
		/** Lista de 20 atributos selecionados utilizando Relieff
		 * para atributos de Parênquima **/
		features = Arrays.asList(GroupFeaturesEnum.ENERGY90_P, GroupFeaturesEnum.ENERGY0_P,
				GroupFeaturesEnum.ENERGY45_P, GroupFeaturesEnum.ENERGY135_P, GroupFeaturesEnum.MEAN_P,
				GroupFeaturesEnum.MEDIAN_P, GroupFeaturesEnum.INERTIA90_P, GroupFeaturesEnum.MINIMUM_P,
				GroupFeaturesEnum.INERTIA45_P, GroupFeaturesEnum.INERTIA135_P, GroupFeaturesEnum.ROOTMEANSQUARE_P,
				GroupFeaturesEnum.ENERGY_P, GroupFeaturesEnum.INERTIA0_P, GroupFeaturesEnum.CORRELATION0_P,
				GroupFeaturesEnum.IDM90_P, GroupFeaturesEnum.CORRELATION45_P, GroupFeaturesEnum.CORRELATION135_P,
				GroupFeaturesEnum.IDM0_P, GroupFeaturesEnum.IDM135_P, GroupFeaturesEnum.IDM45_P);
		System.out.println(features.size());
		
		/* Testar para: CfsSubsetEval 4,11,19,23,41,60
		maximum_N
        skewness_N
        area
        diameter
        idm135_N
        energy_P*/
		
		/*Testar para: P + ES: 1,2,24,48,52,58
                     area
                     energy_P
                     idm0_P
                     shade90_P
                     differenceends
                     pvariance*/
		
		/* Testar para: P: 1,2
                     area
                     energy_P*/
		
		/*Testar para: N + ES: 4,5,11,19,23,41,59
                     maximum_N
                     mean_N
                     skewness_N
                     area
                     diameter
                     idm135_N
                     idm90_N*/
		
		/*Testar para: N: 4,5,11,19,23,41,59
                     maximum_N
                     mean_N
                     skewness_N
                     area
                     diameter
                     idm135_N
                     idm90_N*/
		
		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
	}

	public static void doPrecisionNChart(List<PrecisionByFeatures> precisions, String malignance){
		XYLineChart precisionChart = new XYLineChart(precisions, "Precisão",
				"Precisão para Nódulos " + malignance);
		precisionChart.pack();
		RefineryUtilities.centerFrameOnScreen(precisionChart);
		precisionChart.setVisible(true);
	}
	
	public static Set<Nodule> setAllNodules(File csvFile, String csvSplitBy) {
		Set<Nodule> nodules = new HashSet<>();
		Nodule nodule;
		Scanner scanner;

		try {
			scanner = new Scanner(csvFile);
			scanner.nextLine(); // Pula o cabeçalho do arquivo
			while (scanner.hasNext()) {
				List<String> features = Arrays.asList(scanner.nextLine().split(csvSplitBy));
				nodule = new Nodule(features.get(features.size() - 2), // ID
						features.subList(0, features.size() - 5), // Features
						features.get(features.size() - 1)); // Malignance
				nodules.add(nodule);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("O arquivo não foi encontrado. " + e.getMessage());
		}

		return nodules;
	}

}