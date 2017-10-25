package org.sweep.swiss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

	@Query("SELECT c FROM Client c WHERE c.email=:x or c.phone=:x")
	public Client clientParIdentifiant(@Param("x") String mc);
	
	@Query("select r.client from Reserver r where r.nettoyeur.id = :x")
	public List<Client> listeClientsParNettoyeur(@Param("x") Long idNet);
}
