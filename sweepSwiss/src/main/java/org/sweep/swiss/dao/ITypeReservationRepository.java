package org.sweep.swiss.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sweep.swiss.entites.TypeReservation;

public interface ITypeReservationRepository extends JpaRepository<TypeReservation, Long>{

}
