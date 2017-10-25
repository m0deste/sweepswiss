package org.sweep.swiss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sweep.swiss.entites.Service;

public interface IServiceRepository extends JpaRepository<Service, Long>{

}
