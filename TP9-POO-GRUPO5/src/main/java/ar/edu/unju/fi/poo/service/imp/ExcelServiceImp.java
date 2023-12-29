package ar.edu.unju.fi.poo.service.imp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.MovimientoDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.model.Cliente;
import ar.edu.unju.fi.poo.model.Movimiento;
import ar.edu.unju.fi.poo.repository.ClienteRepository;
import ar.edu.unju.fi.poo.repository.MovimientoRepository;
import ar.edu.unju.fi.poo.util.Conversion;
import ar.edu.unju.fi.poo.util.ExcelExportUtils;

@Service
public class ExcelServiceImp {

	ModelMapper mapper= new ModelMapper();
	
	static final Logger logger = Logger.getLogger(ExcelServiceImp.class);
	
	@Autowired
	MovimientoRepository movimientoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private Conversion conversion;
	
	
	public List<MovimientoDto> exportCustomerToExcel(long dni,HttpServletResponse response,LocalDate desde, LocalDate hasta) throws IOException, ModelException {
		Cliente cliente = clienteRepository.findByDni(dni).orElseThrow(() -> new  ModelException("No existe el cliente"));
		ClienteDto clienteDto= conversion.mapeoEntidadClienteADto(cliente);
	    List<Movimiento> movimientos = cliente.getMovimientos();
	    List<MovimientoDto> movimientosDto =  conversion.mapeoListaMovimientoToDto(movimientos);
	    List<MovimientoDto> movimientosFechaDto=movimientosDtoFecha(movimientosDto,desde,hasta);   
	    ExcelExportUtils exportUtils = new ExcelExportUtils(movimientosFechaDto, clienteDto);
        exportUtils.exportDataToExcel(response);
        logger.info("Se exporto de forma los movimientos del cliente " + clienteDto.getNombre());
        return movimientosDto;
}
	
	private List<MovimientoDto>movimientosDtoFecha(List<MovimientoDto> movimientoDto, LocalDate desde, LocalDate hasta){
		List<MovimientoDto> movimientoDtoFecha = new ArrayList<MovimientoDto>(); 
		for (MovimientoDto m : movimientoDto) {
			  if (m.getFecha().compareTo(desde) >=0 && m.getFecha().compareTo(hasta) <=0) {
				  movimientoDtoFecha.add(m);
			  }
		  }
		return movimientoDtoFecha;
	}
	
	
}