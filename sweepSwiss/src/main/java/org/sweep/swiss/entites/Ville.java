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
public class Ville implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVille;
	@Column(unique=true)
	private String nom;
	
	@OneToMany(mappedBy="ville", fetch=FetchType.LAZY)
	private Collection<Nettoyeur> nettoyeurs;
	
	@OneToMany(mappedBy="ville", fetch=FetchType.LAZY)
	private Collection<Client> clients;
	
	@ManyToOne
	@JoinColumn(name="idCanton")
	private Canton canton;
	
	
	
	public Collection<Nettoyeur> getNettoyeurs() {
		return nettoyeurs;
	}

	public void setNettoyeurs(Collection<Nettoyeur> nettoyeurs) {
		this.nettoyeurs = nettoyeurs;
	}

	public Collection<Client> getClients() {
		return clients;
	}

	public void setClients(Collection<Client> clients) {
		this.clients = clients;
	}

	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getIdVille() {
		return idVille;
	}

	public void setIdVille(Long idVille) {
		this.idVille = idVille;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}
	
	
}
