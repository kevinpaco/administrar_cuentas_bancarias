package ar.edu.unju.fi.poo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.service.ClienteService;
import ar.edu.unju.fi.poo.service.imp.EmailServiceImpl;

@RestController
@RequestMapping("/api/banco")
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class AdherenteController {
	
	 @Autowired
	 ClienteService clienteService;
	 
	 @Autowired
	 EmailServiceImpl emailService;
	 
	 
	 
	 @PostMapping("/clientes/adherente/{id}")
	 public ResponseEntity<?> guardarAdherente(@RequestBody ClienteDto adherente,@PathVariable(name="id") long id) throws Throwable {
		 Map<String, Object> response = new HashMap<String, Object>();
		   try {
			    ClienteDto clienteTitular = clienteService.buscarCliente(id);
				response.put("Objeto", clienteService.crearAdherente(adherente,clienteTitular));
				response.put("Mensaje", "Se agrego al adherente correctamente");		
			    emailService.sendSimpleMessage(adherente.getEmail(),adherente.getNombre(), adherente.getContrasenia());				
			} catch (ModelException e) {
				response.put("Mensaje", "Error al guardar el adherente");
				response.put("Error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
	 
	 @PutMapping("/clientes/adherente")
	 public ResponseEntity<?> editarAdherente(@RequestBody ClienteDto adherente)
	 {
		 Map<String, Object> response = new HashMap<String, Object>();
		 try {
		     ClienteDto clienteBuscado = clienteService.buscarClientebyDni(adherente.getDni());
				response.put("Objeto", clienteService.updateAdherente(clienteBuscado,adherente));
				response.put("Mensaje", "Se Edito al adherente correctamente");						
			} catch (ModelException e) {
				response.put("Mensaje", "Error al guardar el adherente");
				response.put("Error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		 
	 }
	 
	 

	
}
