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
import org.sweep.swiss.dao.IAdminRepository;
import org.sweep.swiss.dao.IApplicationRepository;
import org.sweep.swiss.dao.IAvisRepository;
import org.sweep.swiss.dao.ICantonRepository;
import org.sweep.swiss.dao.IClientRepository;
import org.sweep.swiss.dao.IForfaitRepository;
import org.sweep.swiss.dao.INettoyeurRepository;
import org.sweep.swiss.dao.IPaiementRepository;
import org.sweep.swiss.dao.IPaysRepository;
import org.sweep.swiss.dao.IReserverRepository;
import org.sweep.swiss.dao.IServiceRepository;
import org.sweep.swiss.dao.ITypeReservationRepository;
import org.sweep.swiss.dao.IVilleRepository;
import org.sweep.swiss.divers.CheckUniqueId;
import org.sweep.swiss.divers.Roles;
import org.sweep.swiss.entites.Admin;
import org.sweep.swiss.entites.Application;
import org.sweep.swiss.entites.Avis;
import org.sweep.swiss.entites.Canton;
import org.sweep.swiss.entites.Client;
import org.sweep.swiss.entites.Forfait;
import org.sweep.swiss.entites.Nettoyeur;
import org.sweep.swiss.entites.Paiement;
import org.sweep.swiss.entites.Pays;
import org.sweep.swiss.entites.Reserver;
import org.sweep.swiss.entites.Service;
import org.sweep.swiss.entites.TypeReservation;
import org.sweep.swiss.entites.Ville;

@RestController
public class AdminController {

	@Autowired //pour faire l'injection des dépendances
	private IAdminRepository adminRepository;
	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private INettoyeurRepository nettoyeurRepository;
	
	@Autowired
	private IApplicationRepository applicationRepository;
	@Autowired
	private ICantonRepository cantonRepository;
	@Autowired
	private IForfaitRepository forfaitRepository;
	
	@Autowired
	private IPaiementRepository paiementRepository;
	@Autowired
	private IPaysRepository paysRepository;
	@Autowired
	private IReserverRepository reserverRepository;
	
	@Autowired
	private IServiceRepository serviceRepository;
	@Autowired
	private ITypeReservationRepository typeReservationRepository;	
	@Autowired
	private IVilleRepository villeRepository;	
	@Autowired
	private IAvisRepository avisRepository;
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="/admins", method=RequestMethod.GET)
	public List<Admin> listeAdmins() {
		return adminRepository.findAll();		
	}
		
	@RequestMapping(value="/supprimerAdmin", method=RequestMethod.DELETE)
	public boolean supprimerAdmin(Long id) {
		adminRepository.delete(id);	
		return true;
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public Admin detailsAdmin(Long id) {
		return adminRepository.findOne(id);	
	}	
	
	@RequestMapping(value="/ajouterAdmin", method=RequestMethod.POST)
	public Map<String, String> inscriptionAdmin(Admin a) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");
		
		Date date = new Date();
		a.setDateInscription(date);
		a.setEtat(true);
		a.setStatut(true);
		
		if(a.getEmail() != null) {
			
			if(!CheckUniqueId.checkEmail(a.getEmail()))	{			
				//sendEmail
				adminRepository.save(a);
				result.put("msg", "Opération réussie");
				result.put("etat", "1");
			}else {
				result.put("msg", "Email invalide");
				result.put("etat", "0");
			}
		}
		
		return result;	
	}
		
	@RequestMapping(value="/mdpAdmin", method=RequestMethod.PUT)
	public boolean modifierAdminPassword(String username, String oldMdp, String newMdp) {
		
		boolean etat = false;		
		Admin admin = adminRepository.adminParIdentifiant(username);
		if(admin != null) {
			if(admin.getPassword() == oldMdp) {
				admin.setPassword(newMdp);
				adminRepository.saveAndFlush(admin);
				etat = true;
			}
		}			
		return etat;	
	}
	
	@RequestMapping(value="/renvoyerAdminMdp", method=RequestMethod.PUT)
	public boolean renvoyerAdminPassword(String ref) {
		
		boolean etat = false;		
		Admin admin = adminRepository.adminParIdentifiant(ref);
		if(admin != null) {
		
			//send email message. 

		}			
		return etat;	
	}
	
	@RequestMapping(value="/uploadAdminPhoto", method=RequestMethod.PUT)
	public boolean modifierPhoto() {
		return true;
	}

	/*@RequestMapping(value="/confirmerAjout", method=RequestMethod.GET)
	public Admin confirmerInscription(String token) {
		Admin admin = new Admin();
		return admin;
	}*/
	
	@RequestMapping(value="/changerIdentifiant", method=RequestMethod.PUT)
	public Map<String, String> changerIdentifiant(String email, String newEmail) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");
		
		
		Admin admin = adminRepository.adminParIdentifiant(email);
		if(admin != null) {
			
				if(admin.getEmail() != newEmail) {
					
					if(!CheckUniqueId.checkEmail(newEmail))	{			

						String token = GUIDGenerator.ENTITY_NAME;
						admin.setAlterEmail(newEmail);
						admin.setToken(token);
						adminRepository.saveAndFlush(admin);
						//SendEmail
						

						result.put("msg", "Opération réussie");
						result.put("etat", "1");
					}else {
						result.put("msg", "Email invalide");
						result.put("etat", "0");
					}
				}	
		}
		
		return result;
	}
	
	@RequestMapping(value="/changerIdentifiantConfirme", method=RequestMethod.PUT)
	public boolean confirmerChangementIdentifiant(String id, String token) {
		
		boolean etat = false;
		Admin admin = adminRepository.adminParIdentifiant(id);
		if(admin != null) {
			
				if(admin.getToken() != token) {					
					admin.setEmail(admin.getAlterEmail());
					adminRepository.saveAndFlush(admin);
					//SendEmail
					
					etat = true;
				} 
	
		}
		
		return etat;
	}
	
	@RequestMapping(value="/majProfile", method=RequestMethod.PUT)
	public Admin modifierProfile(Admin admin) {	
		Admin a = adminRepository.findOne(admin.getId());
		
		Date date = new Date();
		a.setDateModification(date);
		a.setDateNaissance(admin.getDateNaissance());
		a.setNationalite(admin.getNationalite());
		a.setNom(admin.getNom());
		a.setPrenom(admin.getPrenom());
		a.setAdresse(admin.getAdresse());
		a.setCodePostal(admin.getCodePostal());
		a.setRole(admin.getRole());
		return adminRepository.saveAndFlush(a);
	}

	/*@RequestMapping(value="/adminProfile", method=RequestMethod.GET)
	public Admin adminProfile(Long username) {
		return adminRepository.findOne(username);
	}*/
	
	@RequestMapping(value="/adminProfile", method=RequestMethod.GET)
	public Admin profileAdmin(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");	
		String username= securityContext.getAuthentication().getName();
	    return adminRepository.adminParIdentifiant(username);
	}

	
	
	//NETTOYEUR
	@RequestMapping(value="/ajouterNettoyeur", method=RequestMethod.POST)
	public Map<String, String> ajouterNettoyeur(Nettoyeur n) {
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", "Erreur");
		result.put("etat", "0");
		
		Date date = new Date();

		//sendEmail or send phone message and generated token
		n.setDateInscription(date);
		n.setEtat(true);
		n.setStatut(true);
		n.setRole(Roles.Nettoyeur.toString());
		
		if(n.getEmail() == null) {
			if(!CheckUniqueId.checkPhoneNumber(n.getPhone()))	{		
				nettoyeurRepository.save(n);
				result.put("msg", "Opération réussie");
				result.put("etat", "1");
			}else {
				result.put("msg", "Numéro de Téléphone invalide");
				result.put("etat", "0");
			}
		}else {
			if(!CheckUniqueId.checkEmail(n.getEmail()))	{			
				//send Confirm Email	
				nettoyeurRepository.save(n);
				result.put("msg", "Opération réussie");
				result.put("etat", "1");
			}else {
				result.put("msg", "Email invalide");
				result.put("etat", "0");
			}
		}
				
		return result;
	}
	
	@RequestMapping(value="/nettoyeurs", method=RequestMethod.GET)
	public List<Nettoyeur> listeNettoyeurs(){
		return nettoyeurRepository.findAll();
	}
	
	@RequestMapping(value="/nettoyeur", method=RequestMethod.GET)
	public Nettoyeur detailsNettoyeurs(Long id) {
		return nettoyeurRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerNettoyeur", method=RequestMethod.DELETE)
	public boolean supprimerNettoyeur(Long id) {
		nettoyeurRepository.delete(id);	
		return true;
	}
	
	
	//PAYS
	@RequestMapping(value="/ajouterPays", method=RequestMethod.POST)
	public Pays ajouterPays(Pays p){
		return paysRepository.save(p);
	}
	
	@RequestMapping(value="/modifierPays", method=RequestMethod.PUT)
	public Pays modifierPays(Pays p){		
		return paysRepository.saveAndFlush(p);
	}
	
	@RequestMapping(value="/lesPays", method=RequestMethod.GET)
	public List<Pays> listePays(){
		return paysRepository.findAll();
	}
	
	@RequestMapping(value="/unPays", method=RequestMethod.GET)
	public Pays detailsPays(Long id) {
		return paysRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerPays", method=RequestMethod.DELETE)
	public boolean supprimerPays(Long id) {
		paysRepository.delete(id);	
		return true;
	}
	//FIN-PAYS
	
	@RequestMapping(value="/basculerCompte", method=RequestMethod.PUT)
	public boolean toogle(String table, Long id) {
		
		boolean etat = false;
		
		switch (table) {
		case "Admin":
			
			Admin admin = adminRepository.findOne(id);
			if(admin != null) {
				boolean statut = !admin.isEtat();
				admin.setEtat(statut);
				admin.setStatut(true);
				adminRepository.saveAndFlush(admin);
				etat = true;
			}	
			
			break;
		case "Client":
			
			Client client = clientRepository.findOne(id);
			if(client != null) {
				boolean statut = !client.isEtat();
				client.setEtat(statut);
				client.setStatut(true);
				clientRepository.saveAndFlush(client);
				etat = true;
			}	
			
			break;
		case "Nettoyeur":
			Nettoyeur nettoyeur = nettoyeurRepository.findOne(id);
			if(nettoyeur != null) {
				boolean statut = !nettoyeur.isEtat();
				nettoyeur.setEtat(statut);
				nettoyeur.setStatut(true);
				nettoyeurRepository.saveAndFlush(nettoyeur);
				etat = true;
			}	
			break;
		default:
			break;
		}
		
		return etat;
	}

	
	
	//CLIENTS
	@RequestMapping(value="/clients", method=RequestMethod.GET)
	public List<Client> listeClients(){
		return clientRepository.findAll();
	}
	
	@RequestMapping(value="/client", method=RequestMethod.GET)
	public Client detailsClient(Long id) {
		return clientRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerClient", method=RequestMethod.DELETE)
	public boolean supprimerClient(Long id) {
		clientRepository.delete(id);	
		return true;
	}
	
	//PAIEMENTS
	@RequestMapping(value="/paies", method=RequestMethod.GET)
	public List<Paiement> listePaiements(){
		return paiementRepository.findAll();
	}
	
	@RequestMapping(value="/paie", method=RequestMethod.GET)
	public Paiement detailsPaiement(Long id) {
		return paiementRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerPaie", method=RequestMethod.DELETE)
	public boolean supprimerPaie(Long id) {
		paiementRepository.delete(id);	
		return true;
	}
	
	
	//Reserver
	@RequestMapping(value="/reservations", method=RequestMethod.GET)
	public List<Reserver> listeReservations(){
		return reserverRepository.findAll();
	}
	
	@RequestMapping(value="/reservation", method=RequestMethod.GET)
	public Reserver detailsReservation(Long id) {
		return reserverRepository.findOne(id.toString());	
	}
	
	@RequestMapping(value="/supprimerReservation", method=RequestMethod.DELETE)
	public boolean supprimerReservation(Long id) {
		reserverRepository.delete(id.toString());	
		return true;
	}
	
	
	//Ville
	@RequestMapping(value="/ajouterVille", method=RequestMethod.POST)
	public Ville ajouterVille(Ville v){
		return villeRepository.save(v);
	}
	
	@RequestMapping(value="/modifierVille", method=RequestMethod.PUT)
	public Ville modifierPays(Ville v){		
		return villeRepository.saveAndFlush(v);
	}
	
	@RequestMapping(value="/villes", method=RequestMethod.GET)
	public List<Ville> listeVilles(){
		return villeRepository.findAll();
	}
	
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public Ville detailsVille(Long id) {
		return villeRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerVille", method=RequestMethod.DELETE)
	public boolean supprimerVille(Long id) {
		villeRepository.delete(id);	
		return true;
	}
	
	
	//Canton
	@RequestMapping(value="/ajouterCanton", method=RequestMethod.POST)
	public Canton ajouterCanton(Canton c){
		return cantonRepository.save(c);
	}
	
	@RequestMapping(value="/modifierCanton", method=RequestMethod.PUT)
	public Canton modifierPays(Canton c){		
		return cantonRepository.saveAndFlush(c);
	}
	
	@RequestMapping(value="/cantons", method=RequestMethod.GET)
	public List<Canton> listeCantons(){
		return cantonRepository.findAll();
	}
	
	@RequestMapping(value="/canton", method=RequestMethod.GET)
	public Canton detailsCanton(Long id) {
		return cantonRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerCanton", method=RequestMethod.DELETE)
	public boolean supprimerCanton(Long id) {
		cantonRepository.delete(id);	
		return true;
	}
	
	
	//Application
	@RequestMapping(value="/ajouterApplication", method=RequestMethod.POST)
	public Application ajouterApplication(Application a){
		return applicationRepository.save(a);
	}
	
	@RequestMapping(value="/modifierApplication", method=RequestMethod.PUT)
	public Application modifierApplication(Application a){		
		return applicationRepository.saveAndFlush(a);
	}
	
	@RequestMapping(value="/applications", method=RequestMethod.GET)
	public List<Application> listeApplications(){
		return applicationRepository.findAll();
	}
	
	@RequestMapping(value="/application", method=RequestMethod.GET)
	public Application detailsApplication(Long id) {
		return applicationRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerApplication", method=RequestMethod.DELETE)
	public boolean supprimerApplication(Long id) {
		applicationRepository.delete(id);	
		return true;
	}
	
	
	//Forfait
	@RequestMapping(value="/ajouterForfait", method=RequestMethod.POST)
	public Forfait ajouterForfait(Forfait f){
		return forfaitRepository.save(f);
	}
	
	@RequestMapping(value="/modifierForfait", method=RequestMethod.PUT)
	public Forfait modifierForfait(Forfait a){		
		return forfaitRepository.saveAndFlush(a);
	}
	
	@RequestMapping(value="/forfaits", method=RequestMethod.GET)
	public List<Forfait> listeForfaits(){
		return forfaitRepository.findAll();
	}
	
	@RequestMapping(value="/forfait", method=RequestMethod.GET)
	public Forfait detailsForfait(Long id) {
		return forfaitRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerForfait", method=RequestMethod.DELETE)
	public boolean supprimerForfait(Long id) {
		forfaitRepository.delete(id);	
		return true;
	}
	
	
	//TypeReservation
		@RequestMapping(value="/ajouterTypeReservation", method=RequestMethod.POST)
		public TypeReservation ajouterTypeReservation(TypeReservation t){
			return typeReservationRepository.save(t);
		}
		
		@RequestMapping(value="/modifierTypeReservation", method=RequestMethod.PUT)
		public TypeReservation modifierTypeReservation(TypeReservation t){		
			return typeReservationRepository.saveAndFlush(t);
		}
		
		@RequestMapping(value="/typeReservations", method=RequestMethod.GET)
		public List<TypeReservation> listeTypeReservations(){
			return typeReservationRepository.findAll();
		}
		
		@RequestMapping(value="/typeReservation", method=RequestMethod.GET)
		public TypeReservation detailsTypeReservation(Long id) {
			return typeReservationRepository.findOne(id);	
		}
		
		@RequestMapping(value="/supprimerTypeReservation", method=RequestMethod.DELETE)
		public boolean supprimerTypeReservation(Long id) {
			typeReservationRepository.delete(id);	
			return true;
		}
		
		
		//Service
		@RequestMapping(value="/ajouterService", method=RequestMethod.POST)
		public Service ajouterService(Service s){
			return serviceRepository.save(s);
		}
		
		@RequestMapping(value="/modifierService", method=RequestMethod.PUT)
		public Service modifierService(Service s){		
			return serviceRepository.saveAndFlush(s);
		}
		
		@RequestMapping(value="/services", method=RequestMethod.GET)
		public List<Service> listeServices(){
			return serviceRepository.findAll();
		}
		
		@RequestMapping(value="/service", method=RequestMethod.GET)
		public Service detailsService(Long id) {
			return serviceRepository.findOne(id);	
		}
		
		@RequestMapping(value="/supprimerService", method=RequestMethod.DELETE)
		public boolean supprimerService(Long id) {
			serviceRepository.delete(id);	
			return true;
		}
		
		//AVIS
		@RequestMapping(value="/modererAvis", method=RequestMethod.PUT)
		public Avis modifierAvis(Avis a){		
			return avisRepository.saveAndFlush(a);
		}
		
		@RequestMapping(value="/lesAvis", method=RequestMethod.GET)
		public List<Avis> listeAvis(){
			return avisRepository.findAll();
		}
		
		@RequestMapping(value="/unAvis", method=RequestMethod.GET)
		public Avis detailsAvis(Long id) {
			return avisRepository.findOne(id.toString());	
		}
		
		@RequestMapping(value="/supprimerAvis", method=RequestMethod.DELETE)
		public boolean supprimerAvis(Long id) {
			avisRepository.delete(id.toString());	
			return true;
		}
		
}
