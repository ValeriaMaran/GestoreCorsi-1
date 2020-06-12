package it.polito.tdp.corsi;


import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPeriodoDidattico;

    @FXML
    private TextField txtCodiceCorso;

    @FXML
    private Button btnCorsiPerPeriodo;

    @FXML
    private Button btnNumeroStudenti;

    @FXML
    private Button btnStudenti;

    @FXML
    private Button btnDivisioneStudenti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void btnStampaStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	List<Studente> studenti = new LinkedList<Studente>();
    	String codCorso = txtCodiceCorso.getText();
    	if(model.esisteCorso(codCorso)==true) {
    		studenti= this.model.getStudentiByCodiceCorso(codCorso);
    			for(Studente s : studenti){
    				txtRisultato.appendText(s+"\n");
    		}
    	}
    	
    }

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	txtRisultato.clear();
    	String pdString = txtPeriodoDidattico.getText();
    	Integer pd = null;
    	try {
    		pd = Integer.parseInt(pdString);
    	}
    	catch(NumberFormatException e){
    		txtRisultato.setText("Devi Inserire un numero tra 1 e 2");
    	}
    	if(pd.equals(1)|| pd.equals(2)) {
    		List<Corso> corsi = this.model.getCorsi(pd);
    		for(Corso c : corsi) {
    			txtRisultato.appendText(c.toString()+"\n");
    		}
    	}
    	
    }

    @FXML
    void stampaDivisioneStudenti(ActionEvent event) {

    }

    @FXML
    void stampaNumeroStudenti(ActionEvent event) {
    	txtRisultato.clear();
    	Map<Corso, Integer> statistiche = new HashMap<Corso,Integer>();
    	Integer pd = null;
    	try {
    		pd = Integer.parseInt(txtPeriodoDidattico.getText());
    		
    	}
    	catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi Inserire un numero pari a uno o 2\n");
    	}
    	if(pd.equals(1) || pd.equals(2)) {
    		statistiche = this.model.getIscrittiByPeriodo(pd);
    		for(Corso c : statistiche.keySet()) {
    			txtRisultato.appendText(c.getNome()+" "+statistiche.get(c)+"\n");
    		}
    	}
    	else {
    		txtRisultato.appendText("devi inserire 1 o 2");
    	}


    }

    @FXML
    void initialize() {
        assert txtPeriodoDidattico != null : "fx:id=\"txtPeriodoDidattico\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCodiceCorso != null : "fx:id=\"txtCodiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model = model;
    }
}
