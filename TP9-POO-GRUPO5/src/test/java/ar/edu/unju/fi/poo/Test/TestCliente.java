package ar.edu.unju.fi.poo.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.CuentaDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.service.ClienteService;

@SpringBootTest
class TestCliente {

	@Autowired
	ClienteService clienteService;
	
	static ClienteDto cliente1;
	static ClienteDto cliente2;
	static ClienteDto cliente3;
	static CuentaDto cuenta1;
	static CuentaDto cuenta2;
    static CuentaDto cuenta3;
    static ClienteDto adherente1; 
    static ClienteDto adherente2; 
    final static Logger logger = Logger.getLogger(TestCliente.class);
    
    @BeforeEach
	void setUp() throws Exception {
    	logger.debug("Inicializacion de variables para pruebas unitarias");
    	cliente1 = new ClienteDto();
    	cliente2 = new ClienteDto();
    	cliente3 = new ClienteDto();
 
    	cuenta1 = new CuentaDto();
    	cuenta1.setEstado("prueba");
    	cuenta1.setNumeroCuenta(21334);
    	    	
    	cuenta2 = new CuentaDto();
    	cuenta2.setEstado("habilitado");
    	cuenta2.setNumeroCuenta(21335);
    	cuenta2.setFechaIngreso(LocalDate.now());
    	cuenta2.setLimiteExtraccion(2000d);
    	cuenta2.setSaldoActual(10000d);
    	
    	cuenta3 = new CuentaDto();
    	cuenta3.setEstado("habilitado");
    	cuenta3.setNumeroCuenta(2312);
    	cuenta3.setFechaIngreso(LocalDate.now());
    	cuenta3.setLimiteExtraccion(1000d);
    	cuenta3.setSaldoActual(20000d);
  
    	
    	cliente1.setDomicilio("Cayetano 969, Palpala");
    	cliente1.setEmail("gabriel@gmail.com");
    	cliente1.setEstado("inhabilitado");
    	cliente1.setNombre("Gabriel Molina");
    	cliente1.setDni(35557261);
    	cliente1.setCuenta(cuenta1);
    	
    	
    	cliente2.setDni(40557261);
    	cliente2.setDomicilio("Cayetano 100, Palpala");
    	cliente2.setEmail("matias@gmail.com");
    	cliente2.setEstado("habilitado");
    	cliente2.setNombre("Matias Rodriguez");
    	cliente2.setCuenta(cuenta2);

    	
    	cliente3.setDni(38111261);
    	cliente3.setDomicilio("España 800, Palpala");
    	cliente3.setEmail("marcos@gmail.com");
    	cliente3.setEstado("Activo");
    	cliente3.setNombre("Marcos Perez");
    	cliente3.setCuenta(cuenta3);
    	
       
        
        adherente1 = new ClienteDto(); 
        adherente1.setDni(50231261);
    	adherente1.setDomicilio("España 900, Palpala");
    	adherente1.setEmail("juan@gmail.com");
    	adherente1.setEstado("habilitado");
    	adherente1.setNombre("Juan Perez");
    	//adherente1.setCuenta(null);
    	//adherente1.setAdherentes(null);
    	
    	adherente2 = new ClienteDto(); 
    	adherente2.setDni(21331261);
    	adherente2.setDomicilio("Italia 900, Ledesma");
    	adherente2.setEmail("omar@gmail.com");
    	adherente2.setEstado("inhabilitado");
    	adherente2.setNombre("Omar Cruz");
    	adherente2.setCuenta(null);
   

	}

	@AfterEach
	void tearDown() throws Exception {
		cliente1 = null;
		cliente2 = null;
		cliente3 = null;
		cuenta1 = null;
		cuenta2 = null;
		cuenta3 = null;
		adherente1 = null; 
		adherente2 = null; 
	}
    
    //funciona
	@Test
    @DisplayName("Guardar Clientes sin adherentes")
	@Disabled
	void guardarClienteTest() {
		    logger.debug("Creando adherente...");
			long listaTotal = clienteService.getNumeroDeRegistros();
			cliente1 = clienteService.guardarCliente(cliente1);
			cliente2 = clienteService.guardarCliente(cliente2);
			cliente3 = clienteService.guardarCliente(cliente3);
			assertEquals(clienteService.getNumeroDeRegistros(), listaTotal+3);
	}

	@Test
    @DisplayName("Guardar Clientes con adherentes")
	@Disabled
	void guardarAdherenteTest() {
		logger.info("Creando adherente...");
		long listaTotal = clienteService.getNumeroDeRegistros();
		cliente1 = clienteService.guardarCliente(cliente1);
		adherente1 = clienteService.crearAdherente(adherente1, cliente1);
		adherente2 = clienteService.crearAdherente(adherente2, cliente1);
		logger.info("Adherente creado");
		//el cliente guardado tiene 2 adherentes, en la tabla se debe esperar en total 3 nuevos registros
		assertEquals(clienteService.getNumeroDeRegistros(), listaTotal+3);
		
	}
	
	@Test
	@DisplayName("Borrar Cliente")
	@Disabled
	void borrarClienteTest() {
		cliente1 = clienteService.guardarCliente(cliente1);
		cliente2 = clienteService.guardarCliente(cliente2);
		cliente3 = clienteService.guardarCliente(cliente3);
		clienteService.eliminarCliente(1L);
		clienteService.eliminarCliente(2L);
		assertEquals(1,clienteService.getNumeroDeRegistros());
		
		
	} 
    
   
	
	@Test
	@DisplayName("Habilitar Adherente")
	@Disabled
	void habilitarAdherente() {
	
		 cliente2 = clienteService.guardarCliente(cliente2); 
		 adherente2 = clienteService.crearAdherente(adherente2, cliente2);
		 assertEquals("inhabilitado",adherente2.getEstado());
		 logger.info("Estado actual: inhabilitado");
		 adherente2 = clienteService.habilitarAdherente(adherente2);
		 assertEquals("habilitado",adherente2.getEstado());
		 logger.info("Estado actualizado: Habilitado");
	}
	
	@Test
	@DisplayName("Inhabilitar Adherente")
	@Disabled
	void inhabilitarAdherente() {
		
		cliente1 = clienteService.guardarCliente(cliente1); 
		 adherente1 = clienteService.crearAdherente(adherente1, cliente1);
		 assertEquals("habilitado",adherente1.getEstado());
		 logger.info("Estado actual: habilitado");
		 adherente1 = clienteService.inhabilitarAdherente(adherente1);
		 assertEquals("inhabilitado",adherente1.getEstado() );
		 logger.info("Estado actualizado: Inhabilitado");
	
	}
	
	
	@Test
	@DisplayName("Modificar Adherente")
	@Disabled
	void modificarAdherente() {
	cliente1 = clienteService.guardarCliente(cliente1);
	adherente1 = clienteService.crearAdherente(adherente1,cliente1); 
	ClienteDto clienteEncontrado = clienteService.buscarClientebyDni(adherente1.getDni());
	assertEquals("juan@gmail.com",adherente1.getEmail());
	assertEquals("Juan Perez",adherente1.getNombre());
	adherente1.setEmail("nuevoCorreo@gmail.com");
	adherente1.setNombre("Pedro Martinez");
	clienteEncontrado = clienteService.updateAdherente(clienteEncontrado, adherente1);
	assertEquals("nuevoCorreo@gmail.com",clienteEncontrado.getEmail());
	assertEquals("Pedro Martinez",clienteEncontrado.getNombre());
	 logger.info("Adherente Modificado");

	}
	
	
	@Test
	@DisplayName("Habilitar Cliente")
	@Disabled
	void habilitarCliente() {
		 cliente1 = clienteService.guardarCliente(cliente1); 
		 assertEquals("inhabilitado",cliente1.getEstado());
		 logger.info("Estado actual: inhabilitado");
		 cliente1 = clienteService.habilitarAdherente(cliente1);
		 assertEquals("habilitado",cliente1.getEstado());	
		 logger.info("Estado actualizado: habilitado");
	}
	
	@Test
	@DisplayName("Inhabilitar Cliente")
	@Disabled
	void inhabilitarCLiente() {
	 cliente2 = clienteService.guardarCliente(cliente2); 
	 assertEquals( "habilitado",cliente2.getEstado());
	 logger.info("Estado actual: habilitado");
	 cliente2 = clienteService.inhabilitarAdherente(cliente2);
	 assertEquals( "inhabilitado",cliente2.getEstado());
	 logger.info("Estado actualizado: inhabilitado");
	
	}
	
	@Test
	@DisplayName("Modificar Cliente")
	@Disabled
	void modificarCliente() {
	 cliente1 = clienteService.guardarCliente(cliente1); 
	 ClienteDto clienteEncontrado = clienteService.buscarClientebyDni(cliente1.getDni());
	 assertEquals("gabriel@gmail.com",clienteEncontrado.getEmail());
	 clienteEncontrado.setEmail("nuevoCorreo@gmail.com");
	 clienteEncontrado.setDomicilio("Italia 900");
	 clienteEncontrado = clienteService.updateCliente(clienteEncontrado, cliente1.getId());
	 assertEquals( "nuevoCorreo@gmail.com",clienteEncontrado.getEmail());
	 assertEquals("Italia 900",clienteEncontrado.getDomicilio() );
	}
	
	
  @Test
  @DisplayName("Buscar Cliente por nombre")
  @Disabled
  void buscarClienteByNombre() {
	  logger.info("Buscar cliente por nombre");
	cliente1 =   clienteService.guardarCliente(cliente1);
    cliente2 = clienteService.guardarCliente(cliente2);
      List<ClienteDto> clientesDto = clienteService.buscarClienteByNombre("Gabriel Molina");
      assertEquals(1,clientesDto.size());
  }
	
	
    
    
    
    
}