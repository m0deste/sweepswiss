package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Reserver implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String idReserver;
	private Date dateReserver;
	private Date dateFinReserver;
	private Date dateExecute;
	private boolean statut; //Verifier si la reserve à été accepté par un nettoyeur
	private boolean etat; //Vérifie fin exécution du service
	private boolean ispaie; //Vérifie si la reserve à été payé
	
	@ManyToOne
    @PrimaryKeyJoinColumn(name = "typeReservationId", referencedColumnName = "idType")
	private TypeReservation typeReservation;
	
	@ManyToOne
    @PrimaryKeyJoinColumn(name = "clientId", referencedColumnName = "id")
	private Client client;
	
	@ManyToOne
    @PrimaryKeyJoinColumn(name = "nettoyeurId", referencedColumnName = "id")
	private Nettoyeur nettoyeur;

	public Reserver() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Date getDateFinReserver() {
		return dateFinReserver;
	}



	public void setDateFinReserver(Date dateFinReserver) {
		this.dateFinReserver = dateFinReserver;
	}



	public String getIdReserver() {
		return idReserver;
	}



	public void setIdReserver(String idReserver) {
		this.idReserver = idReserver;
	}



	public Date getDateReserver() {
		return dateReserver;
	}

	public void setDateReserver(Date dateReserver) {
		this.dateReserver = dateReserver;
	}

	public Date getDateExecute() {
		return dateExecute;
	}

	public void setDateExecute(Date dateExecute) {
		this.dateExecute = dateExecute;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public boolean isIspaie() {
		return ispaie;
	}

	public void setIspaie(boolean ispaie) {
		this.ispaie = ispaie;
	}

	public TypeReservation getTypeReservation() {
		return typeReservation;
	}

	public void setTypeReservation(TypeReservation typeReservation) {
		this.typeReservation = typeReservation;
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
