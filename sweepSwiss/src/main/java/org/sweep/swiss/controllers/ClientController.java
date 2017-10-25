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
import org.sweep.swiss.dao.ICompteurRepository;
import org.sweep.swiss.dao.INettoyeurRepository;
import org.sweep.swiss.dao.IPaiementRepository;
import org.sweep.swiss.dao.IReserverRepository;
import org.sweep.swiss.divers.CheckUniqueId;
import org.sweep.swiss.divers.Roles;
import org.sweep.swiss.entites.Avis;
import org.sweep.swiss.entites.Client;
import org.sweep.swiss.entites.Compteur;
import org.sweep.swiss.entites.Nettoyeur;
import org.sweep.swiss.entites.Paiement;
import org.sweep.swiss.entites.Reserver;

@RestController
public class ClientController {

	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private INettoyeurRepository nettoyeurRepository;
	@Autowired
	private IPaiementRepository paiementRepository;
	@Autowired
	private IReserverRepository reserverRepository;
	@Autowired
	private ICompteurRepository compteurRepository;
	@Autowired
	private IAvisRepository avisRepository;
	
	@RequestMapping(value="/ajouterClient", method=RequestMethod.POST)
	public Map<String, String> inscriptionClient(Client c) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");
		
			Date date = new Date();
			c.setDateInscription(date);
			c.setEtat(true);
			c.setStatut(true);
			c.setAlterEmail(c.getAlterEmail());
			c.setAlterPhone(c.getAlterPhone());
			c.setEtat(true);
			c.setStatut(false);
			c.setRole(Roles.Client.toString());
			if(c.getEmail() == null) {
				if(!CheckUniqueId.checkPhoneNumber(c.getPhone()))	{
					String token = GUIDGenerator.ENTITY_NAME; //generation de 6 chiffres pr le texto
					while (CheckUniqueId.checkToken(token)) {
						token = GUIDGenerator.ENTITY_NAME;//redefinition du token à 6chiffres
					}
					c.setToken(token);
					clientRepository.save(c);
					//send Confirm Texto
					
					result.put("msg", "Opération réussie");
					result.put("etat", "1");
				}else {
					result.put("msg", "Numéro de Téléphone invalide");
					result.put("etat", "0");
				}

			}else {
				if(!CheckUniqueId.checkEmail(c.getEmail()))	{
					String token = GUIDGenerator.ENTITY_NAME;
					c.setToken(token);
					clientRepository.save(c);
					
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
		
	@RequestMapping(value="/mdpClient", method=RequestMethod.PUT)
	public boolean modifierClientPassword(String username, String oldMdp, String newMdp) {
		
		boolean etat = false;		
		Client client = clientRepository.clientParIdentifiant(username);
		if(client != null) {
			if(client.getPassword() == oldMdp) {
				client.setPassword(newMdp);
				clientRepository.saveAndFlush(client);
				etat = true;
			}
		}			
		return etat;	
	}
	
	@RequestMapping(value="/renvoyerClientMdp", method=RequestMethod.PUT)
	public boolean renvoyerClientPassword(String typeId, String ref) {
		
		boolean etat = false;		
		Client client = clientRepository.clientParIdentifiant(ref);
		if(client != null) {
		
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
	
	@RequestMapping(value="/uploadClientPhoto", method=RequestMethod.PUT)
	public boolean modifierPhoto() {
		return true;
	}

	@RequestMapping(value="/confirmerClientInscription", method=RequestMethod.GET)
	public Client confirmerInscription(String token) {
		
		Client client = new Client();
		List<Client> clients = clientRepository.findAll();
		
		for (Client c : clients) {
			if(c.getToken()==token) {
				c.setStatut(true);
				clientRepository.save(c);
				client = c;
				break;
			}			
		}
		return client;
	}
	
	@RequestMapping(value="/changerClientIdentifiant", method=RequestMethod.PUT)
	public Map<String, String> changerClientIdentifiant(String typeId, String username, String newId) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");

		
		Client client = clientRepository.clientParIdentifiant(username);
		if(client != null) {
			switch (typeId) {
			case "Phone":					
				if(client.getPhone() != newId) {
					
					if(!CheckUniqueId.checkPhoneNumber(newId))	{			

						String token = GUIDGenerator.ENTITY_NAME; //generation de 6 chiffres pr le texto
						while (CheckUniqueId.checkToken(token)) {
							token = GUIDGenerator.ENTITY_NAME;//redefinition du token à 6chiffres
						}
						client.setAlterPhone(newId);
						client.setToken(token);
						clientRepository.saveAndFlush(client);
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
				if(client.getEmail() != newId) {
					
					if(!CheckUniqueId.checkEmail(newId))	{
						
						String token = GUIDGenerator.ENTITY_NAME;
						client.setAlterEmail(newId);
						client.setToken(token);
						clientRepository.saveAndFlush(client);
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
	
	@RequestMapping(value="/changerClientIdentifiantConfirme", method=RequestMethod.GET)
	public boolean confirmerChangementIdentifiantClient(String typeId, String id, String token) {
		
		boolean etat = false;
		Client client = clientRepository.clientParIdentifiant(id);
		if(client != null) {
			switch (typeId) {
			case "Phone":					
				if(client.getToken() != token) {
					client.setPhone(client.getAlterPhone());
					client.setToken("");
					clientRepository.saveAndFlush(client);
					//SendEmail
					
					etat = true;
				} 
				break;
			case "Email":
				if(client.getToken() != token) {					
					client.setEmail(client.getAlterEmail());
					clientRepository.saveAndFlush(client);
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
	
	@RequestMapping(value="/majClientProfile", method=RequestMethod.PUT)
	public Client modifierProfile(Client client) {	
		Client c = clientRepository.findOne(client.getId());
		Date date = new Date();
		c.setDateModification(date);
		c.setDateNaissance(client.getDateNaissance());
		c.setNationalite(client.getNationalite());
		c.setNom(client.getNom());
		c.setPrenom(client.getPrenom());
		c.setAdresse(client.getAdresse());
		c.setCodePostal(client.getCodePostal());
		c.setPermisSejour(client.getPermisSejour());
		c.setSociete(client.getSociete());
		return clientRepository.saveAndFlush(c);
	}

	/*@RequestMapping(value="/adminProfile", method=RequestMethod.GET)
	public Admin adminProfile(Long username) {
		return adminRepository.findOne(username);
	}*/
	//AVIS
	@RequestMapping(value="/ajouterAvis", method=RequestMethod.POST)
	public Avis ajouterAvis(Avis a) {
		Date date = new Date();
		a.setDateAvis(date);
		return avisRepository.save(a);
	}
	
	@RequestMapping(value="/modifierAvis", method=RequestMethod.POST)
	public Avis modifierAvis(Avis a) {
		return avisRepository.saveAndFlush(a);
	}
	
	
	@RequestMapping(value="/clientAvis", method=RequestMethod.GET)
	public List<Avis> listeAvisClient(Long idClient){
		return avisRepository.listeAvisClient(idClient);
	}
	//FIN AVIS
	
	
	@RequestMapping(value="/clientProfile", method=RequestMethod.GET)
	public Client profileClient(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");	
		String username= securityContext.getAuthentication().getName();
	    return clientRepository.clientParIdentifiant(username);
	}

	//RESERVATION
	@RequestMapping(value="/reserver", method=RequestMethod.POST)
	public Reserver effectuerReservation(Reserver r) {		
		return reserverRepository.save(r);
	}
	
	@RequestMapping(value="/modifierReservation", method=RequestMethod.PUT)
	public Reserver modifierReservation(Reserver r) {		
		return reserverRepository.saveAndFlush(r);
	}

	@RequestMapping(value="/clientReservations", method=RequestMethod.GET)
	public List<Reserver> listeReservationParClient(Long idClient) {		
		return reserverRepository.listeReservationParClient(idClient);
	}
	
	//NETTOYEUR
	@RequestMapping(value="/clientNettoyeurs", method=RequestMethod.GET)
	public List<Nettoyeur> listeNettoyeurParClient(Long idClient){
		return nettoyeurRepository.listeNettoyeurParClient(idClient);
	}
	
	
	//PAIEMENT & COMPTEUR

	@RequestMapping(value="/clientCompteur", method=RequestMethod.GET)
	public Compteur compteurClient(Long clientId) {
		return compteurRepository.compteurClient(clientId);			
	}
	
	@RequestMapping(value="/clientPaiements", method=RequestMethod.GET)
	public List<Paiement> listePaiementParClient(Long idClient) {
		return paiementRepository.listePaiementParClient(idClient);
	}
	


}
