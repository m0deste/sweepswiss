package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Avis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String idAvis;
	private String avis;
	private int note;
	private Date dateAvis;
	
	@ManyToOne
    @PrimaryKeyJoinColumn(name = "clientId", referencedColumnName = "id")
	private Client client;
	
	@ManyToOne
    @PrimaryKeyJoinColumn(name = "nettoyeurId", referencedColumnName = "id")
	private Nettoyeur nettoyeur;

	public Avis() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getIdAvis() {
		return idAvis;
	}



	public void setIdAvis(String idAvis) {
		this.idAvis = idAvis;
	}



	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Date getDateAvis() {
		return dateAvis;
	}

	public void setDateAvis(Date dateAvis) {
		this.dateAvis = dateAvis;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Nettoyeur getNettoyeur() {
		return nettoyeur;
	}

	public void setNettoyeur(Nettoyeur nettoyeur) {
		this.nettoyeur = nettoyeur;
	}
	
	
}
