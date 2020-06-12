package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {
	public List<Corso> getCorsyByPeriodo(Integer pd){
		String sql ="SELECT * FROM corso WHERE pd= ?";
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();//contiene più di una riga 
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			conn.close();
		}
		
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
		
	}
	public Map<Corso, Integer> getIscrittiCorsoByPeriodoDidattico(int pd){
		Map<Corso, Integer> totaleIscrittiCorso = new HashMap<Corso,Integer>();
		
		String sql="SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot FROM corso as c, iscrizione i	WHERE c.codins = i.codins AND pd=? GROUP BY c.codins,c.nome,c.crediti,c.pd";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Integer n = rs.getInt("tot");
				totaleIscrittiCorso.put(c, n);
				
			}
			conn.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		return totaleIscrittiCorso;
	}
	public List<Studente> getStudenteByCorso(String codins){
		List<Studente> studenti= new LinkedList<Studente>();
		
		String sql = "SELECT s.matricola,s.nome,s.cognome,s.CDS FROM studente AS s, iscrizione AS i	WHERE s.matricola=i.matricola AND i.codins=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,codins);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				studenti.add(s);
			}
			conn.close();
					
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return studenti;
	}
	public boolean esisteCorso(String codins) {
		String sql = "SELECT * FROM corso C WHERE c.codins=?";
		Corso c = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,codins);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				conn.close();
				return true;
			}
			else return false;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
			
		}
		
	}

}
