package org.sweep.swiss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sweep.swiss.entites.Canton;

public interface ICantonRepository extends JpaRepository<Canton, Long>{

}
