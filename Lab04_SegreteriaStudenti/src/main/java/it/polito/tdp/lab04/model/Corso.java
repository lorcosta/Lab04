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
	
	
	
}
