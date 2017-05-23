package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	public static void main(String[] args) throws IOException {

		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallSolidNodulesFeatures_Tamirys.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ",";
		int qntRanking = 10;
		Distances distanceType = Distances.EUCLIDIAN;

		/** Max 59 features **/
		List<SelectedFeaturesEnum> relieffForNodule = Arrays.asList(SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY,
				SelectedFeaturesEnum.RELIEFF_NODULE_SHAPE, SelectedFeaturesEnum.RELIEFF_NODULE_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY_SHAPE,
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE_SHAPE_TEXTURE, SelectedFeaturesEnum.RELIEFF_NODULE);

		/**Max 50 features **/
		List<SelectedFeaturesEnum> relieffForParenchyma = Arrays.asList(
				SelectedFeaturesEnum.RELIEFF_PARANCHYMA_INTENSITY, SelectedFeaturesEnum.RELIEFF_PARENCHYMA_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA);

		/** Max 12 features **/
		List<SelectedFeaturesEnum> relieffForEdgeSharpness = Arrays.asList(SelectedFeaturesEnum.RELIEFF_EDGE_SHARPNESS);

		/** Max 121 features **/
		List<SelectedFeaturesEnum> relieffForCombinationOfFeatures = Arrays.asList(
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_EDGE_SHARPNESS,
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_PARENCHYMA,
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA_WITH_EDGE_SHARPNESS, SelectedFeaturesEnum.RELIEFF_BY_ALL);

		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		Set<Nodule> benignNodules = setAllNodulesByMalignance(allNodules, "BENIGN");
		Set<Nodule> malignantNodules = setAllNodulesByMalignance(allNodules, "MALIGNANT");
		System.out.println("Qnt nodulos benignos: " + benignNodules.size() + "\n" + "Qnt nodulos malignos: "
				+ malignantNodules.size());

		/********************************
		 * SELECIONA NÓDULOS ALEATÓRIOS *
		 ********************************/
		List<EvaluatedNodules> aleatoryEvaluatedBenignNodules = new ArrayList<>();

		/**
		 * Leave one out para cada execução Depois faz a média das precisões
		 **/
		Set<Nodule> benignNodulesWithoutOne;
		for (Nodule noduleByMalignance : benignNodules) {
			benignNodulesWithoutOne = new HashSet<>();
			benignNodulesWithoutOne.addAll(benignNodules);
			benignNodulesWithoutOne.remove(noduleByMalignance);

			aleatoryEvaluatedBenignNodules.add(new EvaluatedNodules("BENIGN", allNodules, benignNodulesWithoutOne));
		}

		List<PrecisionByFeatures> precisionsByAllLeaveOneOut;

		PrecisionByFeatures precisionByFeatures;
		int qntOfRelieffFeatures = 5;
		int count = 0;
		while (qntOfRelieffFeatures < 65) {
			precisionsByAllLeaveOneOut = new ArrayList<>();
			System.out.println("Qnt Relieff Features: " + qntOfRelieffFeatures);
			for (int e = 0; e < relieffForNodule.size(); e++) {

				List<FeaturesEnum> selectedFeatures = relieffForNodule.get(e).getFeatures().subList(0,
						relieffForNodule.get(e).getFeatures().size() > qntOfRelieffFeatures ? qntOfRelieffFeatures
								: relieffForNodule.get(e).getFeatures().size());

				showSelectedFeatures(selectedFeatures);
				if (!selectedFeatures.isEmpty()) {

					precisionByFeatures = new PrecisionByFeatures(relieffForNodule.get(e).getGroupName());
					/**
					 * Seta os nódulos vizinhos para features selecionadas pelo
					 * algoritmo Relieff
					 **/
					for (EvaluatedNodules aleatoryEvaluatedNodule : aleatoryEvaluatedBenignNodules) {
						aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(selectedFeatures, distanceType, qntRanking);

						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getAverageOfPrecisionByRanking());
						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getFeatureName());
						precisionByFeatures.addListOfPrecisionsByFeature(
								aleatoryEvaluatedNodule.getPrecisions().get(count).getAverageOfPrecisionByRanking());
					}
					precisionByFeatures.updatePrecision();
					precisionsByAllLeaveOneOut.add(precisionByFeatures);
					count++;
					System.out.println("Para atributos: " + precisionByFeatures.getFeatureName() + "\n"
							+ "Media para todos os leave one out: "
							+ precisionByFeatures.getAverageOfPrecisionByRanking() + "\n Media do array: "
							+ precisionByFeatures.getAveragePrecision() + " +- "
							+ precisionByFeatures.getStandartDeviation());

					System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
				}
			}
			StringBuilder nameOfFile = new StringBuilder();
			nameOfFile.append(qntOfRelieffFeatures);
			nameOfFile.append("_");
			nameOfFile.append("NoduleFeatures");
			doPrecisionNChart(nameOfFile.toString(), precisionsByAllLeaveOneOut, "BENIGN");
			qntOfRelieffFeatures += 5;
		}
		
		qntOfRelieffFeatures = 5;
		count = 0;
		while (qntOfRelieffFeatures < 55) {
			precisionsByAllLeaveOneOut = new ArrayList<>();
			System.out.println("Qnt Relieff Features: " + qntOfRelieffFeatures);
			for (int e = 0; e < relieffForParenchyma.size(); e++) {

				List<FeaturesEnum> selectedFeatures = relieffForParenchyma.get(e).getFeatures().subList(0,
						relieffForParenchyma.get(e).getFeatures().size() > qntOfRelieffFeatures ? qntOfRelieffFeatures
								: relieffForParenchyma.get(e).getFeatures().size());

				showSelectedFeatures(selectedFeatures);
				if (!selectedFeatures.isEmpty()) {

					precisionByFeatures = new PrecisionByFeatures(relieffForParenchyma.get(e).getGroupName());
					/**
					 * Seta os nódulos vizinhos para features selecionadas pelo
					 * algoritmo Relieff
					 **/
					for (EvaluatedNodules aleatoryEvaluatedNodule : aleatoryEvaluatedBenignNodules) {
						aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(selectedFeatures, distanceType, qntRanking);

						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getAverageOfPrecisionByRanking());
						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getFeatureName());
						precisionByFeatures.addListOfPrecisionsByFeature(
								aleatoryEvaluatedNodule.getPrecisions().get(count).getAverageOfPrecisionByRanking());
					}
					precisionByFeatures.updatePrecision();
					precisionsByAllLeaveOneOut.add(precisionByFeatures);
					count++;
					System.out.println("Para atributos: " + precisionByFeatures.getFeatureName() + "\n"
							+ "Media para todos os leave one out: "
							+ precisionByFeatures.getAverageOfPrecisionByRanking() + "\n Media do array: "
							+ precisionByFeatures.getAveragePrecision() + " +- "
							+ precisionByFeatures.getStandartDeviation());

					System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
				}
			}
			StringBuilder nameOfFile = new StringBuilder();
			nameOfFile.append(qntOfRelieffFeatures);
			nameOfFile.append("_");
			nameOfFile.append("ParenchymaFeatures");
			doPrecisionNChart(nameOfFile.toString(), precisionsByAllLeaveOneOut, "BENIGN");
			qntOfRelieffFeatures += 5;
		}
		
		

		qntOfRelieffFeatures = 5;
		count = 0;
		while (qntOfRelieffFeatures < 20) {
			precisionsByAllLeaveOneOut = new ArrayList<>();
			System.out.println("Qnt Relieff Features: " + qntOfRelieffFeatures);
			for (int e = 0; e < relieffForEdgeSharpness.size(); e++) {

				List<FeaturesEnum> selectedFeatures = relieffForEdgeSharpness.get(e).getFeatures().subList(0,
						relieffForEdgeSharpness.get(e).getFeatures().size() > qntOfRelieffFeatures ? qntOfRelieffFeatures
								: relieffForEdgeSharpness.get(e).getFeatures().size());

				showSelectedFeatures(selectedFeatures);
				if (!selectedFeatures.isEmpty()) {

					precisionByFeatures = new PrecisionByFeatures(relieffForEdgeSharpness.get(e).getGroupName());
					/**
					 * Seta os nódulos vizinhos para features selecionadas pelo
					 * algoritmo Relieff
					 **/
					for (EvaluatedNodules aleatoryEvaluatedNodule : aleatoryEvaluatedBenignNodules) {
						aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(selectedFeatures, distanceType, qntRanking);

						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getAverageOfPrecisionByRanking());
						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getFeatureName());
						precisionByFeatures.addListOfPrecisionsByFeature(
								aleatoryEvaluatedNodule.getPrecisions().get(count).getAverageOfPrecisionByRanking());
					}
					precisionByFeatures.updatePrecision();
					precisionsByAllLeaveOneOut.add(precisionByFeatures);
					count++;
					System.out.println("Para atributos: " + precisionByFeatures.getFeatureName() + "\n"
							+ "Media para todos os leave one out: "
							+ precisionByFeatures.getAverageOfPrecisionByRanking() + "\n Media do array: "
							+ precisionByFeatures.getAveragePrecision() + " +- "
							+ precisionByFeatures.getStandartDeviation());

					System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
				}
			}
			StringBuilder nameOfFile = new StringBuilder();
			nameOfFile.append(qntOfRelieffFeatures);
			nameOfFile.append("_");
			nameOfFile.append("EdgeSharpnessFeatures");
			doPrecisionNChart(nameOfFile.toString(), precisionsByAllLeaveOneOut, "BENIGN");
			qntOfRelieffFeatures += 5;
		}
		
		
		qntOfRelieffFeatures = 5;
		count = 0;
		while (qntOfRelieffFeatures < 125) {
			precisionsByAllLeaveOneOut = new ArrayList<>();
			System.out.println("Qnt Relieff Features: " + qntOfRelieffFeatures);
			for (int e = 0; e < relieffForCombinationOfFeatures.size(); e++) {

				List<FeaturesEnum> selectedFeatures = relieffForCombinationOfFeatures.get(e).getFeatures().subList(0,
						relieffForCombinationOfFeatures.get(e).getFeatures().size() > qntOfRelieffFeatures ? qntOfRelieffFeatures
								: relieffForCombinationOfFeatures.get(e).getFeatures().size());

				showSelectedFeatures(selectedFeatures);
				if (!selectedFeatures.isEmpty()) {

					precisionByFeatures = new PrecisionByFeatures(relieffForCombinationOfFeatures.get(e).getGroupName());
					/**
					 * Seta os nódulos vizinhos para features selecionadas pelo
					 * algoritmo Relieff
					 **/
					for (EvaluatedNodules aleatoryEvaluatedNodule : aleatoryEvaluatedBenignNodules) {
						aleatoryEvaluatedNodule.setNearbyNodulesByFeatures(selectedFeatures, distanceType, qntRanking);

						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getAverageOfPrecisionByRanking());
						// System.out.println(aleatoryEvaluatedNodule.getPrecisions().get(e).getFeatureName());
						precisionByFeatures.addListOfPrecisionsByFeature(
								aleatoryEvaluatedNodule.getPrecisions().get(count).getAverageOfPrecisionByRanking());
					}
					precisionByFeatures.updatePrecision();
					precisionsByAllLeaveOneOut.add(precisionByFeatures);
					count++;
					System.out.println("Para atributos: " + precisionByFeatures.getFeatureName() + "\n"
							+ "Media para todos os leave one out: "
							+ precisionByFeatures.getAverageOfPrecisionByRanking() + "\n Media do array: "
							+ precisionByFeatures.getAveragePrecision() + " +- "
							+ precisionByFeatures.getStandartDeviation());

					System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
				}
			}
			StringBuilder nameOfFile = new StringBuilder();
			nameOfFile.append(qntOfRelieffFeatures);
			nameOfFile.append("_");
			nameOfFile.append("CombinationOfFeaturesFeatures");
			doPrecisionNChart(nameOfFile.toString(), precisionsByAllLeaveOneOut, "BENIGN");
			qntOfRelieffFeatures += 5;
		}
		
	}

	private static Set<Nodule> setAllNodulesByMalignance(Set<Nodule> allNodules, String malignance) {
		Set<Nodule> allNodulesByMalignance = new HashSet<>();
		for (Nodule nodule : allNodules) {
			if (malignance.equals(nodule.getMalignance())) {
				allNodulesByMalignance.add(nodule);
			}
		}
		return allNodulesByMalignance;
	}

	public static void doPrecisionNChart(String nameOfFile, List<PrecisionByFeatures> precisions, String malignance)
			throws IOException {
		XYLineChart precisionChart = new XYLineChart(nameOfFile, precisions, "Precision",
				"PRECISION FOR " + malignance + " NODULES");
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
			// 182,199,209
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

	public static void showSelectedFeatures(List<FeaturesEnum> features) {
		StringBuilder str = new StringBuilder();
		str.append("Features selecionadas: ");
		for (FeaturesEnum feature : features) {
			str.append(feature.getFeatureName());
			str.append(", ");
		}
		System.out.println(str);
	}

}