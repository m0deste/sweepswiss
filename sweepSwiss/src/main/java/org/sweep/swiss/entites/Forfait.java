package org.sweep.swiss.entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Forfait implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idForfait;
	private int heure;
	
	public Forfait() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getIdForfait() {
		return idForfait;
	}


	public void setIdForfait(Long idForfait) {
		this.idForfait = idForfait;
	}


	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

		
}
