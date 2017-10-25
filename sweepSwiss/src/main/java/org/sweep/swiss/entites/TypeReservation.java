package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TypeReservation implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idType;
	@Column(unique=true)
	private String nom;
	private double prixHeure;
	
	@OneToMany(mappedBy="typeReservation", fetch=FetchType.LAZY)
	private Collection<Reserver> reservers;
	
	public TypeReservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Long getIdType() {
		return idType;
	}



	public void setIdType(Long idType) {
		this.idType = idType;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrixHeure() {
		return prixHeure;
	}

	public void setPrixHeure(double prixHeure) {
		this.prixHeure = prixHeure;
	}

	public Collection<Reserver> getReservers() {
		return reservers;
	}

	public void setReservers(Collection<Reserver> reservers) {
		this.reservers = reservers;
	}
	
}
