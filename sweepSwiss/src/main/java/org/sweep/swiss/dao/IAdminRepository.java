package org.sweep.swiss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sweep.swiss.entites.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Long>{

	@Query("SELECT a FROM Admin a WHERE a.email=:x")
	public Admin adminParIdentifiant(@Param("x") String mc);
}
