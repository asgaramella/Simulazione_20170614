package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getAnniBegin() {
		
		String sql = "SELECT DISTINCT exhibitions.`begin` "+
				"FROM exhibitions "+
				"ORDER BY exhibitions.`begin`";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getInt("begin"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<Mostra> getMostreAfterYear(Integer anno) {
		String sql = "SELECT exhibitions.* "+
					"FROM exhibitions "+
					"where exhibitions.`begin`>=?";

		List<Mostra> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new Mostra(
						res.getInt("exhibition_id"),
						res.getString("exhibition_department"),
						res.getString("exhibition_title"),
						res.getInt("begin"),
						res.getInt("end"))
						);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public List<ArtObject> getOpereForMostra(Mostra mtemp, Map<Integer, ArtObject> mapOpere) {
		String sql = "SELECT objects.* "+
					"FROM exhibition_objects,objects "+
					"WHERE exhibition_objects.exhibition_id=? "+
					"AND exhibition_objects.object_id=objects.object_id";

	List<ArtObject> result = new ArrayList<>();

	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, mtemp.getExhibition_id());

		ResultSet res = st.executeQuery();

		while (res.next()) {
			
			ArtObject obj=mapOpere.get(res.getInt("object_id"));
			if(obj==null){
			
					
				obj	=new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
							res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
							res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
							res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
			mapOpere.put(obj.getObjectId(), obj);		
			
			}
		
			result.add(obj);
		}

		conn.close();
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
		
	}
	
}