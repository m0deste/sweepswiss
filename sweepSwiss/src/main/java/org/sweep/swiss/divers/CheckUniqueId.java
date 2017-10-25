package org.sweep.swiss.divers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.sweep.swiss.dao.IClientRepository;
import org.sweep.swiss.entites.Client;


public class CheckUniqueId {

	@Autowired
	static IClientRepository clientRepo;
	
	public static boolean checkPhoneNumber(String value) {
			
		boolean etat = false;
		List<Client> clients = clientRepo.findAll();
		for (Client c : clients) {
			if(value == c.getPhone()) {
				etat = true;
			}
		}		
		return etat;
	}
	
	public static boolean checkEmail(String value) {
		
		boolean etat = false;
		List<Client> clients = clientRepo.findAll();
		for (Client c : clients) {
			if(value == c.getEmail()) {
				etat = true;
			}
		}		
		return etat;
	}
	
	public static boolean checkToken(String value) {
		
		boolean etat = false;
		List<Client> clients = clientRepo.findAll();
		for (Client c : clients) {
			if(value == c.getToken()) {
				etat = true;
			}
		}		
		return etat;
	}
}
