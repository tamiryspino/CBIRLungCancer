package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public LineNumberReader br = null;
	public LineNumberReader ln = null;
	public List<Nodule> aleatoryMaligneNodulesFeatures;
	public List<Nodule> aleatoryBenigneNodulesFeatures;
	public String csvFile = "/home/tamirysp/Documentos/TCC/tcc/nodulesFeaturesDavid.csv";
	public String csvSplitBy = ",";

	public List<Nodule> getAleatoryNodules(int qnt, int malignanceProbability) {
		List<Nodule> nodules = new ArrayList<>();
		String n = "";
		List<String> features = new ArrayList<>();
		Nodule nodule;
		int noduleMalignance = 0;
		try {
			for (int i = 0; i < qnt; i++) {
				while (malignanceProbability != noduleMalignance) {
					n = choose(new File(csvFile));
					features = Arrays.asList(n.split(csvSplitBy));
					noduleMalignance = features.size() - 2;
				}
				nodule = new Nodule(features.get(features.size() - 1), features.subList(0, features.size() - 3),
						noduleMalignance);
				nodules.add(nodule);
				noduleMalignance = 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return nodules;
	}

	public static String choose(File f) throws FileNotFoundException {
		String result = null;
		Random rand = new Random();
		int n = 0;
		for (Scanner sc = new Scanner(f); sc.hasNext();) {
			++n;
			String line = sc.nextLine();
			if (rand.nextInt(n) == 0)
				result = line;
		}

		return result;
	}
}
