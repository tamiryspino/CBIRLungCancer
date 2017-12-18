package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {


	static Integer qntRanking = 10;
	static String nameFile = "/home/tamirysp/Documentos/TCC/tcc/smallNodulesFeatures_novo_standadize_all.csv";
	
	public static void main(String[] args) throws IOException {

		File csvFile = new File(nameFile);
		String csvSplitBy = ",";
		Distances distanceType = Distances.EUCLIDIAN;

		/** Max 59 features **/
		List<SelectedFeaturesEnum> relieff_f = Arrays.asList(
				SelectedFeaturesEnum.RELIEFF_NODULE_SHAPE, 
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY, 
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY_SHAPE,
				SelectedFeaturesEnum.RELIEFF_NODULE_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE_SHAPE_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE,
				SelectedFeaturesEnum.RELIEFF_PARANCHYMA_INTENSITY, 
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA,
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_PARENCHYMA,
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_EDGE_SHARPNESS,
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA_WITH_EDGE_SHARPNESS,
				SelectedFeaturesEnum.RELIEFF_EDGE_SHARPNESS,
				SelectedFeaturesEnum.RELIEFF_BY_ALL);
		
		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		Set<Nodule> benignNodules = setAllNodulesByMalignance(allNodules, "BENIGN");
		Set<Nodule> malignantNodules = setAllNodulesByMalignance(allNodules, "MALIGNANT");
		System.out.println("Qnt nodulos benignos: " + benignNodules.size() + "\n" + "Qnt nodulos malignos: "
				+ malignantNodules.size());

		String resultsFile = "/home/tamirysp/Documentos/TCC/tcc/novo/novosResultados_new_all_withEdge_Tamirys.csv";
		FileWriter writer = new FileWriter(resultsFile);
		
		Integer qntSelectedFeatures = 1;
		Double maxTenM=0.0;
		Double maxTenB = 0.0;
		while(qntSelectedFeatures < 130) {
			CSVUtils.writeLine(writer, Arrays.asList("NODULOS MALIGNOS - "+ qntSelectedFeatures + "Features selecionadas"));
			
			List<String> titulos = new ArrayList<>();
			titulos.add("Qnt Nodulos");
			for(SelectedFeaturesEnum relieff : relieff_f) {
				titulos.add(relieff.getGroupName());
			}
			CSVUtils.writeLine(writer, titulos);
			titulos = new ArrayList<>();
			titulos.add("");
			for(SelectedFeaturesEnum relieff : relieff_f) {
				titulos.add(relieff.getFeatures().size() + " Features");
			}
			CSVUtils.writeLine(writer, titulos);
			for(int qnt = 1; qnt<=qntRanking; qnt++) {		        
				maxTenM = evaluateNodules(malignantNodules, allNodules, relieff_f, distanceType, qnt, "MALIGNANT", writer, qntSelectedFeatures,maxTenM);
			}
			CSVUtils.writeLine(writer, Arrays.asList(""));
			CSVUtils.writeLine(writer, Arrays.asList(""));
	
			CSVUtils.writeLine(writer, Arrays.asList("NODULOS BENIGNOS- "+ qntSelectedFeatures + "Features selecionadas"));
	
			titulos = new ArrayList<>();
			titulos.add("Qnt Nodulos");
			for (SelectedFeaturesEnum relieff : relieff_f) {
				titulos.add(relieff.getGroupName());
			}
			CSVUtils.writeLine(writer, titulos);
			titulos = new ArrayList<>();
			titulos.add("");
			for (SelectedFeaturesEnum relieff : relieff_f) {
				titulos.add(relieff.getFeatures().size() + " Features");
			}
			CSVUtils.writeLine(writer, titulos);
			for (int qnt = 1; qnt <= qntRanking; qnt++) {
			    maxTenB = evaluateNodules(benignNodules, allNodules, relieff_f, distanceType, qnt, "BENIGN", writer, qntSelectedFeatures, maxTenB);	        
			}
		    CSVUtils.writeLine(writer, Arrays.asList(""));
			writer.flush();
			if (qntSelectedFeatures < 20) {
				qntSelectedFeatures += 1;
			} else {
				qntSelectedFeatures +=5;
			}
		}
		writer.close();
	    System.out.println("Ok! Finalizado.");
	}
	
	private static Double evaluateNodules(Set<Nodule> nodulesByMalignance, Set<Nodule> allNodules,
			List<SelectedFeaturesEnum> relieffFeatures, Distances distanceType,Integer qntRanking, String malignance, FileWriter writer,
			Integer qntOfSelectedFeatures, Double maxTen) throws IOException {
		
		List<List<Double>> precisionsOfNearestNodules;
		List<FeaturesEnum> selectedFeatures;
		List<PrecisionByRanking> allPrecisionsByRelieffFeatures;
		
		List<String> meanAndStandartDeviation;
		String groupName = null;
		allPrecisionsByRelieffFeatures = new ArrayList<>();
		meanAndStandartDeviation = new ArrayList<>();
		meanAndStandartDeviation.add(qntRanking.toString());

		Double maxValue = 0.0;
		String precision;
		for(SelectedFeaturesEnum relieff : relieffFeatures) {
			precision = " - ";
			if(relieff.getFeatures().size() > qntOfSelectedFeatures-1) {
				groupName = relieff.getGroupName();
				precisionsOfNearestNodules = new ArrayList<>();
				selectedFeatures = relieff.getFeatures().subList(0, qntOfSelectedFeatures);
	
				Set<Nodule> nearbyNodules;
				for(Nodule nodule : nodulesByMalignance) {
					nearbyNodules = getNearbyNodules(nodule, allNodules, selectedFeatures, distanceType, qntRanking);
					nodule.setNearbyNodules(nearbyNodules);
					precisionsOfNearestNodules.add(getPrecisions(nodule, nearbyNodules));
				}
				precision = doRankingPrecisionLeaveOneOut(qntOfSelectedFeatures, groupName, selectedFeatures, precisionsOfNearestNodules, allPrecisionsByRelieffFeatures);
				String[] p = precision.split(" ");
				Double prec = Double.parseDouble(p[0]) + Double.parseDouble(p[2]);
			    if(prec >= maxValue) {
					maxValue = prec;
					precision = "*** " + precision + " ***";
				}
			    if((qntRanking == 10) && (maxTen<=prec)){
			    	precision = "++++ " + precision + " ++++";
			    	maxTen=prec;
			    }
			} 
			meanAndStandartDeviation.add(precision);
		}

		doPrecisionNChart("_"+qntOfSelectedFeatures+"_"+groupName+"_" + malignance, allPrecisionsByRelieffFeatures, malignance);
		CSVUtils.writeLine(writer, meanAndStandartDeviation);
		return maxTen;
	}
	
	private static String doRankingPrecisionLeaveOneOut(int qntSelectedFeatures, String groupName, 
			List<FeaturesEnum> selectedFeatures,
			List<List<Double>> precisionsOfNearestNodules, 
			List<PrecisionByRanking> meanPrecisionsByFeatures) throws IOException {
				
		Double precisionMean;
		Double stdDeviation;
		PrecisionByRanking precisionMeanForRankingByFeatures = new PrecisionByRanking(qntSelectedFeatures,
				groupName, selectedFeatures, getAllMeanPrecisionForLeaveOneOut(precisionsOfNearestNodules));
		precisionMeanForRankingByFeatures.showResults();
		meanPrecisionsByFeatures.add(precisionMeanForRankingByFeatures);
		
		precisionMean = precisionMeanForRankingByFeatures.getPrecisionMean();
		stdDeviation = precisionMeanForRankingByFeatures.getStandartDeviation();
		
		return precisionMean + " +- " + stdDeviation;
	}
	
	private static List<List<Double>> getAllMeanPrecisionForLeaveOneOut(List<List<Double>> precisionsOfNearestNodules) {
		int qntRanking = precisionsOfNearestNodules.get(0).size();
		List<Double> allPrecisionByNoduleRanking = new ArrayList<>();
		List<Double> precisionByNoduleRanking;
		List<List<Double>> precisionsOfNearestNodules_leaveOneOut;
		List<List<Double>> allMeanPrecisionOfNearestNodules_leaveOneOut = new ArrayList<>();
		for (int i=0; i< precisionsOfNearestNodules.size(); i++) {
			precisionsOfNearestNodules_leaveOneOut = new ArrayList<>();
			precisionsOfNearestNodules_leaveOneOut.addAll(precisionsOfNearestNodules);
			precisionsOfNearestNodules_leaveOneOut.remove(i);
			allPrecisionByNoduleRanking = new ArrayList<>();
			for(int j = 0; j < qntRanking; j++) {
				precisionByNoduleRanking = new ArrayList<>();
				for(int k=0; k< precisionsOfNearestNodules_leaveOneOut.size(); k++){
					List<Double> precision = precisionsOfNearestNodules_leaveOneOut.get(k);
					precisionByNoduleRanking.add(precision.get(j));
					//System.out.println("Precisoes do nodulo " + k + " lugar " + j + ": " + precision.get(j));
				}
				//System.out.println("Ok, proximo lugar no ranking...");
				//System.out.println(precisionByNoduleRanking);
				allPrecisionByNoduleRanking.add(precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
				//System.out.println("Media do lugar: "+ precisionByNoduleRanking.stream().mapToDouble(a -> a).average().orElse(0));
			}
			allMeanPrecisionOfNearestNodules_leaveOneOut.add(allPrecisionByNoduleRanking);
			//System.out.println("Media geral por ranking para leave one out: " + allPrecisionByNoduleRanking);
		}
		return allMeanPrecisionOfNearestNodules_leaveOneOut;
	}

	public static boolean isMalignant(String malignance) {
		return "MALIGNANT".equals(malignance) ? true : false;
	}

	public static List<Double> getPrecisions(Nodule nodule, Set<Nodule> nearbyNodules) {
		Double sumMalignances = 0.0;
		List<Double> partialPrecision = new ArrayList<>();
		int noduleQnt = 0;
		boolean primaryNoduleMalignance = isMalignant(nodule.getMalignance());
		boolean nearbyNoduleMalignance;
		for (Nodule nearby : nearbyNodules) {
			noduleQnt++;
			nearbyNoduleMalignance = isMalignant(nearby.getMalignance());
			if (primaryNoduleMalignance == nearbyNoduleMalignance) {
				sumMalignances++;
			}
			partialPrecision.add(sumMalignances / noduleQnt);
		}
		return partialPrecision;
	}

	private static Set<Nodule> getNearbyNodules(Nodule primaryNodule, Set<Nodule> allNodules, List<FeaturesEnum> selectedFeatures,
			Distances distanceType, int qntRanking) {
		List<Nodule> nearestNodules = new ArrayList<>(allNodules);
		Double distance = 0.0;
		for (Nodule nodule : allNodules) {
			//if (distanceType == Distances.EUCLIDIAN) {
				distance = Operations.euclidianDistance(primaryNodule, nodule, selectedFeatures);
			//}
			// TODO else if (distanceFormula == Distances.)
			nodule.setDistance(distance);
		}
		Collections.sort(nearestNodules, Comparator.comparing(Nodule::getDistance));

		return new LinkedHashSet<>(nearestNodules.subList(0, nearestNodules.size() > qntRanking ? qntRanking : nearestNodules.size()));
	}

	public static Set<Nodule> setAllNodulesByMalignance(Set<Nodule> allNodules, String malignance) {
		Set<Nodule> allNodulesByMalignance = new HashSet<>();
		for (Nodule nodule : allNodules) {
			if (malignance.equals(nodule.getMalignance())) {
				allNodulesByMalignance.add(nodule);
			}
		}
		System.out.println(allNodulesByMalignance);
		return allNodulesByMalignance;
	}

	public static void doPrecisionNChart(String nameOfFile, List<PrecisionByRanking> precisions, String malignance)
			throws IOException {
		XYLineChart precisionChart = new XYLineChart(nameOfFile, precisions, "Precision",
				"PRECISION FOR " + malignance + " NODULES");
		precisionChart.pack();
	}

	public static Set<Nodule> setAllNodules(File csvFile, String csvSplitBy) {
		Set<Nodule> nodules = new HashSet<>();
		Nodule nodule;
		Scanner scanner;

		try {
			scanner = new Scanner(csvFile);
			scanner.nextLine(); // Pula o cabeçalho do arquivo
			while (scanner.hasNext()) {
				List<String> allData = Arrays.asList(scanner.nextLine().split(csvSplitBy));
				List<Double> features = allData.subList(0, allData.size() - 4).stream().map(Double::parseDouble).collect(Collectors.toList());
				
				nodule = new Nodule(allData.get(allData.size() - 2), // ID
						features, // Features
						allData.get(allData.size() - 1)); // Malignance
				nodules.add(nodule);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("O arquivo não foi encontrado. " + e.getMessage());
		}
		System.out.println(nodules.size());
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