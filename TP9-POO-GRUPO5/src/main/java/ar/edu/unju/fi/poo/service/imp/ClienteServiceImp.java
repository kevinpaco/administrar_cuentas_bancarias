package ar.edu.unju.fi.poo.service.imp;

import java.util.List; 

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.CuentaDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.model.Cliente;
import ar.edu.unju.fi.poo.model.Cuenta;
import ar.edu.unju.fi.poo.repository.ClienteRepository;
import ar.edu.unju.fi.poo.repository.CuentaRepository;
import ar.edu.unju.fi.poo.service.ClienteService;
import ar.edu.unju.fi.poo.util.Conversion;

@Service
public class ClienteServiceImp implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private Conversion conversion;

	 static final Logger logger = Logger.getLogger(ClienteServiceImp.class);
	
	
	
	ModelMapper mapper = new ModelMapper(); 
	
	/**
	 * Metodo para crear un cliente
	 * @param clienteDto, el cliente a crear debe cumplir con ciertas normas 
	 */
	@Override
	public ClienteDto guardarCliente(ClienteDto clienteDto) throws ModelException{
		validarCliente(clienteDto);
		int length = 8;
		String contrasenia = RandomStringUtils.randomAlphanumeric(length);
	    clienteDto.setContrasenia(contrasenia);
		Cliente cliente= conversion.mapeoDtoAEntidadCliente(clienteDto);
		clienteRepository.save(cliente);
		logger.info("Se creo un cliente nuevo con id "+clienteDto.getId());
		logger.info("Gracias"+ clienteDto.getNombre() +"por ser parte de nuestro banco");
		clienteDto = conversion.mapeoEntidadClienteADto(cliente);
		return clienteDto;
	}
 
	/**
	 * Metodo para buscar un cliente, caso contrario lanza una excepcion
	 * @param id , pertenece al cliente que se busca
	 */
	@Override
	public ClienteDto buscarCliente(Long id) throws ModelException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new  ModelException("No existe el cliente"));
		return conversion.mapeoEntidadClienteADto(cliente);
	}
	
	/**
	 * Metodo para editar un cliente, solo se mapea los datos permitidos a editar,
	 * no podra modificar los parametros de su cuenta, dni 
	 * @param clienteDto , cliente con datos nuevos
	 * @param id , el id del cliente a editar
	 * @exception ModelException, ocurre al no encontrar el cliente a editar
	 */
	@Override
	public ClienteDto updateCliente(ClienteDto clienteDto, Long id)  throws ModelException  {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new  ModelException("No existe el cliente"));
		cliente.setDomicilio(clienteDto.getDomicilio());
		cliente.setEmail(clienteDto.getDomicilio());
		cliente.setEstado(clienteDto.getEstado());
		cliente.setNombre(clienteDto.getNombre());
	    clienteRepository.save(cliente); 
		logger.info("Se edito un cliente con dni: "+ cliente.getDni());
	    clienteDto = conversion.mapeoEntidadClienteADto(cliente);	 
	    
		return clienteDto;
	
	}
	
	
    /**
     * Metodo para obtener una lista con todos los clientes registrados
     * @exception ModelException, ocurre al no encontrar ningun cliente registrado;
     */
	@Override
	public List<ClienteDto> listClientes() throws ModelException {
		List<ClienteDto> listClienteDto= conversion.mapeoListaClientesToDto(clienteRepository.findAll());
		if (listClienteDto.isEmpty())
			throw new ModelException("No existen clientes registrados");
		return listClienteDto;
	}
	

	/**
	 * Metodo para eliminar (eliminacion no logica) a un cliente
	 * @param id , identificador del cliente a eliminar
	 * @exception ModelException, ocurre al no encontrar un cliente con el id solicitado
	 */
	@Override
	public ClienteDto eliminarCliente(Long id)throws ModelException {
		
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new  ModelException("No existe el cliente"));
        clienteRepository.deleteById(id);
        logger.info("Se elimino un cliente con dni: "+ cliente.getDni());
        return conversion.mapeoEntidadClienteADto(cliente);
	}

	/**
	 * Metodo para habilitar un Adherente
	 * @param adherente , adherente a modificar estado
	 */
	@Override
	public ClienteDto habilitarAdherente(ClienteDto adherente) {
		
		adherente.setEstado("habilitado");
		Cliente cliente = conversion.mapeoDtoAEntidadCliente(adherente);
		clienteRepository.save(cliente);
		logger.info("Se habilito un cliente con dni: "+ cliente.getDni());
		return conversion.mapeoEntidadClienteADto(cliente);
		
	}


	/**
	 * Metodo para inhabilitar un Adherente
	 * @param adherente , adherente a modificar estado
	 */
	@Override
	public ClienteDto inhabilitarAdherente(ClienteDto adherente) {
		
		adherente.setEstado("inhabilitado");
		Cliente cliente = conversion.mapeoDtoAEntidadCliente(adherente);
		clienteRepository.save(cliente);
		logger.info("Se inhabilito un cliente con dni: "+ cliente.getDni());
		return conversion.mapeoEntidadClienteADto(cliente);
		
	}


	/**
	 * Metodo para crear un Adherente
	 * @param adherente, contiene los datos del adherente a crear 
	 * @param titular, el titular al cual se adhiere el nuevo adherente
	 * @exception ModelException, ocurre al intentar crear un adherente con datos invalidos (ej: con una cuenta)
	 */
	@Override
	public ClienteDto crearAdherente(ClienteDto adherente,ClienteDto titular) throws ModelException {

		validarAdherente(adherente);
		int length = 8;
		String contrasenia = RandomStringUtils.randomAlphanumeric(length);
	    adherente.setContrasenia(contrasenia);
		Cliente titularEntity = conversion.mapeoDtoAEntidadCliente(titular);
		Cliente adherenteE = conversion.mapeoDtoAEntidadCliente(adherente);
		adherenteE.setTitular(titularEntity);
		clienteRepository.save(adherenteE);
		logger.info("Se creo un adherente con dni: "+ adherente.getDni()+" adherido al cliente con dni: "+ titular.getDni());
		return adherente;
		
	}
	
	/**
	 * Metodo para editar un Adherente, solo se mapea los datos permitidos a editar
	 * no podra agregar una cuenta, cambiar dni 
	 * @param adherente , adherentes con datos nuevos
	 * @param clienteDtoEditado , es adherente registrado actualmente
	 * 
	 */
	@Override
	public ClienteDto updateAdherente(ClienteDto adherente, ClienteDto clienteDtoEditado)
	{
		adherente.setDomicilio(clienteDtoEditado.getDomicilio());
		adherente.setEmail(clienteDtoEditado.getDomicilio());
		adherente.setEstado(clienteDtoEditado.getEstado());
		adherente.setNombre(clienteDtoEditado.getNombre());
        Cliente adherenteEntity = conversion.mapeoDtoAEntidadCliente(adherente);
        clienteRepository.save(adherenteEntity);
        logger.info("Se edito el adherente con dni: "+ adherente.getDni());
        return conversion.mapeoEntidadClienteADto(adherenteEntity);
	}
	
	/**
	 * Metodo para buscar un Cliente/Adherente por dni
	 * @param dni 
	 * @exception ModelException, ocurre al no encontrar el cliente/adherente 
	 */
	@Override
	public ClienteDto buscarClientebyDni(long dni)  throws ModelException {
		Cliente clienteBuscado= clienteRepository.findByDni(dni).orElseThrow(() -> new  ModelException("No existe el cliente"));
		return conversion.mapeoEntidadClienteADto(clienteBuscado);
	}
	
	/**
	 * Metodo para obtener los Clientes/Adherentes con el mismo nombre
	 * @param nombre
	 */
	@Override
	public List<ClienteDto> buscarClienteByNombre(String nombre) {
		List<Cliente> clientes = clienteRepository.findByNombre(nombre);
		return mapper.map(clientes, new TypeToken<List<ClienteDto>>() {}.getType());
	}

	/**
	 * Metodo para buscar un Cliente/Adherente por email
	 * @param email 
	 */
	@Override
	public ClienteDto buscarClienteByEmail(String email) {	
	 Cliente clienteBuscado = clienteRepository.findByEmail(email).orElse(null);
		 if (clienteBuscado !=null ) {  
	        return conversion.mapeoEntidadClienteADto(clienteBuscado);
	        }
	 	 else {
	 				return null; 
	 		  }
	}
	
	/**
	 * Metodo para buscar un Cliente/Adherente por dni, para verificar su existencia
	 * @param dni 
	 * @exception ModelException, ocurre al no encontrar el cliente/adherente 
	 */
	private ClienteDto buscarClieneExistente(long dni) {	
		Cliente clienteBuscado= clienteRepository.findByDni(dni).orElse(null);
		 if (clienteBuscado !=null ) {  
			   return conversion.mapeoEntidadClienteADto(clienteBuscado);
		}else {
				return null; 
			 }
	    }
	
	
/**
 * Metodo para evaluar un cliente, este tendra ciertas restricciones
 * @param clienteDto, cliente a evaluar
 * @throws ModelException, si no cumple ocurrira una excepcion con el mensaje correspondiente
 */
	private void validarCliente(ClienteDto clienteDto) throws ModelException {		
		if (buscarClieneExistente(clienteDto.getDni()) != null)
		    throw new ModelException("Cliente existente");		
		if (clienteDto.getCuenta() == null) 
			throw new ModelException("Se esta intentando añadir un adherente");
		if (buscarNroCuenta(clienteDto.getCuenta().getNumeroCuenta()) != null)
			throw new ModelException("Cuenta existent, por favor genere otra");
		if (buscarClienteByEmail(clienteDto.getEmail()) != null)
			throw new ModelException("Email ya registrado");
		}
	
	/**
	 * Metodo para evaluar un adherente, este tendra ciertas restricciones
	 * @param clienteDto, cliente a evaluar
	 * @throws ModelException, si no cumple ocurrira una excepcion con el mensaje correspondiente
	 */
	private void validarAdherente(ClienteDto clienteDto) throws ModelException {		
		if (buscarClieneExistente(clienteDto.getDni()) != null)
		    throw new ModelException("Adherente existente");		
		if (clienteDto.getCuenta() != null) 
			throw new ModelException("Se esta intentando añadir un Cliente");
		if (buscarClienteByEmail(clienteDto.getEmail()) != null)
			throw new ModelException("Email ya registrado");
		}
	
	/**
	 * Metodo para buscar una Cuenta por numero de cuenta, para verificar su existencia
	 * @param numero  
	 */
	private CuentaDto buscarNroCuenta(int numero) {
		Cuenta cuentaBuscado= cuentaRepository.findByNumero(numero).orElse(null);
		 if (cuentaBuscado !=null ) {  
			   return conversion.mapeoEntidadCuentaADto(cuentaBuscado);
		}else {
				return null; 
			 }
	    }

	
	
	@Override
	public long getNumeroDeRegistros()
	{
		return clienteRepository.findAll().size(); 
	}
	
	@Override
	public ClienteDto getTitularCuenta(ClienteDto cliente)
	{
		Cliente clienteEntity = clienteRepository.findByDni(cliente.getDni()).get();
		if(clienteEntity.getCuenta() == null)
              return conversion.mapeoEntidadClienteADto(clienteEntity.getTitular());
		else
			 return conversion.mapeoEntidadClienteADto(clienteEntity);
	}

	
}


