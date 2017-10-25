package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Canton implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCanton;
	@Column(unique=true)
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="idPays")
	private Pays pays;
	
	@OneToMany(mappedBy="canton", fetch=FetchType.LAZY)
	private Collection<Ville> villes;
	
	public Canton() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Long getIdCanton() {
		return idCanton;
	}



	public void setIdCanton(Long idCanton) {
		this.idCanton = idCanton;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Collection<Ville> getVilles() {
		return villes;
	}

	public void setVilles(Collection<Ville> villes) {
		this.villes = villes;
	}
	
	
	
}
