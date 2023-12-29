package ar.edu.unju.fi.poo.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.time.LocalDate;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.CuentaDto;
import ar.edu.unju.fi.poo.service.ClienteService;
import ar.edu.unju.fi.poo.service.MovimientoService;
import ar.edu.unju.fi.poo.service.imp.GenerarPdfImp;

@SpringBootTest
class TestMovimiento {

	@Autowired
	MovimientoService movimientoService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	GenerarPdfImp genera;
	
	static ClienteDto cliente1;
	static ClienteDto adherente;
	static CuentaDto cuenta1;
    final static Logger logger = Logger.getLogger(TestCliente.class);
    
    @BeforeEach
	void setUp() throws Exception {
    	logger.debug("Inicializacion de variables para pruebas unitarias");
    	
    	cuenta1 = new CuentaDto();
    	cuenta1.setEstado("habilitado");
    	cuenta1.setNumeroCuenta(21335);
    	cuenta1.setFechaIngreso(LocalDate.now());
    	cuenta1.setLimiteExtraccion(2000d);
    	cuenta1.setSaldoActual(10000d);
    	
    	cliente1 = new ClienteDto();
    	cliente1.setDomicilio("Cayetano 969, Palpala");
    	cliente1.setEmail("gabriel@gmail.com");
    	cliente1.setEstado("habilitado");
    	cliente1.setNombre("Gabriel Molina");
    	cliente1.setDni(35557261);
    	cliente1.setCuenta(cuenta1);
    	
    	
    	
    	adherente = new ClienteDto();
    	adherente.setDomicilio("Cayetano 969, Palpala");
    	adherente.setEmail("gdsaiel@gmail.com");
    	adherente.setEstado("habilitado");
    	adherente.setNombre("Gabriel Molina");
    	adherente.setDni(35553261);
    
    	
    	

	}

	@AfterEach
	void tearDown() throws Exception {
		cliente1 = null;
		cuenta1 = null;
		adherente = null; 
	}
    
    
	
	@Test
	@DisplayName("Extraccion De Dinero")
	@Disabled
	void extraerDinero() {
		logger.info("Extraer dinero");
		 try {	
		 cliente1 = clienteService.guardarCliente(cliente1); 
		 adherente = clienteService.crearAdherente(adherente, cliente1);
		 movimientoService.extraccion(200d, cliente1);
		 movimientoService.extraccion(100d, cliente1);
		 movimientoService.extraccion(50d, adherente);
		 cliente1 = clienteService.buscarClientebyDni(cliente1.getDni());
		 assertEquals(9650d, cliente1.getCuenta().getSaldoActual());
		 }catch(ModelException obj) {                     
	         obj.printStackTrace();
	         logger.error("Error: " + obj.getMessage());
		 }
	}
	
	
	@Test
	@DisplayName("Deposito De Dinero")
	void depositarDinero() {
		logger.info("Depositar dinero");
		 try {	
			 cliente1 = clienteService.guardarCliente(cliente1); 
			 adherente = clienteService.crearAdherente(adherente, cliente1);
			 movimientoService.deposito(200d, cliente1);
			 movimientoService.deposito(100d, cliente1);
			 movimientoService.deposito(50d, adherente);
			 cliente1 = clienteService.buscarClientebyDni(cliente1.getDni());
			 assertEquals(10350d, cliente1.getCuenta().getSaldoActual());
			 }catch(ModelException obj) {                     
		         obj.printStackTrace();
		         logger.error("Error: " + obj.getMessage());
			 }
		
		
	
	
	}
}



	
	
	
    
    