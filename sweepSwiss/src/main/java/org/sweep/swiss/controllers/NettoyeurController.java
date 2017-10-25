package org.sweep.swiss.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sweep.swiss.dao.IAvisRepository;
import org.sweep.swiss.dao.IClientRepository;
import org.sweep.swiss.dao.INettoyeurRepository;
import org.sweep.swiss.dao.IReserverRepository;
import org.sweep.swiss.dao.IServiceRepository;
import org.sweep.swiss.divers.CheckUniqueId;
import org.sweep.swiss.divers.Roles;
import org.sweep.swiss.entites.Avis;
import org.sweep.swiss.entites.Client;
import org.sweep.swiss.entites.Nettoyeur;
import org.sweep.swiss.entites.Reserver;
import org.sweep.swiss.entites.Service;


@RestController
public class NettoyeurController {

	
	@Autowired 
	private IClientRepository clientRepository;
	@Autowired
	private INettoyeurRepository nettoyeurRepository;
	@Autowired
	private IReserverRepository reserverRepository;
	@Autowired
	private IServiceRepository serviceRepository;
	@Autowired
	private IAvisRepository avisRepository;
	
	@RequestMapping(value="/inscriptionNettoyeur", method=RequestMethod.POST)
	public Map<String, String> inscriptionNettoyeur(Nettoyeur n) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");
		
		Date date = new Date();
		n.setDateInscription(date);
		n.setEtat(true);
		n.setStatut(true);
		n.setAlterEmail(n.getAlterEmail());
		n.setAlterPhone(n.getAlterPhone());
		n.setEtat(true);
		n.setStatut(false);
		n.setRole(Roles.Nettoyeur.toString());
		if(n.getEmail() == null) {
			if(!CheckUniqueId.checkPhoneNumber(n.getPhone()))	{
				String token = GUIDGenerator.ENTITY_NAME; //generation de 6 chiffres pr le texto
				while (CheckUniqueId.checkToken(token)) {
					token = GUIDGenerator.ENTITY_NAME;//redefinition du token à 6chiffres
				}
				n.setToken(token);
				nettoyeurRepository.save(n);
				//send Confirm Texto
				
				result.put("msg", "Opération réussie");
				result.put("etat", "1");
			}else {
				result.put("msg", "Numéro de Téléphone invalide");
				result.put("etat", "0");
			}
		}else {
			if(!CheckUniqueId.checkEmail(n.getEmail()))	{
				String token = GUIDGenerator.ENTITY_NAME;
				n.setToken(token);
				nettoyeurRepository.save(n);
				
				//send Confirm Email
				
				result.put("msg", "Opération réussie");
				result.put("etat", "1");
			}else {
				result.put("msg", "Email invalide");
				result.put("etat", "0");
			}
		}
		
		return result;
	}
		
	@RequestMapping(value="/mdpNettoyeur", method=RequestMethod.PUT)
	public boolean modifierNettoyeurPassword(String username, String oldMdp, String newMdp) {
		
		boolean etat = false;		
		Nettoyeur nettoyeur = nettoyeurRepository.nettoyeurParIdentifiant(username);
		if(nettoyeur != null) {
			if(nettoyeur.getPassword() == oldMdp) {
				nettoyeur.setPassword(newMdp);
				nettoyeurRepository.saveAndFlush(nettoyeur);
				etat = true;
			}
		}			
		return etat;	
	}
	
	@RequestMapping(value="/renvoyerNettoyeurMdp", method=RequestMethod.PUT)
	public boolean renvoyerNettoyeurPassword(String typeId, String ref) {
		
		boolean etat = false;		
		Nettoyeur nettoyeur = nettoyeurRepository.nettoyeurParIdentifiant(ref);
		if(nettoyeur != null) {
			switch (typeId) {
				case "Phone":					
						//send phone message. 
					break;
				case "Email":
						//send email message. 

					break;
				default:
					break;
			}
		
		}			
		return etat;	
	}
	
	@RequestMapping(value="/uploadNettoyeurPhoto", method=RequestMethod.PUT)
	public boolean modifierPhoto() {
		return true;
	}

	@RequestMapping(value="/confirmerNettoyeurInscription", method=RequestMethod.GET)
	public Nettoyeur confirmerInscription(String token) {
		
		Nettoyeur nettoyeur = new Nettoyeur();
		List<Nettoyeur> nettoyeurs = nettoyeurRepository.findAll();
		
		for (Nettoyeur c : nettoyeurs) {
			if(c.getToken()==token) {
				c.setStatut(true);
				nettoyeurRepository.save(c);
				nettoyeur = c;
				break;
			}			
		}
		return nettoyeur;
	}
	
	@RequestMapping(value="/changerNettoyeurIdentifiant", method=RequestMethod.PUT)
	public  Map<String, String> changerNettoyeurIdentifiant(String typeId, String username, String newId) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");

		
		Nettoyeur nettoyeur = nettoyeurRepository.nettoyeurParIdentifiant(username);
		if(nettoyeur != null) {
			switch (typeId) {
			case "Phone":					
				if(nettoyeur.getPhone() != newId) {
					
					if(!CheckUniqueId.checkPhoneNumber(newId))	{			

						String token = GUIDGenerator.ENTITY_NAME; //generation de 6 chiffres pr le texto
						while (CheckUniqueId.checkToken(token)) {
							token = GUIDGenerator.ENTITY_NAME;//redefinition du token à 6chiffres
						}
						nettoyeur.setAlterPhone(newId);
						nettoyeur.setToken(token);
						nettoyeurRepository.saveAndFlush(nettoyeur);
						//SendConfirmPhoneNumberTexto
						


						result.put("msg", "Opération réussie");
						result.put("etat", "1");
					}else {
						result.put("msg", "Email invalide");
						result.put("etat", "0");
					}
					
					
					
				} 
				break;
			case "Email":
				if(nettoyeur.getEmail() != newId) {
					
					if(!CheckUniqueId.checkEmail(newId))	{
						String token = GUIDGenerator.ENTITY_NAME;
						nettoyeur.setAlterEmail(newId);
						nettoyeur.setToken(token);
						nettoyeurRepository.saveAndFlush(nettoyeur);
						//Send Confirm Email 						
						
						result.put("msg", "Opération réussie");
						result.put("etat", "1");
					}else {
						result.put("msg", "Email invalide");
						result.put("etat", "0");
					}
				} 

				break;
			default:
				break;
		}
			
		}
		
		return result;
	}
	
	@RequestMapping(value="/changerNettoyeurIdentifiantConfirme", method=RequestMethod.GET)
	public boolean confirmerChangementIdentifiantNettoyeur(String typeId, String id, String token) {
		
		boolean etat = false;
		Nettoyeur nettoyeur = nettoyeurRepository.nettoyeurParIdentifiant(id);
		if(nettoyeur != null) {
			switch (typeId) {
			case "Phone":					
				if(nettoyeur.getToken() != token) {
					nettoyeur.setPhone(nettoyeur.getAlterPhone());
					nettoyeur.setToken("");
					nettoyeurRepository.saveAndFlush(nettoyeur);
					//SendEmail
					
					etat = true;
				} 
				break;
			case "Email":
				if(nettoyeur.getToken() != token) {					
					nettoyeur.setEmail(nettoyeur.getAlterEmail());
					nettoyeurRepository.saveAndFlush(nettoyeur);
					
					//SendEmail
					
					etat = true;
				} 

				break;
			default:
				break;
		}
			
		}
		
		return etat;
	}
	
	@RequestMapping(value="/majNettoyeurProfile", method=RequestMethod.PUT)
	public Nettoyeur modifierProfile(Nettoyeur nettoyeur) {	
		Nettoyeur c = nettoyeurRepository.findOne(nettoyeur.getId());
		Date date = new Date();
		c.setDateModification(date);
		c.setDateNaissance(nettoyeur.getDateNaissance());
		c.setNationalite(nettoyeur.getNationalite());
		c.setNom(nettoyeur.getNom());
		c.setPrenom(nettoyeur.getPrenom());
		c.setAdresse(nettoyeur.getAdresse());
		c.setCodePostal(nettoyeur.getCodePostal());
		c.setPermisSejour(nettoyeur.getPermisSejour());
		return nettoyeurRepository.saveAndFlush(c);
	}

	@RequestMapping(value="/nettoyeurAvis", method=RequestMethod.GET)
	public List<Avis> listeAvisParNettoyeur(Long idNet){
		return avisRepository.listeAvisClient(idNet);
	}
	
	/*
	 * @RequestMapping(value="/adminProfile", method=RequestMethod.GET)
		public Admin adminProfile(Long username) {
			return adminRepository.findOne(username);
		}
	*/
	
	@RequestMapping(value="/nettoyeurProfile", method=RequestMethod.GET)
	public Nettoyeur profileNettoyeur(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");	
		String username= securityContext.getAuthentication().getName();
	    return nettoyeurRepository.nettoyeurParIdentifiant(username);
	}
	
	@RequestMapping(value="/nettoyeurReservations", method=RequestMethod.GET)
	public List<Reserver> listeReservationParNettoyeur(Long idNet){
		return reserverRepository.listeReservationParNettoyeur(idNet);
	}

	@RequestMapping(value="/accepterReservation", method=RequestMethod.POST)
	public String accepterReservation(String temps, String idReserver) {
		Reserver r = reserverRepository.findOne(idReserver);
		r.setStatut(true);
		reserverRepository.saveAndFlush(r);
		//Send email
		
		return temps;
	}
	
	@RequestMapping(value="/refuserReservation", method=RequestMethod.POST)
	public boolean refuserReservation(Long idReserver) {
		//sendEmail
		return true;
	}
	
	@RequestMapping(value="/nettoyeurClients", method=RequestMethod.GET)
	public List<Client> listeClientsParNettoyeur(Long idNet){
		return clientRepository.listeClientsParNettoyeur(idNet);
	}
	
	@RequestMapping(value="/ajouterServicesNettoyeur", method=RequestMethod.POST)
	public Nettoyeur ajouterNettoyeurServices(Long idNet, Long service) {
		Nettoyeur n = nettoyeurRepository.findOne(idNet);
		Service s = serviceRepository.findOne(service);
		n.getServices().add(s);
		return nettoyeurRepository.save(n);
	}
	
}
