package org.sweep.swiss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Nettoyeur;

public interface INettoyeurRepository extends JpaRepository<Nettoyeur, Long>{

	@Query("select r.nettoyeur from Reserver r where r.client.id = :x")
	public List<Nettoyeur> listeNettoyeurParClient(@Param("x") Long idClient);
	
	@Query("SELECT n FROM Nettoyeur n WHERE n.email=:x or n.phone=:x")
	public Nettoyeur nettoyeurParIdentifiant(@Param("x") String mc);
}
