package org.sweep.swiss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Paiement;

public interface IPaiementRepository extends JpaRepository<Paiement, Long>{

	@Query("SELECT p FROM Paiement p WHERE p.client.id =:x")
	public List<Paiement> listePaiementParClient(@Param("x") Long mc);
}
