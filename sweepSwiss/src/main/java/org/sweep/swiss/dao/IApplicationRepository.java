package org.sweep.swiss.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.sweep.swiss.entites.Application;

public interface IApplicationRepository extends JpaRepository<Application, Long>{

}
