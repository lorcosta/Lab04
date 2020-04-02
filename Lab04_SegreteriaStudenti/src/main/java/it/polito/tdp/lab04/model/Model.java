package it.polito.tdp.lab04.model;

import java.util.Collection;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	CorsoDAO corsoDB= new CorsoDAO();
	StudenteDAO studenteDB= new StudenteDAO();
	public Studente getStudente(Integer matricola) {
		return studenteDB.getStudente(matricola);
	}
	public boolean isStudenteIscrittoAlCorso(Integer matricola, Corso corso) {
		
		return studenteDB.isStudenteIscrittoAlCorso(matricola, corso);
	}
	public Collection<Corso> getCorsiStudente(Integer matricola) {
		return studenteDB.getCorsiStudente(matricola);
	}
	public Collection<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return corsoDB.getStudentiIscrittiAlCorso(corso);
	}
	public boolean iscriviStudenteACorso(Studente s, Corso corso) {
		return corsoDB.iscriviStudenteACorso(s, corso);
	}
	public Collection<Corso> getTuttiICorsi() {
		// TODO Auto-generated method stub
		return corsoDB.getTuttiICorsi();
	}
	
	
	
	
	
}
