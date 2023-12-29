package ar.edu.unju.fi.poo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.service.ClienteService;
import ar.edu.unju.fi.poo.service.imp.ExcelServiceImp;
import ar.edu.unju.fi.poo.service.imp.GenerarPdfImp;

@RestController
@RequestMapping("/api/banco")
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ReporteController {
	 @Autowired
	 GenerarPdfImp generarPdf;
	 @Autowired
	 ClienteService clienteService; 
	 @Autowired
	 ExcelServiceImp excelService;
	 
		
	 
	
 
	
	 
	 //Obtener un Pdf con los ultimos 20 movimientos de un cliente/adherente
	 @GetMapping("/pdf/movimientos/{dni}")
	 public ResponseEntity<?> generarPdfMovimientos(@PathVariable long dni){
		 Map<String, Object> response = new HashMap<String, Object>();
		 try {
		 
		  generarPdf.generarPdfMovimientos(dni);
		  
		 }catch (ModelException e) {
				response.put("Mensage: ",e.getMessage());	
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (NullPointerException e) {
			response.put("Mensage: ",e.getMessage());	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch ( MalformedURLException e) {
			response.put("Mensage", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	 }
	 
	//Obtener un Pdf de una cuenta
	 @GetMapping("/pdf/{dni}")
	 public ResponseEntity<?> generarPdf(@PathVariable long dni){
		 Map<String, Object> response = new HashMap<String, Object>();
		 try {
		  ClienteDto buscarCliente =  clienteService.buscarClientebyDni(dni);
		  ClienteDto titularCuenta = clienteService.getTitularCuenta(buscarCliente); 
		  generarPdf.generarPdf(titularCuenta);
		  
		 }catch (ModelException e) {
				response.put("Mensage: ",e.getMessage());	
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (NullPointerException e) {
			response.put("Mensage: ",e.getMessage());	
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch ( MalformedURLException e) {
			response.put("Mensage", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	 }
	 
	 //Excel
	 @GetMapping("/excel/{dni}/{desde}/{hasta}")
	 public void exportToExcel( @PathVariable long dni, @PathVariable String desde,  @PathVariable String hasta, HttpServletResponse response) throws ModelException, IOException {
	        response.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=Movimientos_Informacion"+ dni + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        excelService.exportCustomerToExcel(dni,response,LocalDate.parse(desde),LocalDate.parse(hasta));
	 }
}
