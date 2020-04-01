package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(Integer matricola){
		
		final String sql="select cognome,nome from studente where matricola=? ";
		
		
		Studente s=null;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs= st.executeQuery();
			
			if(rs.next()) {
				String nome=rs.getString("nome");
				String cognome=rs.getString("cognome");
				//System.out.println(nome+" "+cognome);
				s= new Studente(null, nome,cognome,null);
			}
			conn.close();
			return s;
			
		}catch (SQLException e) {
			throw new RuntimeException("Errore DB",e);
		}
	}
}
