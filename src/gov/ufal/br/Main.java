package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

		EvaluatedNodules benignNodules = new EvaluatedNodules("BENIGN", allNodules, qntAleatoryNodules);
		EvaluatedNodules malignantNodules = new EvaluatedNodules("MALIGNANT", allNodules, qntAleatoryNodules);
		System.out.println(qntAleatoryNodules + " nódulos aleatórios foram escolhidos!");
		
		Distances distanceType = Distances.EUCLIDIAN;
		
		List<GroupFeaturesEnum> features = new ArrayList<>();
		features.add(GroupFeaturesEnum.ALL_FEATURES);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por todas as features para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por todas as features para cada nódulos aleatórios MALIGNOS");
		malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		features.clear();
		
		features.add(GroupFeaturesEnum.NODULE_TEXTURE);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por textura para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por textura para cada nódulos aleatórios MALIGNOS");
		malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		features.clear();
		features.add(GroupFeaturesEnum.NODULE_SHAPE);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma para cada nódulos aleatórios MALIGNOS");
		malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		features.clear();
		features.add(GroupFeaturesEnum.NODULE_SHAPE);
		features.add(GroupFeaturesEnum.NODULE_TEXTURE);
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma e textura para cada nódulos aleatórios BENIGNOS");
		benignNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		System.out.println("Calculando lista de nódulos vizinhos mais próximos por forma e textura para cada nódulos aleatórios MALIGNOS");
		malignantNodules.setNearbyNodulesByFeatures(features, distanceType, qntRanking);
		
		System.out.println("Vizinhança dos nódulos foi adicionada pela menor distância euclidiana.");
	}

	public static void doPrecisionNChart(List<PrecisionByFeatures> precisions, String malignance){
		XYLineChart_AWT precisionChart = new XYLineChart_AWT(precisions, "Precisão",
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