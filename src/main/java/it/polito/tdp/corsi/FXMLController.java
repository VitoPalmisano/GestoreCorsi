package it.polito.tdp.corsi;

import java.util.List;
import java.util.Map;
import java.net.URL;
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
    private TextField txtPeriodo;

    @FXML
    private TextField txtCorso;

    @FXML
    private Button btnCorsiPerPeriodo;

    @FXML
    private Button btnStudenti;

    @FXML
    private Button btnNumeroStudenti;

    @FXML
    private Button btnDivisioneStudenti;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String pdString = txtPeriodo.getText();
    	Integer pd;
    	
    	try {
    		pd = Integer.parseInt(pdString);
    	}
    	catch (NumberFormatException nfe){
    		txtRisultato.setText("Devi inserire un numero (1 o 2)!");
    		return;
    	}
    	
    	if(!pd.equals(1) && !pd.equals(2)) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2)!");
    		return;
    	}
    	
    	// L'input e' corretto
    	List<Corso> corsi = this.model.getCorsiByPeriodo(pd);
    	
    	for(Corso c : corsi) {
    		txtRisultato.appendText(c.toString()+"\n");
    	}
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String pdString = txtPeriodo.getText();
    	Integer pd;
    	
    	try {
    		pd = Integer.parseInt(pdString);
    	}
    	catch (NumberFormatException nfe){
    		txtRisultato.setText("Devi inserire un numero (1 o 2)!");
    		return;
    	}
    	
    	if(!pd.equals(1) && !pd.equals(2)) {
    		txtRisultato.setText("Devi inserire un numero (1 o 2)!");
    		return;
    	}
    	
    	// L'input e' corretto
    	Map<Corso, Integer> statistiche = this.model.getIscrittiByPeriodo(pd);
    	
    	for(Corso c : statistiche.keySet()) {
    		txtRisultato.appendText(c.getNome()+" "+statistiche.get(c)+"\n");
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	// Tipo di output che mi aspetto, dato un codins ottengo una divisione degli studenti in base al loro CDS
    	// Codice corso Informatica 12
    	// Codice corso Gestionale 25
    	
    	txtRisultato.clear();    	
    	String codins = txtCorso.getText();
    	
    	if(!this.model.esisteCorso(codins)) {
    		txtRisultato.appendText("Il corso non esiste");
    		return;
    	}
    	
    	Map<String, Integer> statistiche = this.model.getDivisioneCDS(new Corso(codins, null, null, null));
    	
    	for(String cds : statistiche.keySet()) {
    		txtRisultato.appendText(cds+" "+statistiche.get(cds)+"\n");
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	String codins = txtCorso.getText();
    	
    	// Controllo se il codice corrisonde ad un corso esistente
    	if(!this.model.esisteCorso(codins)) {
    		txtRisultato.appendText("Il corso non esiste");
    		return;
    	}
    	
    	List<Studente> studenti = this.model.getStudentiByCorso(new Corso(codins, null, null, null));
    	
    	if(studenti.size()==0) {
    		txtRisultato.appendText("Il corso non ha studenti iscritti");
    		return;    		
    	}
    	
    	for(Studente s : studenti) {
    		txtRisultato.appendText(s+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
