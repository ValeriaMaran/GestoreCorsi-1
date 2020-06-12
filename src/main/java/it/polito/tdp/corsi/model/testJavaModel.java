package it.polito.tdp.corsi.model;

public class testJavaModel {
	public static void main(String[] args) {
		Model model = new Model();
		System.out.print(model.getCorsi(1)+"\n");
		System.out.print(model.getCorsi(2)+"\n");
		System.out.print(model.getIscrittiByPeriodo(1)+"\n");
		//Corso c = new Corso("01KSUPG",8,"Gestione dell'innovazione e sviluppo prodotto",2);
		System.out.print(model.getStudentiByCodiceCorso("01KSUPG"+"\n"));
		if(model.esisteCorso("xbuda")==true){
			System.out.println("Il corso esiste");
		}
		else 
			System.out.println("Il corso non esiste");
	}
}
