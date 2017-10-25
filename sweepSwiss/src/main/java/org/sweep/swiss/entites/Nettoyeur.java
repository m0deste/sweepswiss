package org.sweep.swiss.entites;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Nettoyeur extends Compte{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="idVille")
	private Ville ville;
	
	@OneToMany(mappedBy="nettoyeur", fetch=FetchType.LAZY)
	private Collection<Reserver> reservers;
	
	@OneToMany(mappedBy="nettoyeur", fetch=FetchType.LAZY)
	private Collection<Avis> lesAvis;
	
	@ManyToMany
	@JoinTable(name="Nettoyeur_Service", joinColumns= @JoinColumn(name="idNettoyeur"), inverseJoinColumns=@JoinColumn(name="idService"))
	private Collection<Service> services;
	
	public Nettoyeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Collection<Service> getServices() {
		return services;
	}



	public void setServices(Collection<Service> services) {
		this.services = services;
	}



	public Collection<Avis> getLesAvis() {
		return lesAvis;
	}


	public void setLesAvis(Collection<Avis> lesAvis) {
		this.lesAvis = lesAvis;
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
