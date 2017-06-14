package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulator {
	
	private int anno;
	private DirectedGraph<Mostra,DefaultEdge> graph;
	
	private PriorityQueue<Event> queue;
	
	private Map<Integer,Set<ArtObject>> opereVisitate;

	public Simulator(DirectedGraph<Mostra, DefaultEdge> graph, Integer N, Integer anno) {
		super();
		this.graph = graph;
		this.anno=anno;
		queue=new PriorityQueue<Event>();
		opereVisitate=new HashMap<>();
		for(int i=1;i<=N;i++){
			Set<ArtObject> stemp=new HashSet<>();
			opereVisitate.put(i,stemp);
		}
	}
	
	public void run(){
		this.popolaCoda();
		while(!queue.isEmpty()){
			
			Event e = queue.poll();
			Mostra partenza=e.getMostra();
			
			opereVisitate.get(e.getStudente()).addAll(partenza.getOpere());
			
			if(graph.outgoingEdgesOf(partenza).size()!=0){
			Random r= new Random();
			int pos=r.nextInt(graph.outgoingEdgesOf(partenza).size());
			
			Mostra m=Graphs.successorListOf(graph,partenza).get(pos);
			
			Event e2= new Event(e.getStudente(),m, m.getBegin());
			queue.add(e2);
			}
			
		}
		
	}
	
	
	public void popolaCoda(){
		
		List<Mostra> ltemp=new ArrayList<Mostra>();
		
		for(Mostra mtemp:graph.vertexSet()){
			if(mtemp.getBegin()==anno)
				ltemp.add(mtemp);
		}
		
		Random r=new Random();
		int pos=r.nextInt(ltemp.size());
		Mostra partenza=ltemp.get(pos);
		
		for(int i:opereVisitate.keySet()){
			Event e=new Event(i,partenza,partenza.getBegin());
			queue.add(e);
		}
		
	}

	public List<Stat> getClassifica(){
		this.run();
		List<Stat> result=new ArrayList<>();
		
		for(int i: opereVisitate.keySet()){
			int opere=opereVisitate.get(i).size();
			result.add(new Stat(i,opere));
		}
		
		
		return result;
	}

	
	
	
}
