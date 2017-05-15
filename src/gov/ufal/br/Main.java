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
		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallSolidNodulesFeatures_Tamirys.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ";";
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
		
		/*********************************** FEATURES SELECIONADAS ******************************************/
		//TODO Verificar se esse valor corresponde ao csv, deve ser +1
		/** Lista de atributos selecionados em todos os atributos disponíveis **/
/*		List<Integer> selectedFeaturesOfAllFeatures = 
				new ArrayList<>(23, 19, 4, 11, 17, 110, 101, 10, 8, 92, 120, 67, 20, 7, 118, 2, 12, 117, 116, 68);
		
		List<Integer> selectedFeaturesOfNoduleFeaturesWithEdgeSharpness = 
				new ArrayList<>(23,4,19,11,10,60,7,68,8,17,20,67,66,12,2,14,70,15,65,9,53);
		List<Integer> selectedFeaturesOfParenchymaFeaturesWithEdgeSharpness = 
				new ArrayList<>(61,42,51,15,33,59,24,58,57,60,8,5,50,7,44,32,17,9,23,41);
*/
		List<Integer> selectedFeaturesOfNoduleFeatures;
		List<Integer> selectedFeaturesOfParenchymaFeatures;
		
		
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