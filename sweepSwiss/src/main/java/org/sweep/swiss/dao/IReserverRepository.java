package org.sweep.swiss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Reserver;

public interface IReserverRepository extends JpaRepository<Reserver, String>{

	@Query("SELECT r FROM Reserver r WHERE r.client.id =:x")
	public List<Reserver> listeReservationParClient(@Param("x") Long idClient);
	
	@Query("SELECT r FROM Reserver r WHERE r.nettoyeur.id =:x")
	public List<Reserver> listeReservationParNettoyeur(@Param("x") Long idNet);
	
	
	
}
