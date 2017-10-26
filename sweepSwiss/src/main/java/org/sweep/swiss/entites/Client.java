package org.sweep.swiss.entites;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String societe;
	
	@ManyToOne
	@JoinColumn(name="idVille")
	private Ville ville;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Reserver> reservers;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Paiement> paiements;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Compteur> compteurs;
	
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Avis> lesAvis;
	
	public Client(String email, String password, boolean activer, String role) {
		super(email, password, activer, role);
		// TODO Auto-generated constructor stub
	}



	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getSociete() {
		return societe;
	}



	public void setSociete(String societe) {
		this.societe = societe;
	}



	public Collection<Avis> getLesAvis() {
		return lesAvis;
	}



	public void setLesAvis(Collection<Avis> lesAvis) {
		this.lesAvis = lesAvis;
	}



	public Collection<Compteur> getCompteurs() {
		return compteurs;
	}



	public void setCompteurs(Collection<Compteur> compteurs) {
		this.compteurs = compteurs;
	}



	public Collection<Paiement> getPaiements() {
		return paiements;
	}



	public void setPaiements(Collection<Paiement> paiements) {
		this.paiements = paiements;
	}



	public Ville getVille() {
		return ville;
	}


	public void setVille(Ville ville) {
		this.ville = ville;
	}


	public Collection<Reserver> getReservers() {
		return reservers;
	}


	public void setReservers(Collection<Reserver> reservers) {
		this.reservers = reservers;
	}
	
}
