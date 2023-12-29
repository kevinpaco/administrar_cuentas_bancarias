package ar.edu.unju.fi.poo.util;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.CuentaDto;
import ar.edu.unju.fi.poo.dto.MovimientoDto;
import ar.edu.unju.fi.poo.model.Cliente;
import ar.edu.unju.fi.poo.model.Cuenta;
import ar.edu.unju.fi.poo.model.Movimiento;

@Component
public class Conversion {

	ModelMapper mapper = new ModelMapper();

	public Cliente mapeoDtoAEntidadCliente(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		mapper.map(clienteDto, cliente);
		return cliente;
	}

	public ClienteDto mapeoEntidadClienteADto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		mapper.map(cliente, clienteDto);
		return clienteDto;
	}
	
	public CuentaDto mapeoEntidadCuentaADto(Cuenta cuenta) {
		CuentaDto cuentaDto = new CuentaDto();
		mapper.map(cuenta, cuentaDto);
		return cuentaDto;
	}
	
	public Cuenta mapeoDtoAEntidadCuenta(CuentaDto cuentaDto) {
		Cuenta cuenta = new Cuenta();
		mapper.map(cuentaDto, cuenta);
		return cuenta;
	}
	
	public List<ClienteDto> mapeoListaClientesToDto(List<Cliente> clientes)
	{
		return clientes
		.stream()
        .map(c -> mapper.map(c, ClienteDto.class))
        .collect(Collectors.toList());
	}
	
	public MovimientoDto mapeoDtoAEntidadMovimiento(Movimiento movimiento) {
	    MovimientoDto movimientoDto = new MovimientoDto();
		mapper.map(movimiento, movimientoDto);
		return movimientoDto;
	}
	
	public List<MovimientoDto> mapeoListaMovimientoToDto(List<Movimiento> movimientos)
	{
		List<MovimientoDto> movimientosDto=new ArrayList<MovimientoDto>();
		
	
		for(Movimiento e:  movimientos) {
			MovimientoDto movimientoDto=new MovimientoDto();
			mapper.map(e, movimientoDto);
			movimientoDto.setClienteDto( mapeoEntidadClienteADto(e.getCliente()));
			movimientoDto.setCuentaDto( mapeoEntidadCuentaADto(e.getCuenta()));
			movimientosDto.add(movimientoDto);
		}
		return movimientosDto;
	}
	
	
}
