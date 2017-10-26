package org.sweep.swiss;

//import java.text.DateFormat;
import java.text.ParseException;
//import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
/*import org.sweep.swiss.dao.IClientRepository;
import org.sweep.swiss.dao.INettoyeurRepository;
import org.sweep.swiss.divers.Roles;
import org.sweep.swiss.entites.Client;
import org.sweep.swiss.entites.Nettoyeur;*/
//import org.sweep.swiss.dao.IAdminRepository;
//import org.sweep.swiss.entites.Admin;

@SpringBootApplication
public class SweepSwissApplication {

	public static void main(String[] args) throws ParseException{
		//ApplicationContext ctx = 
				SpringApplication.run(SweepSwissApplication.class, args);
	
		/*IClientRepository clientRepo = ctx.getBean(IClientRepository.class);
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		clientRepo.save(new Client("client", "123", true, "Client"));
		
		INettoyeurRepository  netRepo = ctx.getBean(INettoyeurRepository.class);
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		netRepo.save(new Nettoyeur("net", "123", true, Roles.Nettoyeur.toString()));*/
	}
}
