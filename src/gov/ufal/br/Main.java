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

	public static void main(String[] args) {

		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallSolidNodulesFeatures_Tamirys.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ",";

		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);

		int qntRanking = 10;
		int qntAleatoryNodules = 10;
		Distances distanceType = Distances.EUCLIDIAN;
		List<GroupFeaturesEnum> features = new ArrayList<>();

		/**
		 * Lista de 20 atributos selecionados utilizando Relieff para todas as
		 * 121 features (nódulo, parênquima e nitidez de borda)
		 **/
		// Features 0 a 120
		final List<GroupFeaturesEnum> relieffByAll = Arrays.asList(GroupFeaturesEnum.MAXIMUM_N,
				GroupFeaturesEnum.SKEWNESS_N, GroupFeaturesEnum.SPHERICALDISPROPORTION,
				GroupFeaturesEnum.DIFFERENCEENDS, GroupFeaturesEnum.ENERGY90_P, GroupFeaturesEnum.ROOTMEANSQUARE_N,
				GroupFeaturesEnum.MINIMUM_N, GroupFeaturesEnum.ENERGY45_P, GroupFeaturesEnum.SKEWNESS,
				GroupFeaturesEnum.MINIMUM_P, GroupFeaturesEnum.SURFACEAREA, GroupFeaturesEnum.MEDIAN_N,
				GroupFeaturesEnum.SD, GroupFeaturesEnum.ENTROPY_N, GroupFeaturesEnum.STANDARDDEVIATION_N,
				GroupFeaturesEnum.SVARIANCE, GroupFeaturesEnum.PVARIANCE, GroupFeaturesEnum.RANGE_P,
				GroupFeaturesEnum.ENERGY0_P, GroupFeaturesEnum.VARIANCE_N);

		/**
		 * Lista de 20 atributos selecionados por Relieff para atributos de
		 * Nódulo
		 **/
		// Features 0 a 58
		final List<GroupFeaturesEnum> relieffByNodule = Arrays.asList(GroupFeaturesEnum.MAXIMUM_N,
				GroupFeaturesEnum.AREA, GroupFeaturesEnum.SKEWNESS_N, GroupFeaturesEnum.ROOTMEANSQUARE_N,
				GroupFeaturesEnum.SPHERICALDISPROPORTION, GroupFeaturesEnum.MINIMUM_N,
				GroupFeaturesEnum.STANDARDDEVIATION_N, GroupFeaturesEnum.MEDIAN_N, GroupFeaturesEnum.ENTROPY_N,
				GroupFeaturesEnum.SURFACEAREA, GroupFeaturesEnum.VARIANCE_N, GroupFeaturesEnum.ENERGY135_N,
				GroupFeaturesEnum.ENERGY45_N, GroupFeaturesEnum.INERTIA90_N, GroupFeaturesEnum.RANGE_N,
				GroupFeaturesEnum.INERTIA45_N, GroupFeaturesEnum.INERTIA0_N, GroupFeaturesEnum.ENERGY_N,
				GroupFeaturesEnum.INERTIA135_N, GroupFeaturesEnum.COMPACTNESS1);

		/**
		 * Lista de 20 atributos selecionados utilizando Relieff para atributos
		 * de Parênquima
		 **/
		// Features de 59 a 108
		final List<GroupFeaturesEnum> relieffByParenchyma = Arrays.asList(GroupFeaturesEnum.ENERGY90_P,
				GroupFeaturesEnum.ENERGY0_P, GroupFeaturesEnum.ENERGY45_P, GroupFeaturesEnum.ENERGY135_P,
				GroupFeaturesEnum.MEAN_P, GroupFeaturesEnum.MEDIAN_P, GroupFeaturesEnum.INERTIA90_P,
				GroupFeaturesEnum.MINIMUM_P, GroupFeaturesEnum.INERTIA45_P, GroupFeaturesEnum.INERTIA135_P,
				GroupFeaturesEnum.ROOTMEANSQUARE_P, GroupFeaturesEnum.ENERGY_P, GroupFeaturesEnum.INERTIA0_P,
				GroupFeaturesEnum.CORRELATION0_P, GroupFeaturesEnum.IDM90_P, GroupFeaturesEnum.CORRELATION45_P,
				GroupFeaturesEnum.CORRELATION135_P, GroupFeaturesEnum.IDM0_P, GroupFeaturesEnum.IDM135_P,
				GroupFeaturesEnum.IDM45_P);

		/**
		 * Lista de 20 atributos selecionados utilizando Relieff para features
		 * de nódulo e nitidez de borda
		 **/
		// Features de 0 a 58 + 109 a 120
		final List<GroupFeaturesEnum> relieffByNoduleWithEdgeSharpness = Arrays.asList(GroupFeaturesEnum.DIAMETER,
				GroupFeaturesEnum.MAXIMUM_N, GroupFeaturesEnum.AREA, GroupFeaturesEnum.SKEWNESS_N,
				GroupFeaturesEnum.ROOTMEANSQUARE_N, GroupFeaturesEnum.DIFFERENCEENDS, GroupFeaturesEnum.MEDIAN_N,
				GroupFeaturesEnum.SD, GroupFeaturesEnum.MINIMUM_N, GroupFeaturesEnum.SPHERICALDISPROPORTION,
				GroupFeaturesEnum.SVARIANCE, GroupFeaturesEnum.STANDARDDEVIATION_N, GroupFeaturesEnum.SURFACEAREA,
				GroupFeaturesEnum.PVARIANCE, GroupFeaturesEnum.ENTROPY_N, GroupFeaturesEnum.VARIANCE_N,
				GroupFeaturesEnum.COMPACTNESS1, GroupFeaturesEnum.RANGE_N, GroupFeaturesEnum.SKEWNESS,
				GroupFeaturesEnum.INERTIA90_N);

		/**
		 * Lista de 20 atributos selecionados utilizando Relieff para atributos
		 * do parênquima e nitidez de borda
		 **/
		// Features 59 a 120
		final List<GroupFeaturesEnum> relieffByParenchymaWithEdgeSharpness = Arrays.asList(GroupFeaturesEnum.SKEWNESS,
				GroupFeaturesEnum.ENERGY90_P, GroupFeaturesEnum.DIFFERENCEENDS, GroupFeaturesEnum.ENERGY0_P,
				GroupFeaturesEnum.ENERGY45_P, GroupFeaturesEnum.SD, GroupFeaturesEnum.ENERGY135_P,
				GroupFeaturesEnum.SVARIANCE, GroupFeaturesEnum.PVARIANCE, GroupFeaturesEnum.KURTOSIS,
				GroupFeaturesEnum.MINIMUM_P, GroupFeaturesEnum.MEAN_P, GroupFeaturesEnum.IDM90_P,
				GroupFeaturesEnum.MEDIAN_P, GroupFeaturesEnum.INERTIA90_P, GroupFeaturesEnum.IDM135_P,
				GroupFeaturesEnum.INERTIA0_P, GroupFeaturesEnum.RANGE_P, GroupFeaturesEnum.IDM0_P,
				GroupFeaturesEnum.IDM45_P);

		List<GroupFeaturesEnum> csfSubsetEvalByAll = Arrays.asList(GroupFeaturesEnum.MAXIMUM_N,
				GroupFeaturesEnum.SKEWNESS_N, GroupFeaturesEnum.AREA, GroupFeaturesEnum.DIAMETER,
				GroupFeaturesEnum.IDM135_N, GroupFeaturesEnum.ENERGY_P);
		List<GroupFeaturesEnum> csfSubsetEvalByParenchymaWithEdgeSharpness = Arrays.asList(GroupFeaturesEnum.AREA,
				GroupFeaturesEnum.ENERGY_P, GroupFeaturesEnum.IDM0_P, GroupFeaturesEnum.SHADE90_P,
				GroupFeaturesEnum.DIFFERENCEENDS, GroupFeaturesEnum.PVARIANCE);
		List<GroupFeaturesEnum> csfSubsetEvalByParenchyma = Arrays.asList(GroupFeaturesEnum.AREA,
				GroupFeaturesEnum.ENERGY_P);
		List<GroupFeaturesEnum> csfSubsetEvalByNoduleOrNoduleWithEdgeSharpness = Arrays.asList(
				GroupFeaturesEnum.MAXIMUM_N, GroupFeaturesEnum.MEAN_N, GroupFeaturesEnum.SKEWNESS_N,
				GroupFeaturesEnum.AREA, GroupFeaturesEnum.DIAMETER, GroupFeaturesEnum.IDM135_N,
				GroupFeaturesEnum.IDM90_N);

		/********************************
		 * SELECIONA NÓDULOS ALEATÓRIOS *
		 ********************************/
		//Set<Nodule> allNodulesAuxBenign = new HashSet<>();
		//allNodulesAuxBenign.addAll(allNodules);
		EvaluatedNodules benignNodules = new EvaluatedNodules("BENIGN", allNodules, qntAleatoryNodules);
		//allNodulesAuxBenign.removeAll(benignNodules.getAleatoryNodulesByMalignance());
		//System.out.println(allNodulesAuxBenign.size());
		EvaluatedNodules malignantNodules = new EvaluatedNodules("MALIGNANT", allNodules, qntAleatoryNodules);
		System.out.println(qntAleatoryNodules + " nódulos aleatórios benignos foram escolhidos!");

		List<EvaluatedNodules> aleatoryEvaluatedNodules = Arrays.asList(benignNodules, malignantNodules);

		for (EvaluatedNodules aleatoryEvaluatedNodule : aleatoryEvaluatedNodules) {
			/**
			 * Seta os nódulos vizinhos para features selecionadas pelo
			 * algoritmo Relieff
			 **/
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By All", relieffByAll, distanceType,
					qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Nodule Features", relieffByNodule,
					distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Nodule and Edge Sharpness Features",
					relieffByNoduleWithEdgeSharpness, distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Parenchyma Features", relieffByParenchyma,
					distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Parenchyma and Edge Sharpness Features",
					relieffByParenchymaWithEdgeSharpness, distanceType, qntRanking);
			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(0, 5),
					aleatoryEvaluatedNodule.getMalignance());

			/**
			 * Seta os nódulos vizinhos para features selecionadas pelo
			 * algoritmo csfSubsetEval
			 **/
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("csfSubsetEval By All Features", csfSubsetEvalByAll,
					distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(
					"csfSubsetEval By Nodule Or Nodule With Edge Sharpness Features",
					csfSubsetEvalByNoduleOrNoduleWithEdgeSharpness, distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("csfSubsetEval By Parenchyma Features",
					csfSubsetEvalByParenchyma, distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("csfSubsetEval By Parenchyma With Edge Sharpness",
					csfSubsetEvalByParenchymaWithEdgeSharpness, distanceType, qntRanking);
			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(5, 9),
					aleatoryEvaluatedNodule.getMalignance());

			features.add(GroupFeaturesEnum.ALL_FEATURES);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.NODULE_TEXTURE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.NODULE_SHAPE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.EDGE_SHARPNESS);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.NODULE_SHAPE);
			features.add(GroupFeaturesEnum.NODULE_TEXTURE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.NODULE_SHAPE);
			features.add(GroupFeaturesEnum.NODULE_TEXTURE);
			features.add(GroupFeaturesEnum.EDGE_SHARPNESS);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(9, 15),
					aleatoryEvaluatedNodule.getMalignance());

			features.clear();
			features.add(GroupFeaturesEnum.PARENCHYMA_TEXTURE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.PARANCHYMA_INTENSITY);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.PARENCHYMA_TEXTURE);
			features.add(GroupFeaturesEnum.PARANCHYMA_INTENSITY);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(GroupFeaturesEnum.PARENCHYMA_TEXTURE);
			features.add(GroupFeaturesEnum.PARANCHYMA_INTENSITY);
			features.add(GroupFeaturesEnum.EDGE_SHARPNESS);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(15, 19),
					aleatoryEvaluatedNodule.getMalignance());

			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions(), aleatoryEvaluatedNodule.getMalignance());
		}
		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
	}

	public static void doPrecisionNChart(List<PrecisionByFeatures> precisions, String malignance) {
		XYLineChart precisionChart = new XYLineChart(precisions, "Precisão", "Precisão para Nódulos " + malignance);
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