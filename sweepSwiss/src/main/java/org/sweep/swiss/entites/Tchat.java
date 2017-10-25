package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tchat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTchat;
	private String expediteur;
	private String destinataire;
	private String message;
	private Date dateEnvoi;
	private boolean statut;
	
	public Tchat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdTchat() {
		return idTchat;
	}
	public void setIdTchat(Long idTchat) {
		this.idTchat = idTchat;
	}
	public String getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateEnvoi() {
		return dateEnvoi;
	}
	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}
	public boolean isStatut() {
		return statut;
	}
	public void setStatut(boolean statut) {
		this.statut = statut;
	}	
	
	
}
