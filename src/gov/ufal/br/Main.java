package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String nameFile = "/home/tamirysp/Documentos/TCC/tcc/nodulesFeaturesDavid.csv";
		File csvFile = new File(nameFile);
		String csvSplitBy = ",";
		List<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		int qntRanking = 10;
		int qntAleatoryNodules = 20; //Cada 1/4 desses nódulos tera uma malignancia diferente
		
		Evaluator evaluator = new Evaluator(allNodules, qntRanking, qntAleatoryNodules);
		evaluator.setAleatoryBenignNodules();
		evaluator.setAleatoryMalignantNodules();
		
		System.out.println("Nódulos aleatórios foram escolhidos!");
		
		for(Nodule nodule : evaluator.getAleatoryBenignNodules()) {
			ListNearestNodules listNearestNodules = new ListNearestNodules(nodule, allNodules);
			listNearestNodules.setPrecision();
			listNearestNodules.setAveragePrecision();
			nodule.setNearbyNodules(listNearestNodules.getNearbyNodules());	
			System.out.println(nodule.showNearbys());
		}
		
		for(Nodule nodule : evaluator.getAleatoryMalignantNodules()) {
			ListNearestNodules listNearestNodules = new ListNearestNodules(nodule, allNodules);
			listNearestNodules.setPrecision();
			listNearestNodules.setAveragePrecision();
			nodule.setNearbyNodules(listNearestNodules.getNearbyNodules());
		}
	}

	public static List<Nodule> setAllNodules(File csvFile, String csvSplitBy) {
		List<Nodule> nodules = new ArrayList<>();
		Nodule nodule;
		Scanner scanner;
		
		try {
			scanner = new Scanner(csvFile);
			scanner.nextLine();
			while (scanner.hasNext()) {
				List<String> features = Arrays
						.asList(scanner.nextLine().split(csvSplitBy));
				nodule = new Nodule(features.get(features.size() - 1), // ID
						features.subList(0, features.size() - 3), // Features
						Integer.parseInt(features.get(features.size() - 2))); // Malignance
				nodules.add(nodule);
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nodules;
	}
}