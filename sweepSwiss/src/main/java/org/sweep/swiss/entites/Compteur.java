package org.sweep.swiss.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Compteur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCompteur;
	private int nbreHeure;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Client client;

	public Compteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdCompteur() {
		return idCompteur;
	}

	public void setIdCompteur(Long idCompteur) {
		this.idCompteur = idCompteur;
	}

	public int getNbreHeure() {
		return nbreHeure;
	}

	public void setNbreHeure(int nbreHeure) {
		this.nbreHeure = nbreHeure;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
