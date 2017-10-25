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
public class Pays implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPays;
	@Column(unique=true)
	private String nom;
	
	@OneToMany(mappedBy="pays", fetch=FetchType.LAZY)
	private Collection<Canton> cantons;
	
	
	public Collection<Canton> getCantons() {
		return cantons;
	}
	public void setCantons(Collection<Canton> cantons) {
		this.cantons = cantons;
	}
	
	public Long getIdPays() {
		return idPays;
	}
	public void setIdPays(Long idPays) {
		this.idPays = idPays;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
