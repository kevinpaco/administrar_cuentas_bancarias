package ar.edu.unju.fi.poo.service.imp;

import java.net.MalformedURLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.model.Cliente;
import ar.edu.unju.fi.poo.repository.ClienteRepository;
import ar.edu.unju.fi.poo.util.ReporteMovimientoPdf;
import ar.edu.unju.fi.poo.util.SaldoPdf;

@Service
public class GenerarPdfImp {
       
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public void generarPdf(ClienteDto clienteDto )throws ModelException, MalformedURLException {
		
		SaldoPdf.generarPdf(clienteDto.getNombre(),clienteDto.getCuenta().getNumeroCuenta(),clienteDto.getId(),clienteDto.getCuenta().getFechaIngreso(), clienteDto.getCuenta().getEstado(),clienteDto.getCuenta().getSaldoActual());

	}
	
	public void generarPdfMovimientos(long dni)throws ModelException, MalformedURLException {
	   Cliente cliente = clienteRepository.findByDni(dni).orElseThrow(  ()-> new ModelException("No se encontro el cliente"));
	   ReporteMovimientoPdf.generarPdf(cliente);
	}
}
