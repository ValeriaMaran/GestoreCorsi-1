package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.db.CorsoDAO;

public class Model {
	public List <Corso> getCorsi(Integer pd){
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsyByPeriodo(pd);
	}
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd){
		CorsoDAO dao= new CorsoDAO();
		return dao.getIscrittiCorsoByPeriodoDidattico(pd);
	}
	public List<Studente> getStudentiByCodiceCorso(String codins){
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudenteByCorso(codins);
		
	}
	public boolean esisteCorso(String codins) {
		CorsoDAO dao = new CorsoDAO();
		return dao.esisteCorso(codins);
	}
}
