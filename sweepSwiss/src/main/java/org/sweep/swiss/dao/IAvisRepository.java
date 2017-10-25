package org.sweep.swiss.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Avis;

public interface IAvisRepository extends JpaRepository<Avis, String>{

	@Query("select a from Avis a where a.nettoyeur.id =:x")
	public List<Avis> listeAvisParNettoyeur(@Param("x") Long idNet);
	@Query("select a from Avis a where a.client.id =:x")
	public List<Avis> listeAvisClient(@Param("x") Long idClient);
}
