package org.sweep.swiss.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//@MappedSuperclass
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_USER", discriminatorType=DiscriminatorType.STRING, length=10)
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	//@Column(unique=true, nullable=false)
	private String email;
	//@Column(unique=true)
	private String phone;
	private String alterEmail;
	//@Column(unique=true)
	private String token;
	private String password;
	private Date dateInscription;
	private Date dateModification;
	private String codePostal;
	private String adresse;
	private Boolean permisSejour;
	private String nationalite;
	private Date dateNaissance;
	private boolean activer; //Pour activation ou désactivation
	private String AlterPhone;
	@Column(unique=true)
	private String photo;
	private String role;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}		
	
	
	public User(String email, String password, boolean activer, String role) {
		super();
		this.email = email;
		this.password = password;
		this.activer = activer;
		this.role = role;
	}


	public boolean isActiver() {
		return activer;
	}

	public void setActiver(boolean activer) {
		this.activer = activer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getAlterPhone() {
		return AlterPhone;
	}

	public void setAlterPhone(String alterPhone) {
		AlterPhone = alterPhone;
	}

	

	public String getAlterEmail() {
		return alterEmail;
	}

	public void setAlterEmail(String alterEmail) {
		this.alterEmail = alterEmail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Boolean getPermisSejour() {
		return permisSejour;
	}
	public void setPermisSejour(Boolean permisSejour) {
		this.permisSejour = permisSejour;
	}
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
		
}
