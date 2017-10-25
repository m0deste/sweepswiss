package org.sweep.swiss.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sweep.swiss.dao.IAdminRepository;
import org.sweep.swiss.dao.IApplicationRepository;
import org.sweep.swiss.dao.ICantonRepository;
import org.sweep.swiss.dao.IClientRepository;
import org.sweep.swiss.dao.IForfaitRepository;
import org.sweep.swiss.dao.INettoyeurRepository;
import org.sweep.swiss.dao.IPaiementRepository;
import org.sweep.swiss.dao.IPaysRepository;
import org.sweep.swiss.dao.IReserverRepository;
import org.sweep.swiss.dao.IServiceRepository;
import org.sweep.swiss.dao.ITchatRepository;
import org.sweep.swiss.dao.ITypeReservationRepository;
import org.sweep.swiss.dao.IVilleRepository;
import org.sweep.swiss.entites.Tchat;

@RestController
public class PartagerController {

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
	private ITchatRepository tchatRepository;
	@Autowired
	private ITypeReservationRepository typeReservationRepository;
	
	@Autowired
	private IVilleRepository villeRepository;	
	
	@RequestMapping(value="/supprimer", method=RequestMethod.DELETE)
	public boolean supprimerTable(String table, Long ref) {
		
		boolean etat = false;
		
		switch (table) {
		case "Admin":
			adminRepository.delete(ref);
			etat = true;
			break;
		case "Application":
			applicationRepository.delete(ref);
			etat = true;
			break;
		case "Canton":
			cantonRepository.delete(ref);
			etat = true;
			break;
		case "Client":
			clientRepository.delete(ref);
			etat = true;
			break;
		case "Forfait":
			forfaitRepository.delete(ref);
			etat = true;
			break;
		case "Nettoyeur":
			nettoyeurRepository.delete(ref);	
			etat = true;
			break;
		case "Paiement":
			paiementRepository.delete(ref);
			etat = true;
			break;
		case "Pays":
			paysRepository.delete(ref);
			etat = true;
			break;
		case "Reserver":
			reserverRepository.delete(ref.toString());
			etat = true;
			break;
		case "Service":
			serviceRepository.delete(ref);
			etat = true;
			break;
		case "Tchat":
			tchatRepository.delete(ref);
			etat = true;
			break;
		case "TypeReservation":
			typeReservationRepository.delete(ref);
			etat = true;
			break;
		case "Ville":
			villeRepository.delete(ref);
			etat = true;
			break;
		default:
			break;
		}
		
		return etat;
	}
	

	//Tchat
	@RequestMapping(value="/ajouterTchat", method=RequestMethod.POST)
	public Tchat ajouterTchat(Tchat t){
		return tchatRepository.save(t);
	}
	
	@RequestMapping(value="/modifierTchat", method=RequestMethod.PUT)
	public Tchat modifierTchat(Tchat t){		
		return tchatRepository.saveAndFlush(t);
	}
	
	@RequestMapping(value="/tchats", method=RequestMethod.GET)
	public List<Tchat> listeTchats(){
		return tchatRepository.findAll();
	}
	
	@RequestMapping(value="/tchat", method=RequestMethod.GET)
	public Tchat detailsTchat(Long id) {
		return tchatRepository.findOne(id);	
	}
	
	@RequestMapping(value="/supprimerTchat", method=RequestMethod.DELETE)
	public boolean supprimerTchat(Long id) {
		tchatRepository.delete(id);	
		return true;
	}
	


}
