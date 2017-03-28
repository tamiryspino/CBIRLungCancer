package gov.ufal.br;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Evaluator {

	public List<Nodule> aleatoryMalignantNodules;
	public List<Nodule> aleatoryBenignNodules;
	public List<Nodule> allNodules; // Esses não possuem vizinhos
	public int qntEvaluatedNodulesRanking; // TODO Setar para 10
	public int qntEvaluatedNodules; // Deve ser um valor par //TODO Setar para 10
	
	public Evaluator(List<Nodule> allNodules, int qntEvaluatedNodulesRanking,
			int qntEvaluatedNodules) {
		super();
		this.allNodules = allNodules;
		this.qntEvaluatedNodulesRanking = qntEvaluatedNodulesRanking;
		this.qntEvaluatedNodules = qntEvaluatedNodules;
	}
	
	public List<Nodule> getAleatoryMalignantNodules() {
		return aleatoryMalignantNodules;
	}

	public void setAleatoryMalignantNodules(List<Nodule> aleatoryMalignantNodules) {
		this.aleatoryMalignantNodules = aleatoryMalignantNodules;
	}

	public List<Nodule> getAleatoryBenignNodules() {
		return aleatoryBenignNodules;
	}

	public void setAleatoryBenignNodules(List<Nodule> aleatoryBenignNodules) {
		this.aleatoryBenignNodules = aleatoryBenignNodules;
	}

	public int getQntEvaluatedNodulesRanking() {
		return qntEvaluatedNodulesRanking;
	}

	public void setQntEvaluatedNodulesRanking(int qntEvaluatedNodulesRanking) {
		this.qntEvaluatedNodulesRanking = qntEvaluatedNodulesRanking;
	}

	public int getQntEvaluatedNodules() {
		return qntEvaluatedNodules;
	}

	public void setQntEvaluatedNodules(int qntEvaluatedNodules) {
		this.qntEvaluatedNodules = qntEvaluatedNodules;
	}

	public void setAleatoryMalignantNodules() {
		this.aleatoryMalignantNodules = setAleatoryNodules(5);
		this.aleatoryMalignantNodules.addAll(setAleatoryNodules(4));
	}

	public void setAleatoryBenignNodules() {
		this.aleatoryBenignNodules = setAleatoryNodules(1);
		this.aleatoryBenignNodules.addAll(setAleatoryNodules(2));
	}

	public List<Nodule> setAleatoryNodules(int malignanceProbability) {

		Random rnd;
		List<Nodule> nodules = new ArrayList<>();
		Nodule nodule = null;
		int n;
		int noduleMalignance = 0;
		int qnt = qntEvaluatedNodules / 4;
		for (int i = 0; i < qnt; i++) {
			while (malignanceProbability != noduleMalignance) {
				rnd = new Random();
				n = rnd.nextInt(this.allNodules.size());
				nodule = (Nodule) allNodules.toArray()[n];
				noduleMalignance = nodule.getMalignance();
			}
			nodules.add(nodule);
		}

		return nodules;
	}

	public List<Nodule> getAllNodules() {
		return allNodules;
	}

	public void setAllNodules(List<Nodule> allNodules) {
		this.allNodules = allNodules;
	}

}
