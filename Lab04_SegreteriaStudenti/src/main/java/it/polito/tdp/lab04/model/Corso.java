package it.polito.tdp.lab04.model;

public class Corso {
	private String codice;
	private Integer crediti;
	private String nome;
	private Integer pd; //periodo didattico
	/**
	 * @param codice
	 * @param crediti
	 * @param nome
	 * @param pd
	 */
	public Corso(String codice, Integer crediti, String nome, Integer pd) {
		super();
		this.codice = codice;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Integer getCrediti() {
		return crediti;
	}
	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPd() {
		return pd;
	}
	public void setPd(Integer pd) {
		this.pd = pd;
	}
	@Override
	public String toString() {
		return codice + " " + crediti + " " + nome + " " + pd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}
	
	
	
}
