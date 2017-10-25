package org.sweep.swiss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Compteur;

public interface ICompteurRepository extends JpaRepository<Compteur, Long>{

	@Query("SELECT c FROM Compteur c WHERE c.client.id=:x")
	public Compteur compteurClient(@Param("x") Long clientId);
}
