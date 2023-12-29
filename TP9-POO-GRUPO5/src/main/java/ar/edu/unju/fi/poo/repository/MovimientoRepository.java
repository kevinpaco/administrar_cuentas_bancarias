package ar.edu.unju.fi.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{

}
