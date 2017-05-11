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
		
		List<GroupFeaturesEnum> listOfFeatures = new ArrayList<>();
		listOfFeatures.add(GroupFeaturesEnum.ALL_FEATURES);
		setNearbyNodules(evaluator, listOfFeatures, Distances.EUCLIDIAN, aleatoryBenignNodules, allNodules, qntRanking, "Benignos");
		setNearbyNodules(evaluator, listOfFeatures, Distances.EUCLIDIAN, aleatoryMalignantNodules, allNodules, qntRanking, "Malignos");
		
		listOfFeatures.clear();
		listOfFeatures.add(GroupFeaturesEnum.NODULE_TEXTURE);
		listOfFeatures.add(GroupFeaturesEnum.NODULE_SHAPE);
		setNearbyNodules(evaluator, listOfFeatures, Distances.EUCLIDIAN, aleatoryBenignNodules, allNodules, qntRanking, "Benignos");
		setNearbyNodules(evaluator, listOfFeatures, Distances.EUCLIDIAN, aleatoryMalignantNodules, allNodules, qntRanking, "Malignos");
		
		
		
		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
	}
	
	private static void setNearbyNodules(Evaluator evaluator, List<GroupFeaturesEnum> listOfFeatures, Distances distanceFormula,
			Set<Nodule> aleatoryNodules, Set<Nodule> allNodules, int qntRanking, String malignance) {
		evaluator.setNearbyNodulesByFeatures(listOfFeatures, distanceFormula, aleatoryNodules, allNodules, qntRanking);		
		List<Double> averagePrecision = evaluator.setAveragePrecisionByNoduleRanking(aleatoryNodules);
		List<Double> averageRecall = evaluator.setAverageRecallForAllNodules(aleatoryNodules);
		Double precisionMeanForNodules = averagePrecision.stream().mapToDouble(a -> a).average().orElse(0);
		Double standardDeviationForNodules = Operations.standardDeviation(averagePrecision);
		System.err.println("Precisão média para Nódulos "+ malignance +" " + precisionMeanForNodules + " +- " + standardDeviationForNodules);
		
		doPrecisionNChart(averagePrecision, malignance);
		doPrecisionVsRecallChart(averagePrecision, averageRecall, malignance);
	}

	public static void doPrecisionNChart(List<Double> averagePrecision, String malignance){
		XYLineChart_AWT precisionChart = new XYLineChart_AWT(averagePrecision, "Precisão",
				"Precisão (" + averagePrecision.size() + ") para Nódulos " + malignance, malignance);
		precisionChart.pack();
		RefineryUtilities.centerFrameOnScreen(precisionChart);
		precisionChart.setVisible(true);
	}
	
	public static void doPrecisionVsRecallChart(List <Double> averagePrecision, List<Double> averageRecall, String malignance) {
		XYLineChart_AWT precisionVsRecallChart = new XYLineChart_AWT(averagePrecision, averageRecall, "Precisão vs Revocação",
				"Revocação (" + averageRecall.size() + ") para Nódulos " + malignance, malignance);
		precisionVsRecallChart.pack();
		RefineryUtilities.centerFrameOnScreen(precisionVsRecallChart);
		precisionVsRecallChart.setVisible(true);
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