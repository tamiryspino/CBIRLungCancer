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
		int qntAleatoryNodules = 20;
		
		Evaluator evaluator = new Evaluator(allNodules, qntRanking, qntAleatoryNodules);		

	}

	public static List<Nodule> setAllNodules(File csvFile, String csvSplitBy) {
		List<Nodule> nodules = new ArrayList<>();
		Nodule nodule;
		Scanner scanner;
		
		try {
			scanner = new Scanner(csvFile);
			while (scanner.hasNext()) {
				List<String> features = Arrays
						.asList(scanner.nextLine().split(csvSplitBy));
				nodule = new Nodule(features.get(features.size() - 1), // ID
						features.subList(0, features.size() - 3), // Features
						features.size() - 2); // Malignance
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