package org.sweep.swiss.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Application implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idApp;
	private String logo;
	private String nom;
	private String couleur;
	private String email;
	private String contact;
	private String adresse;
	private String copyright;
	private String cgu;
	private String pc;
	private String ccm;
	
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getIdApp() {
		return idApp;
	}

	public void setIdApp(Long idApp) {
		this.idApp = idApp;
	}

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getCgu() {
		return cgu;
	}
	public void setCgu(String cgu) {
		this.cgu = cgu;
	}
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	public String getCcm() {
		return ccm;
	}
	public void setCcm(String ccm) {
		this.ccm = ccm;
	}
	
	
}
