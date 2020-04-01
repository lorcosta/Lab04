package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	/**
	 * Ritorna un oggetto studente data la sua matricola
	 * @param matricola
	 * @return {@link Studente} se lo studente appartiene al DB, {@code null} altrimenti
	 */
	public Studente getStudente(Integer matricola){
		final String sql="select cognome,nome,matricola,CDS from studente where matricola=? ";
		
		Studente s=null;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs= st.executeQuery();
			
			if(rs.next()) {
				String nome=rs.getString("nome");
				Integer matr=rs.getInt("matricola");
				String cognome=rs.getString("cognome");
				String CDS=rs.getString("CDS");
				//System.out.println(nome+" "+cognome);
				s= new Studente(matr, nome,cognome,CDS);
			}
			conn.close();
			return s;
			
		}catch (SQLException e) {
			throw new RuntimeException("Errore DB",e);
		}
	}
	/**
	 * Ritorna l'insieme dei corsi ai quali uno studente è iscritto
	 * @param matricola
	 * @return List<Corso>
	 */
	public List<Corso> getCorsiStudente(Integer matricola){
		String sql="SELECT c.codins,c.crediti,c.nome,c.pd "
				+ "FROM corso c, studente s, iscrizione i "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND s.matricola=?";
		List<Corso> corsi= new LinkedList<Corso>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				String codins=rs.getString("codins");
				Integer crediti=rs.getInt("crediti");
				String nome=rs.getString("nome");
				Integer pd=rs.getInt("pd");
				corsi.add(new Corso(codins,crediti,nome,pd));
			}
			conn.close();
			return corsi;
		}catch(SQLException e){
			throw new RuntimeException("Errore DB", e);
		}
	}
	/**
	 * Restituisce true se lo studente identificato da {@code matricola} è iscritto al {@link Corso}
	 * passato come parametro
	 * @param matricola {@link Integer}
	 * @param {@link Corso}
	 * @return {@code true} o {@code false}
	 */
	public boolean isStudenteIscrittoAlCorso(Integer matricola, Corso corso) {
		String sql="SELECT c.codins,c.crediti,c.nome,c.pd "
				+ "FROM corso c, studente s, iscrizione i "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins "
				+ "AND s.matricola=? AND c.nome=?";
		boolean ritorno=false;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, corso.getNome());
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				ritorno=true;
			}
			conn.close();
		}catch(SQLException e){
			throw new RuntimeException("Errore DB", e);
		}
		return ritorno;
	}
}