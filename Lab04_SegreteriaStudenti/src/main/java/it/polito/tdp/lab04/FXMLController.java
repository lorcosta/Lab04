/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	ObservableList<String> observList=FXCollections.observableArrayList();
	CorsoDAO corsoDB= new CorsoDAO();
	StudenteDAO studenteDB= new StudenteDAO();
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxChoice"
    private ChoiceBox<String> boxChoice; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtIdStudente"
    private TextField txtIdStudente; // Value injected by FXMLLoader

    @FXML // fx:id="checkBox"
    private CheckBox checkBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtNomeStudente"
    private TextField txtNomeStudente; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognomeSTudente"
    private TextField txtCognomeSTudente; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsi"
    private Button btnCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void CercaCorsi(ActionEvent event) {

    }

    @FXML
    void CercaIscrittiCorso(ActionEvent event) {
    	String nomeCorso= boxChoice.getValue();
    	Corso corso= new Corso(null,null,nomeCorso,null);
    	txtOutput.clear();
    	for(Studente s:corsoDB.getStudentiIscrittiAlCorso(corso)) {
    		String nome=String.format("%-10s",s.getNome());
    		String cognome=String.format("%-30s",s.getCognome());
    		String matr=String.format("%-30s",s.getMatricola());
    		String CDS=String.format("%-30s",s.getCDS());
    		String strBuilder=matr+" "+nome+" "+cognome+" "+CDS;
    		txtOutput.appendText(strBuilder+"\n");
    	}

    }

    @FXML
    void IscriviStudente(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtIdStudente.clear();
    	txtNomeStudente.clear();
    	this.txtCognomeSTudente.clear();
    	this.txtOutput.clear();
    	boxChoice.setValue(null);
    }

    @FXML
    void doShowNomeCognomeStudente(ActionEvent event) {
    	Studente s;
    	String matrString= txtIdStudente.getText();
    	Integer matricola = null;
    	try {
    		matricola= Integer.parseInt(matrString);
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    		txtOutput.setText("Inserire una matricola composta da 6 cifre");
    	}
    	s=studenteDB.getStudente(matricola);
    	if(s==null) {
    		txtOutput.setText("La matricola inserita non corrisponde ad alcuno studente");
    		return;
    	}
    	txtNomeStudente.setText(s.getNome());
    	txtCognomeSTudente.setText(s.getCognome());
    }
    
    private void loadData() {
    	List<String> corsi=new ArrayList<String>();
    	corsi.add("Corsi");
    	for(Corso delDB: corsoDB.getTuttiICorsi()) {
    		corsi.add(delDB.getNome());
    	}
    	observList.addAll(corsi);
    	boxChoice.getItems().addAll(corsi);
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	loadData();
        assert boxChoice != null : "fx:id=\"boxChoice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIdStudente != null : "fx:id=\"txtIdStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNomeStudente != null : "fx:id=\"txtNomeStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognomeSTudente != null : "fx:id=\"txtCognomeSTudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
    }
}
