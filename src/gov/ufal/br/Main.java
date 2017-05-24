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
import java.util.List;
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
		List<SelectedFeaturesEnum> relieffForNodule = Arrays.asList(
				SelectedFeaturesEnum.RELIEFF_NODULE_SHAPE, 
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY, 
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY_SHAPE,
				SelectedFeaturesEnum.RELIEFF_NODULE_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE_SHAPE_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE_INTENSITY_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_NODULE,
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_EDGE_SHARPNESS,
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_PARENCHYMA,
				SelectedFeaturesEnum.RELIEFF_BY_ALL);

		/**Max 50 features **/
		List<SelectedFeaturesEnum> relieffForParenchyma = Arrays.asList(
				SelectedFeaturesEnum.RELIEFF_PARANCHYMA_INTENSITY, 
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA_TEXTURE,
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA,
				SelectedFeaturesEnum.RELIEFF_PARENCHYMA_WITH_EDGE_SHARPNESS,
				SelectedFeaturesEnum.RELIEFF_NODULE_WITH_PARENCHYMA,
				SelectedFeaturesEnum.RELIEFF_BY_ALL);

		Set<Nodule> allNodules = setAllNodules(csvFile, csvSplitBy);
		Set<Nodule> benignNodules = setAllNodulesByMalignance(allNodules, "BENIGN");
		Set<Nodule> malignantNodules = setAllNodulesByMalignance(allNodules, "MALIGNANT");
		System.out.println("Qnt nodulos benignos: " + benignNodules.size() + "\n" + "Qnt nodulos malignos: "
				+ malignantNodules.size());

		String resultsFile = "/home/tamirysp/Documentos/TCC/tcc/resultados_3_Tamirys.csv";
        FileWriter writer = new FileWriter(resultsFile);
        

		CSVUtils.writeLine(writer, Arrays.asList("NODULOS MALIGNOS"));
		
        List<String> titulos = new ArrayList<>();
        titulos.add("Qnt Features");
        for(SelectedFeaturesEnum relieff : relieffForNodule) {
        	titulos.add(relieff.getGroupName());
        }
        CSVUtils.writeLine(writer, titulos);
        titulos = new ArrayList<>();
        titulos.add("");
        for(SelectedFeaturesEnum relieff : relieffForNodule) {
        	titulos.add(relieff.getFeatures().size() + " Features");
        }
        CSVUtils.writeLine(writer, titulos);
		List<PrecisionByRanking> precisionsForNodule = evaluateNodules(malignantNodules, allNodules, relieffForNodule, distanceType, qntRanking, "BENIGN", writer);

		CSVUtils.writeLine(writer, Arrays.asList(""));
        
		titulos = new ArrayList<>();
		titulos.add("Qnt Features");
		for(SelectedFeaturesEnum relieff : relieffForParenchyma) {
        	titulos.add(relieff.getGroupName());
        }
        CSVUtils.writeLine(writer, titulos);
        titulos = new ArrayList<>();
        titulos.add("");
        for(SelectedFeaturesEnum relieff : relieffForParenchyma) {
        	titulos.add(relieff.getFeatures().size() + " Features");
        }
        CSVUtils.writeLine(writer, titulos);
        
		List<PrecisionByRanking> precisionsForParenchyma = evaluateNodules(malignantNodules, allNodules, relieffForParenchyma, distanceType, qntRanking, "BENIGN", writer);

		CSVUtils.writeLine(writer, Arrays.asList(""));
		CSVUtils.writeLine(writer, Arrays.asList(""));
		
		CSVUtils.writeLine(writer, Arrays.asList(""));
		
		CSVUtils.writeLine(writer, Arrays.asList("NODULOS BENIGNOS"));
		
		
        titulos = new ArrayList<>();
        titulos.add("Qnt Features");
        for(SelectedFeaturesEnum relieff : relieffForNodule) {
        	titulos.add(relieff.getGroupName());
        }
        CSVUtils.writeLine(writer, titulos);
        titulos = new ArrayList<>();
        titulos.add("");
        for(SelectedFeaturesEnum relieff : relieffForNodule) {
        	titulos.add(relieff.getFeatures().size() + " Features");
        }
        CSVUtils.writeLine(writer, titulos);
		List<PrecisionByRanking> precisionsForNoduleB = evaluateNodules(benignNodules, allNodules, relieffForNodule, distanceType, qntRanking, "BENIGN", writer);

		CSVUtils.writeLine(writer, Arrays.asList(""));
        
		titulos = new ArrayList<>();
		titulos.add("Qnt Features");
		for(SelectedFeaturesEnum relieff : relieffForParenchyma) {
        	titulos.add(relieff.getGroupName());
        }
        CSVUtils.writeLine(writer, titulos);
        titulos = new ArrayList<>();
        titulos.add("");
        for(SelectedFeaturesEnum relieff : relieffForParenchyma) {
        	titulos.add(relieff.getFeatures().size() + " Features");
        }
        CSVUtils.writeLine(writer, titulos);
        
		List<PrecisionByRanking> precisionsForParenchymaB = evaluateNodules(benignNodules, allNodules, relieffForParenchyma, distanceType, qntRanking, "BENIGN", writer);

		CSVUtils.writeLine(writer, Arrays.asList(""));
		
		
		writer.flush();
        writer.close();
        System.out.println("Ok! Finalizado.");
	}
	
	private static List<PrecisionByRanking> evaluateNodules(Set<Nodule> nodulesByMalignance, Set<Nodule> allNodules,
			List<SelectedFeaturesEnum> relieffFeatures, Distances distanceType, int qntRanking, String malignance, FileWriter writer) throws IOException {
		Integer qntOfSelectedFeatures = 5;
		List<List<Double>> precisionsOfNearestNodules;
		List<FeaturesEnum> selectedFeatures;
		List<PrecisionByRanking> allPrecisionsByRelieffFeatures = new ArrayList<>();
		
		List<String> meanAndStandartDeviation;
		String groupName = null;
		while (qntOfSelectedFeatures < 130) {
			allPrecisionsByRelieffFeatures = new ArrayList<>();
			meanAndStandartDeviation = new ArrayList<>();
			meanAndStandartDeviation.add(qntOfSelectedFeatures.toString());
			for(SelectedFeaturesEnum relieff : relieffFeatures) {
				groupName = relieff.getGroupName();
				precisionsOfNearestNodules = new ArrayList<>();
				selectedFeatures = relieff.getFeatures().subList(0, 
						relieff.getFeatures().size() > qntOfSelectedFeatures ? qntOfSelectedFeatures : relieff.getFeatures().size());
				//showSelectedFeatures(selectedFeatures);
				List<Nodule> nearbyNodules;
				for(Nodule nodule : nodulesByMalignance) {
					nearbyNodules = getNearbyNodules(nodule, allNodules, selectedFeatures, distanceType, qntRanking);
					nodule.setNearbyNodules(new HashSet<>(nearbyNodules));
					precisionsOfNearestNodules.add(getPrecisions(nodule, nearbyNodules));
				}
				meanAndStandartDeviation.add(doRankingPrecisionLeaveOneOut(qntOfSelectedFeatures, groupName, selectedFeatures, precisionsOfNearestNodules, allPrecisionsByRelieffFeatures, writer));
			}
			doPrecisionNChart("_"+qntOfSelectedFeatures+"_"+groupName+"_" + malignance, allPrecisionsByRelieffFeatures, malignance);
			CSVUtils.writeLine(writer, meanAndStandartDeviation);
			qntOfSelectedFeatures +=5;
		}
		return allPrecisionsByRelieffFeatures;
	}
	
	private static String doRankingPrecisionLeaveOneOut(int qntSelectedFeatures, String groupName, 
			List<FeaturesEnum> selectedFeatures,
			List<List<Double>> precisionsOfNearestNodules, 
			List<PrecisionByRanking> meanPrecisionsByFeatures, FileWriter writer) throws IOException {
				
		PrecisionByRanking precisionMeanForRankingByFeatures = new PrecisionByRanking(qntSelectedFeatures,
				groupName, selectedFeatures, getAllMeanPrecisionForLeaveOneOut(precisionsOfNearestNodules));
		precisionMeanForRankingByFeatures.showResults();
		meanPrecisionsByFeatures.add(precisionMeanForRankingByFeatures);

		return (precisionMeanForRankingByFeatures.getPrecisionMean() + " +- " + precisionMeanForRankingByFeatures.getStandartDeviation());
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

	public static List<Double> getPrecisions(Nodule nodule, List<Nodule> nearbyNodules) {
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

	private static List<Nodule> getNearbyNodules(Nodule primaryNodule, Set<Nodule> allNodules, List<FeaturesEnum> selectedFeatures,
			Distances distanceType, int qntRanking) {
		List<Nodule> nearestNodules = new ArrayList<>(allNodules);
		Double distance = 0.0;
		for (Nodule nodule : allNodules) {
			if (distanceType == Distances.EUCLIDIAN) {
				distance = Operations.euclidianDistance(primaryNodule, nodule, selectedFeatures);
			}
			// TODO else if (distanceFormula == Distances.)
			nodule.setDistance(distance);
		}
		Collections.sort(nearestNodules, Comparator.comparing(Nodule::getDistance));

		return nearestNodules.subList(0,
					nearestNodules.size() > qntRanking ? qntRanking : nearestNodules.size());
	}

	public static Set<Nodule> setAllNodulesByMalignance(Set<Nodule> allNodules, String malignance) {
		Set<Nodule> allNodulesByMalignance = new HashSet<>();
		for (Nodule nodule : allNodules) {
			if (malignance.equals(nodule.getMalignance())) {
				allNodulesByMalignance.add(nodule);
			}
		}
		return allNodulesByMalignance;
	}

	public static void doPrecisionNChart(String nameOfFile, List<PrecisionByRanking> precisions, String malignance)
			throws IOException {
		XYLineChart precisionChart = new XYLineChart(nameOfFile, precisions, "Precision",
				"PRECISION FOR " + malignance + " NODULES");
		precisionChart.pack();
		//RefineryUtilities.centerFrameOnScreen(precisionChart);
		//precisionChart.setVisible(true);
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