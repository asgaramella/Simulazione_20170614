package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

public class Mostra {
	
	private int exhibition_id;
	private String exhibition_department;
	private String exhibition_title;
	private int begin;
	private int end;
	private List<ArtObject> opere;
	
	public Mostra(int exhibition_id, String exhibition_department, String exhibition_title, int begin, int end) {
		super();
		this.exhibition_id = exhibition_id;
		this.exhibition_department = exhibition_department;
		this.exhibition_title = exhibition_title;
		this.begin = begin;
		this.end = end;
		opere=new ArrayList();
	}
	
	
	public int getExhibition_id() {
		return exhibition_id;
	}
	public void setExhibition_id(int exhibition_id) {
		this.exhibition_id = exhibition_id;
	}
	public String getExhibition_department() {
		return exhibition_department;
	}
	public void setExhibition_department(String exhibition_department) {
		this.exhibition_department = exhibition_department;
	}
	public String getExhibition_title() {
		return exhibition_title;
	}
	public void setExhibition_title(String exhibition_title) {
		this.exhibition_title = exhibition_title;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	


	public List<ArtObject> getOpere() {
		return opere;
	}


	public void setOpere(List<ArtObject> opere) {
		this.opere = opere;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exhibition_id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mostra other = (Mostra) obj;
		if (exhibition_id != other.exhibition_id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return exhibition_title;
	}
	
	

}
