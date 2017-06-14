package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private SimpleDirectedGraph<Mostra,DefaultEdge> graph;
	private Map<Integer,ArtObject> mapOpere;
	private Simulator sim;
	

	public Model() {
		super();
		dao=new ArtsmiaDAO();
		mapOpere=new HashMap<>();
	}



	public List<Integer> getAnniBegin() {
		return dao.getAnniBegin();
	}
	
	public boolean creaGrafo(Integer anno){
		graph=new SimpleDirectedGraph<Mostra,DefaultEdge>(DefaultEdge.class);
		
		List<Mostra> mostre= dao.getMostreAfterYear(anno);
		for(Mostra mtemp: mostre){
			mtemp.getOpere().addAll(dao.getOpereForMostra(mtemp,mapOpere));
		}
		
		Graphs.addAllVertices(graph,mostre);
		
		for(Mostra mtemp1:graph.vertexSet()){
			for(Mostra mtemp2:graph.vertexSet()){
				if(!mtemp1.equals(mtemp2)){
					if(mtemp1.getBegin()<mtemp2.getBegin() && mtemp2.getBegin()<mtemp1.getEnd()){
						graph.addEdge(mtemp1, mtemp2);
					}
				}
			}
			
		}
		
		
		ConnectivityInspector<Mostra,DefaultEdge> ci=new ConnectivityInspector<Mostra,DefaultEdge>(graph);
		
		return ci.isGraphConnected();
		
		
		
	}



	public Mostra trovaMostraMaggiore() {
		int max=Integer.MIN_VALUE;
		Mostra best=null;
		for(Mostra mtemp: graph.vertexSet()){
			if(mtemp.getOpere().size()>max){
				max=mtemp.getOpere().size();
				best=mtemp;
			}
				
		}
	return best;
		
	}



	public List<Stat> getClassifica(int anno,int N) {
		sim=new Simulator(graph,N,anno);
		
		return sim.getClassifica();
		
	}

}
