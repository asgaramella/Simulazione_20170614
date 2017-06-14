package it.polito.tdp.artsmia.model;

public class Stat implements Comparable<Stat> {
	private int studente;
	private int nrOpere;
	
	
	public Stat(int studente, int nrOpere) {
		super();
		this.studente = studente;
		this.nrOpere = nrOpere;
	}
	
	
	public int getStudente() {
		return studente;
	}
	public int getNrOpere() {
		return nrOpere;
	}
	public void setNrOpere(int nrOpere) {
		this.nrOpere = nrOpere;
	}
	public void setStudente(int studente) {
		this.studente = studente;
	}


	@Override
	public int compareTo(Stat other) {
	
		return this.nrOpere-other.getNrOpere();
	}
	
	

	
	
	
	
	
	

}
