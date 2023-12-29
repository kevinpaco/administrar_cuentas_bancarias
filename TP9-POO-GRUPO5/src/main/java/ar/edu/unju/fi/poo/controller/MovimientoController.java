package ar.edu.unju.fi.poo.controller;

import java.util.HashMap;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.service.ClienteService;
import ar.edu.unju.fi.poo.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/banco")
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class MovimientoController {
       
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	MovimientoService movimientoService;
	
	
	@PostMapping("/movimientos/extracciones")
	@ResponseBody
	public ResponseEntity<?> extraerDinero(@RequestParam(required=false)double importe, @RequestParam(required=false)long dni) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			ClienteDto buscarCliente=clienteService.buscarClientebyDni(dni);
			movimientoService.extraccion(importe, buscarCliente);
		    response.put("Extrajo dinero el cliente con cuenta:", buscarCliente);
		}catch (ModelException e) {
			response.put("Mensaje", "Error al realizar la extraccion");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/movimientos/depositos")
	@ResponseBody
	public ResponseEntity<?> depositarDinero(@RequestParam(required=false)double importe, @RequestParam(required=false)long dni) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
		ClienteDto buscarCliente= clienteService.buscarClientebyDni(dni);
		movimientoService.deposito(importe, buscarCliente);
		response.put("Deposito dinero el cliente con cuenta", buscarCliente);
		}catch (ModelException e) {
			response.put("Mensage: ","No se realizo el depositvo");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
}
