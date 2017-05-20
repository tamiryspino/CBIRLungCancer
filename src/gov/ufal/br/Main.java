package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jfree.ui.RefineryUtilities;

public class Main {

	public static void main(String[] args) {

		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallSolidNodulesFeatures_Tamirys.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ",";

		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		Set<Nodule> benignNodules = setAllNodulesByMalignance(allNodules, "BENIGN");
		Set<Nodule> malignantNodules = setAllNodulesByMalignance(allNodules, "MALIGNANT");
		System.out.println("Qnt nodulos benignos: " + benignNodules.size() + "\n"
				+ "Qnt nodulos malignos: " + malignantNodules.size());
		
		int qntRanking = 10;
		Distances distanceType = Distances.EUCLIDIAN;
		List<FeaturesEnum> features = new ArrayList<>();

		/********************************
		 * SELECIONA NÓDULOS ALEATÓRIOS *
		 ********************************/
		List<EvaluatedNodules> aleatoryEvaluatedNodules = new ArrayList<>();
				
		/** Leave one out para cada execução
		 * Depois faz a média das precisões **/
		Set<Nodule> benignNodulesWithoutOne;
		for(Nodule noduleByMalignance : benignNodules) {
			benignNodulesWithoutOne = new HashSet<>();
			benignNodulesWithoutOne.addAll(benignNodules);
			benignNodulesWithoutOne.remove(noduleByMalignance);
			
			aleatoryEvaluatedNodules.add(new EvaluatedNodules("BENIGN", allNodules, benignNodulesWithoutOne));
		}
		int qnt = 0;
		PrecisionByFeatures precisionByAllLeaveOneOut = new PrecisionByFeatures("Relieff By All");
		for (EvaluatedNodules aleatoryEvaluatedNodule : aleatoryEvaluatedNodules) {
			/**
			 * Seta os nódulos vizinhos para features selecionadas pelo
			 * algoritmo Relieff
			 **/
			qnt++;
			System.out.println("Setando nódulos vizinhos..." + qnt);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By All", SelectedFeaturesEnum.RELIEFF_BY_ALL, distanceType,
					qntRanking);
			//TODO get(0) pega apenas o Relieff By All
			System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(0).getAverageOfPrecisionByRanking());
			System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(0).getFeatureName());
			precisionByAllLeaveOneOut.addListOfPrecisionsByFeature(aleatoryEvaluatedNodule.getPrecisions().get(0).getAverageOfPrecisionByRanking());
			
			//averagePrecisionForFeatures.add(aleatoryEvaluatedNodule.getPrecisions().get(0).getAverageOfPrecisionByRanking());
			/*aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Nodule Features", relieffByNodule,
					distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Nodule and Edge Sharpness Features",
					relieffByNoduleWithEdgeSharpness, distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Parenchyma Features", relieffByParenchyma,
					distanceType, qntRanking);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("Relieff By Parenchyma and Edge Sharpness Features",
					relieffByParenchymaWithEdgeSharpness, distanceType, qntRanking);*/
//			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(0, 5),
//					aleatoryEvaluatedNodule.getMalignance());

			/**
			 * Seta os nódulos vizinhos para features selecionadas pelo
			 * algoritmo csfSubsetEval
			 **/
	/*		aleatoryEvaluatedNodule.setNearbyNodulesByFeatures("csfSubsetEval By All Features", csfSubsetEvalByAll,
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
*/
			/*features.clear();
			features.add(FeaturesEnum.NODULE_TEXTURE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.NODULE_SHAPE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);			

			features.clear();
			features.add(FeaturesEnum.NODULE_INTENSITY);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.EDGE_SHARPNESS);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.NODULE_SHAPE);
			features.add(FeaturesEnum.NODULE_TEXTURE);
			features.add(FeaturesEnum.NODULE_INTENSITY);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.NODULE_SHAPE);
			features.add(FeaturesEnum.NODULE_TEXTURE);
			features.add(FeaturesEnum.NODULE_INTENSITY);
			features.add(FeaturesEnum.EDGE_SHARPNESS);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
//			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(5, 11),
//					aleatoryEvaluatedNodule.getMalignance());

			features.clear();
			features.add(FeaturesEnum.PARENCHYMA_TEXTURE);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.PARANCHYMA_INTENSITY);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.PARENCHYMA_TEXTURE);
			features.add(FeaturesEnum.PARANCHYMA_INTENSITY);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);

			features.clear();
			features.add(FeaturesEnum.PARENCHYMA_TEXTURE);
			features.add(FeaturesEnum.PARANCHYMA_INTENSITY);
			features.add(FeaturesEnum.EDGE_SHARPNESS);
			aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(features, distanceType, qntRanking);*/
//			doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions().subList(11, 15),
//					aleatoryEvaluatedNodule.getMalignance());
			
			/** Lista de lista de precisoes **/
		/*	List<PrecisionByFeatures> precisionsOfAleatoryEvaluatedNodule = aleatoryEvaluatedNodule.getPrecisions();
			for(int j = 0; j<precisionsOfAleatoryEvaluatedNodule.size(); j++) {
				String characteristic;
				precisionsOfAleatoryEvaluatedNodule[characteristic].add(aleatoryEvaluatedNodule.getPrecisions().get(j));
			}*/
		}
		System.out.println("Media para todos os leave one out: " + precisionByAllLeaveOneOut.getAverageOfPrecisionByRanking()
		+ "\n Media do array: " + precisionByAllLeaveOneOut.getAveragePrecision());
		//doPrecisionNChart(aleatoryEvaluatedNodule.getPrecisions(), aleatoryEvaluatedNodule.getMalignance());
		/*for(int k=0; k< averagePrecisionForFeatures.size(); k++) {
			System.out.println(averagePrecisionForFeatures.get(k));
		}*/
		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
	}

	private static Set<Nodule> setAllNodulesByMalignance(Set<Nodule> allNodules, String malignance) {
		Set<Nodule> allNodulesByMalignance = new HashSet<>();
		for(Nodule nodule : allNodules) {
			if(malignance.equals(nodule.getMalignance())) {
				allNodulesByMalignance.add(nodule);
			}
		}
		return allNodulesByMalignance;
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
			//182,199,209
			while (scanner.hasNext()) {
				List<String> features = Arrays.asList(scanner.nextLine().split(csvSplitBy));
				nodule = new Nodule(features.get(features.size() - 2), // ID
						features.subList(0, features.size() - 4), // Features
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