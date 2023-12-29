package ar.edu.unju.fi.poo.service;

import java.util.List;

import ar.edu.unju.fi.poo.dto.ClienteDto;

public interface ClienteService {

	public ClienteDto guardarCliente(ClienteDto clienteDto);

	public ClienteDto buscarCliente(Long id);
	
	public ClienteDto updateCliente (ClienteDto clienteDto, Long id);
	
	public List<ClienteDto> listClientes();
	
	public ClienteDto eliminarCliente(Long id);
	
	public ClienteDto habilitarAdherente(ClienteDto adherente);
	
	public ClienteDto inhabilitarAdherente(ClienteDto adherente);
	
	public ClienteDto crearAdherente(ClienteDto adherente,ClienteDto titular);
	public ClienteDto updateAdherente(ClienteDto adherente, ClienteDto clienteDtoEditado);
	
	public ClienteDto buscarClientebyDni(long dni);
	
	public ClienteDto buscarClienteByEmail(String email);
	
	public List<ClienteDto> buscarClienteByNombre(String nombre);
	
	public long getNumeroDeRegistros();
	
	public ClienteDto getTitularCuenta(ClienteDto cliente);

	}
		
	

