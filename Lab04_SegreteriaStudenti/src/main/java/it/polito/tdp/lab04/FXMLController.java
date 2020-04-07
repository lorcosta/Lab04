/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
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
	Model model = new Model();
	
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
    	txtOutput.clear();
    	Studente s;
    	String matrString= txtIdStudente.getText();
    	Integer matricola = null;
    	if(matrString.length()!=6) {//Controllo che numero di caratteri sia corretto
    		txtOutput.setText("Matricola inserita non valida! Inserire una matricola composta da 6 cifre");
    		return;
    	}
    	try {//Provo a trasformare la matricola in un intero, così se ci sono lettere restituisco errore
    		matricola= Integer.parseInt(matrString);
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    		txtOutput.setText("Inserire una matricola composta da SOLE cifre");
    	}
    	s=model.getStudente(matricola);//Interrogo DB per ottenere lo studente
    	if(s==null) {//se lo studente ritorno è null allora la matricola non c'è nel DB
    		txtOutput.setText("La matricola inserita non corrisponde ad alcuno studente");
    		return;
    	}
    	if(boxChoice.getValue()!=null) {//Controllo se uno studente è iscritto ad un determinato corso
    		String nomeCorso= boxChoice.getValue();
        	Corso corso= new Corso(null,null,nomeCorso,null);
    		if(model.isStudenteIscrittoAlCorso(matricola, corso))
    			txtOutput.setText("Studente gia' iscritto a questo corso");
    		else txtOutput.setText("Studente non ancora iscritto a questo corso");
    		return;
    	}
    	List<Corso> corsiIscritto= new LinkedList<Corso>(model.getCorsiStudente(matricola));//Lista dei corsi che frequenta lo studente 
    	if(corsiIscritto.size()==0) {//Se la lista è vuota significa che lo studente non è iscritto ad alcun corso
    		txtOutput.setText("Lo studente non è iscritto ad alcun corso");
    		return;
    	}
    	StringBuilder sb= new StringBuilder();
    	for(Corso c:corsiIscritto) {//Se lo studente è presente visualizzo tutti i corsi ai quali è iscritto
    		sb.append(String.format("%-10s",c.getCodice()));
    		sb.append(String.format("%-20s",c.getNome()));
    		sb.append(String.format("%-20s",c.getCrediti()));
    		sb.append(String.format("%-10s",c.getPd()));
    		sb.append("\n");
    		txtOutput.appendText(sb.toString());
    	}
    }

    @FXML
    void CercaIscrittiCorso(ActionEvent event) {
    	String corsoString=boxChoice.getValue();
    	if(boxChoice.getValue()==null) {
    		txtOutput.setText("Selezionare un corso");
    		return;
    	}
    	String[]corsoTemp=corsoString.split("-");
    	Corso corso=new Corso(corsoTemp[1],null,corsoTemp[0],null);
    	txtOutput.clear();
    	txtNomeStudente.clear();
    	txtCognomeSTudente.clear();
    	List<Studente> studentiIscrittiAlCorso= new LinkedList<Studente>(model.getStudentiIscrittiAlCorso(corso));
    	if(studentiIscrittiAlCorso.size()==0) {//se la lista è vuota significa che non ci sono studenti iscritti a questo corso
    		txtOutput.setText("Non ci sono studenti iscritti a questo corso");
    		return;
    	}
    	StringBuilder sb= new StringBuilder();
    	for(Studente s:studentiIscrittiAlCorso) {
    		sb.append(String.format("%-10s",s.getNome()));
    		sb.append(String.format("%-20s",s.getCognome()));
    		sb.append(String.format("%-20s",s.getMatricola()));
    		sb.append(String.format("%-10s",s.getCDS()));
    		sb.append("\n");
    		txtOutput.appendText(sb.toString());
    	}
    }

    @FXML
    void IscriviStudente(ActionEvent event) {
    	Studente s;
    	Corso corso=null;
    	String matrString= txtIdStudente.getText();
    	Integer matricola = null;
    	if(matrString.length()!=6) {//Controllo che numero di caratteri sia corretto
    		txtOutput.setText("Matricola inserita non valida! Inserire una matricola composta da 6 cifre");
    		return;
    	}else if(boxChoice.getValue()==null) {
    		txtOutput.setText("Selezionare un corso");
    		return;
    	}
    	try {//Provo a trasformare la matricola in un intero, così se ci sono lettere restituisco errore
    		matricola= Integer.parseInt(matrString);
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    		txtOutput.setText("Inserire una matricola composta da SOLE cifre");
    	}
    	
    	s=model.getStudente(matricola);//Interrogo DB per ottenere lo studente
    	if(s==null) {//se lo studente ritorno è null allora la matricola non c'è nel DB
    		txtOutput.setText("La matricola inserita non corrisponde ad alcuno studente");
    		return;
    	}
    	if(boxChoice.getValue()!=null) {//Controllo se uno studente è iscritto ad un determinato corso
    		String corsoString=boxChoice.getValue();
        	String[]corsoTemp=corsoString.split("-");
        	corso=new Corso(corsoTemp[1],null,corsoTemp[0],null);
    		if(model.isStudenteIscrittoAlCorso(matricola, corso)) {
    			txtOutput.setText("Studente gia' iscritto a questo corso");
        		return;
    		}
    	}
    	if(model.iscriviStudenteACorso(s, corso))
    		txtOutput.setText("Studente iscritto con successo");
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
    	txtNomeStudente.clear();
    	txtCognomeSTudente.clear();
    	txtOutput.clear();
    	Studente s;
    	String matrString= txtIdStudente.getText();
    	Integer matricola = null;
    	try {
    		matricola= Integer.parseInt(matrString);
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    		txtOutput.setText("Inserire una matricola composta da 6 cifre");
    	}
    	s=model.getStudente(matricola);
    	if(s==null) {
    		txtOutput.setText("La matricola inserita non corrisponde ad alcuno studente");
    		return;
    	}
    	txtNomeStudente.setText(s.getNome());
    	txtCognomeSTudente.setText(s.getCognome());
    }
    
    private void loadData() {
    	List<String> corsi=new ArrayList<String>();
    	for(Corso delDB: model.getTuttiICorsi()) {
    		corsi.add(delDB.getNome()+"-"+delDB.getCodice());
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

	public void setModel(Model model) {
		this.model=model;
		
	}
}
