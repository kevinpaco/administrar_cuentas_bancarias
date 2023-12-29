package ar.edu.unju.fi.poo.service;

import java.util.List;

import ar.edu.unju.fi.poo.dto.CuentaDto;

public interface CuentaService {


	public void guardarCuenta(CuentaDto cuentaDto);
	
	public CuentaDto buscarCuenta(Long id);
	
	public List<CuentaDto> listarCuenta();
	
	public void eliminarCuenta(Long id);

}
