package ar.edu.unju.fi.poo.service;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.MovimientoDto;

public interface MovimientoService {
	public MovimientoDto extraccion(double importe, ClienteDto cliente);
	public MovimientoDto deposito(double importe, ClienteDto cliente);
}
