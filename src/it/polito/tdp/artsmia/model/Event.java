package it.polito.tdp.artsmia.model;

public class Event implements Comparable<Event>{
	private int studente;
	private Mostra mostra;
	private int time;
	
	
	public Event(int studente, Mostra mostra, int time) {
		super();
		this.studente = studente;
		this.mostra = mostra;
		this.time = time;
	}


	public int getStudente() {
		return studente;
	}


	public void setStudente(int studente) {
		this.studente = studente;
	}


	public Mostra getMostra() {
		return mostra;
	}


	public void setMostra(Mostra mostra) {
		this.mostra = mostra;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	@Override
	public int compareTo(Event other) {
		
		return this.time-other.time;
	}
	
	
	
	
	

}
