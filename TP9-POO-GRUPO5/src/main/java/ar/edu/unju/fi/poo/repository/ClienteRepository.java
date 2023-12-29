package ar.edu.unju.fi.poo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.model.Cliente;

@Repository	
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByDni(long dni);
	
	public List<Cliente> findByNombre(String nombre);
	
	public Optional<Cliente> findByEmail(String email);

}
