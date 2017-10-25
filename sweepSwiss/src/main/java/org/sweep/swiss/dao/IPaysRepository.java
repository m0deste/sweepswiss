package org.sweep.swiss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sweep.swiss.entites.Pays;

public interface IPaysRepository extends JpaRepository<Pays, Long>{

}
