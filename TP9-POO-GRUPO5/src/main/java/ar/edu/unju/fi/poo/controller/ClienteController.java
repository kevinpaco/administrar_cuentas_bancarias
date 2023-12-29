package ar.edu.unju.fi.poo.controller;


import java.util.HashMap; 
 
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ClienteController {

	
	 @Autowired
	 ClienteService clienteService;
	 
	 @Autowired
	 EmailServiceImpl emailService;
	 
	 //Crear Cliente
	 @PostMapping("/clientes")
	 public ResponseEntity<?> guardarCliente(@RequestBody ClienteDto clienteDto) throws MessagingException {
		 Map<String, Object> response = new HashMap<String, Object>();
			try {		
				response.put("Objeto", clienteService.guardarCliente(clienteDto));
				response.put("Mensaje", "Objeto creado correctamente");		
		     	emailService.sendSimpleMessage(clienteDto.getEmail(),clienteDto.getNombre(), clienteDto.getContrasenia());				
			} catch (ModelException e) {
				response.put("Mensaje", "Error al guardar el objeto");
				response.put("Error", e.getMessage());
		  		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
	 
	 //Editar Cliente
	 @PutMapping("/clientes/{id}")
		public ResponseEntity<?> updateCliente(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
		 Map<String, Object> response = new HashMap<String, Object>();
			try {
				ClienteDto clienteDTO = clienteService.updateCliente(clienteDto, id);
				response.put("Objeto", clienteDTO);
				response.put("Mensaje", "El cliente se ha modificado correctamente");	
			} catch (ModelException e) {
				response.put("Error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} 		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	 //Borrar Cliente
	 @DeleteMapping("/clientes/{id}")
	 public ResponseEntity<?> borrarCliente(@PathVariable Long id){
		 Map<String, Object> response = new HashMap<String, Object>();
			try {
				ClienteDto clienteDto = clienteService.buscarCliente(id);
				clienteService.eliminarCliente(id);
				response.put("Objeto", clienteDto);
				response.put("Mensaje", "Cliente se ha borrado correctamente");
			} catch (ModelException e) {
				response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);	 
		 
}
	 
	 //Obtener todos los clientes
	 @GetMapping("/clientes/all")
	 public ResponseEntity<?> obtenerClientes(){
		 Map<String, Object> response = new HashMap<String, Object>();
			try {
				List<ClienteDto> listaClientes = clienteService.listClientes();
				
				response.put("Objeto", listaClientes);
				response.put("Mensaje", "Se ha obtenido la lista de clientes");

			} catch (ModelException e) {
				response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	 
	 }
	 //Obtener un cliente en especifico
      @GetMapping("/clientes/{dni}")
      public ResponseEntity<?> obtenerCliente(@PathVariable long dni){
 		 Map<String, Object> response = new HashMap<String, Object>();
 			try {
 				response.put("Objeto", clienteService.buscarClientebyDni(dni));
 				response.put("Mensaje", "Se ha obtenido el cliente solicitado");

 			} catch (ModelException e) {
 				response.put("Error", e.getMessage());
 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 			}
 		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	 
 	 }
      
	
		 
}

