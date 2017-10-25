package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Service implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idService;
	@Column(unique=true)
	private String nom;	
	
	@ManyToMany(mappedBy="services")
	private Collection<Nettoyeur> nettoyeurs;
	
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Collection<Nettoyeur> getNettoyeurs() {
		return nettoyeurs;
	}



	public void setNettoyeurs(Collection<Nettoyeur> nettoyeurs) {
		this.nettoyeurs = nettoyeurs;
	}



	public Long getIdService() {
		return idService;
	}


	public void setIdService(Long idService) {
		this.idService = idService;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
