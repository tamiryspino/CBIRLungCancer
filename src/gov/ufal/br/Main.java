package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.jfree.ui.RefineryUtilities;

public class Main {

	public static void main(String[] args) {

		//String nameFile = "/home/tamirysp/Documentos/TCC/tcc/nodulesFeaturesDavid.csv";
		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallSolidNodulesFeatures.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ";";
		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		int qntRanking = 10;
		int qntAleatoryNodules = 20; // Cada 1/4 desses nódulos tera uma
										// malignancia diferente

		Evaluator evaluator = new Evaluator(allNodules, qntAleatoryNodules);
		Set<Nodule> aleatoryBenignNodules = evaluator.getAleatoryBenignNodules();
		Set<Nodule> aleatoryMalignantNodules = evaluator.getAleatoryMalignantNodules();
		System.out.println(qntAleatoryNodules + " nódulos aleatórios foram escolhidos!");

		evaluator.setNearbyNodulesByAllFeatures(aleatoryBenignNodules, allNodules, qntRanking);		
		List<Double> averagePrecisionForBenignNodules = evaluator
				.setAveragePrecisionForAllNodules(aleatoryBenignNodules);
		List<Double> averageRecallForBenignNodules = evaluator.setAverageRecallForAllNodules(aleatoryBenignNodules);
		//System.out.println("Precisao: " + averagePrecisionForBenignNodules.size() + " Recall: " + averageRecallForBenignNodules.size());

		evaluator.setNearbyNodulesByAllFeatures(aleatoryMalignantNodules, allNodules, qntRanking);
		List<Double> averagePrecisionForMalignantNodules = evaluator
				.setAveragePrecisionForAllNodules(aleatoryMalignantNodules);
		List<Double> averageRecallForMalignantNodules = evaluator
				.setAverageRecallForAllNodules(aleatoryMalignantNodules);

		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");

		System.out.println("Lista da média das precisões para nódulos benignos: " + averagePrecisionForBenignNodules);
		XYLineChart_AWT benignPrecisionChart = new XYLineChart_AWT(averagePrecisionForBenignNodules, "Precisão",
				"Precisão (" + averagePrecisionForBenignNodules.size() + ") para Nódulos Benignos");
		benignPrecisionChart.pack();
		RefineryUtilities.centerFrameOnScreen(benignPrecisionChart);
		benignPrecisionChart.setVisible(true);

		System.out
		.println("Lista da média das precisões para nódulos malignos: " + averagePrecisionForMalignantNodules);		
		XYLineChart_AWT malignantPrecisionChart = new XYLineChart_AWT(averagePrecisionForMalignantNodules, "Precisão",
				"Precisão (" + averagePrecisionForMalignantNodules.size() + ") para Nódulos Malignos");
		malignantPrecisionChart.pack();
		RefineryUtilities.centerFrameOnScreen(malignantPrecisionChart);
		malignantPrecisionChart.setVisible(true);
		
		System.out.println("Lista da média das revocações para nódulos benignos: " + averageRecallForBenignNodules);
		XYLineChart_AWT benignPrecisionVsRecallChart = new XYLineChart_AWT(averagePrecisionForBenignNodules, averageRecallForBenignNodules, "Revocação",
				"Revocação (" + averageRecallForBenignNodules.size() + ") para Nódulos Benignos");
		benignPrecisionVsRecallChart.pack();
		RefineryUtilities.centerFrameOnScreen(benignPrecisionVsRecallChart);
		benignPrecisionVsRecallChart.setVisible(true);

		System.out
				.println("Lista da média das revocações para nódulos malignos: " + averageRecallForMalignantNodules);

		XYLineChart_AWT malignantRecallChart = new XYLineChart_AWT(averagePrecisionForBenignNodules, averageRecallForMalignantNodules, "Revocação",
				"Revocação (" + averageRecallForMalignantNodules.size() + ") para Nódulos Malignos");
		malignantRecallChart.pack();
		RefineryUtilities.centerFrameOnScreen(malignantRecallChart);
		malignantRecallChart.setVisible(true);
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
						Integer.parseInt((features.get(features.size() - 3)).replace(".0", ""))); // Malignance
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