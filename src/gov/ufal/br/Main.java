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

		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/nodulesFeaturesDavid.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ",";
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
		evaluator.setNearbyNodulesByAllFeatures(aleatoryMalignantNodules, allNodules, qntRanking);
		List<Double> averagePrecisionForMalignantNodules = evaluator
				.setAveragePrecisionForAllNodules(aleatoryMalignantNodules);
		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");


		System.out.println("Lista da média das precisões para nódulos benignos: " + averagePrecisionForBenignNodules);
		XYLineChart_AWT benignChart = new XYLineChart_AWT(averagePrecisionForBenignNodules, "Precisão",
				"Precisão (" + averagePrecisionForBenignNodules.size() + ") para Nódulos Benignos");
		benignChart.pack();
		RefineryUtilities.centerFrameOnScreen(benignChart);
		benignChart.setVisible(true);

		System.out
				.println("Lista da média das precisões para nódulos malignos: " + averagePrecisionForMalignantNodules);

		XYLineChart_AWT malignantChart = new XYLineChart_AWT(averagePrecisionForMalignantNodules, "Precisão",
				"Precisão (" + averagePrecisionForMalignantNodules.size() + ") para Nódulos Malignos");
		malignantChart.pack();
		RefineryUtilities.centerFrameOnScreen(malignantChart);
		malignantChart.setVisible(true);
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
				nodule = new Nodule(features.get(features.size() - 1), // ID
						features.subList(0, features.size() - 3), // Features
						Integer.parseInt(features.get(features.size() - 2))); // Malignance
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