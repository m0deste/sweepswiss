package org.sweep.swiss.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Paiement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPaiement;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;

	public Paiement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdPaiement() {
		return idPaiement;
	}

	public void setIdPaiement(Long idPaiement) {
		this.idPaiement = idPaiement;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
