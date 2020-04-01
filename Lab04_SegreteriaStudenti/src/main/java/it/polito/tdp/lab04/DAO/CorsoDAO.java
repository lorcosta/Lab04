package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/**
	 * Ottengo tutti i corsi salvati nel Db
	 * @return {@link List}<{@link Corso}>
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c= new Corso(codins,numeroCrediti,nome,periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/**
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/**
	 * Ottengo tutti gli studenti iscritti al Corso
	 * @param {@link Corso}
	 * @return {@link List}<{@link Studente}>
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql= "SELECT s.matricola,s.nome,s.cognome,s.CDS "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND c.nome=?";
		List<Studente> studentiIscritti= new LinkedList<Studente>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, corso.getNome());
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				Integer matricola=rs.getInt("matricola");
				String nome=rs.getString("nome");
				String cognome=rs.getString("cognome");
				String CDS=rs.getString("CDS");
				studentiIscritti.add(new Studente(matricola,nome,cognome,CDS));
				//String studente=matricola+" "+nome+" "+cognome+" "+CDS;
				//System.out.println(studente);
			}
			conn.close();
			return studentiIscritti;
			
		}catch(SQLException e) {
			throw new RuntimeException("Errore DB",e);
		}
	}

	/**
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 * @param {@link Studente, Corso}
	 * @return {@code true} se iscrizione va a buon fine, {@code false} altrimenti
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		
		String sql="INSERT INTO iscrizione VALUES (?,?)";
		boolean ritorno=false;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getNome());
			Integer rs=st.executeUpdate();
			if(rs.equals(1)) {
				ritorno=true;
			}
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException("Errore DB",e);
		}
		// ritorna true se l'iscrizione e' avvenuta con successo
		return ritorno;
	}
}




























