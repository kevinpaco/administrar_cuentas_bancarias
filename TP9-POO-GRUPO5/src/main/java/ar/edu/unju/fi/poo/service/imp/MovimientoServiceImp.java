package ar.edu.unju.fi.poo.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.ClienteDto;
import ar.edu.unju.fi.poo.dto.MovimientoDto;
import ar.edu.unju.fi.poo.exceptions.ModelException;
import ar.edu.unju.fi.poo.model.Cliente;
import ar.edu.unju.fi.poo.model.Movimiento;
import ar.edu.unju.fi.poo.repository.ClienteRepository;
import ar.edu.unju.fi.poo.repository.CuentaRepository;
import ar.edu.unju.fi.poo.repository.MovimientoRepository;
import ar.edu.unju.fi.poo.service.MovimientoService;
import ar.edu.unju.fi.poo.util.Conversion;
@Service
public class MovimientoServiceImp implements MovimientoService{
    
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	Conversion conversion; 
	@Autowired
	MovimientoRepository movimientoRepository;
	@Autowired
	CuentaRepository cuentaRepository;
	
	static final Logger logger = Logger.getLogger(MovimientoServiceImp.class);
	
	
	/**
	 * Metodo para realizar una extraccion
	 * @param importe, valor correspondiente a la extraccion
	 * @param cliente , cliente/adherente que realiza la extraccion
	 * @throws ModelException, ocurre cuando la extraccion es invalido
	 */
	@Override
	public MovimientoDto extraccion(double importe, ClienteDto cliente) throws ModelException  {
		 Cliente clienteEntity = clienteRepository.findByDni(cliente.getDni()).get();
		 Cliente clienteTitular = getClienteTitular(clienteEntity);
		 validarExtraccion(importe, conversion.mapeoEntidadClienteADto(clienteTitular));
		 Movimiento movimiento = new Movimiento(LocalDate.now(),LocalTime.now(),"Extraccion",importe,clienteEntity);
		 movimiento.setCuenta(clienteTitular.getCuenta());
		 movimiento.getCuenta().setSaldoActual( movimiento.getCuenta().getSaldoActual() - importe );
		 movimientoRepository.save(movimiento);
		 cuentaRepository.save(movimiento.getCuenta());
		 logger.info("Se ha realizado con exito la extraccion de " +importe +" del cliente con id" + cliente.getId());
		 logger.info("Posee un saldo restante de " + movimiento.getCuenta().getSaldoActual());
 		 return conversion.mapeoDtoAEntidadMovimiento(movimiento);
		
	}
	/**
	 * Metodo para realizar un deposito
	 * @param importe, valor correspondiente al deposito
	 * @param cliente , cliente/adherente quien realiza el deposito
	 * @throws ModelException, ocurre cuando la importe es invalido
	 */
	@Override
	public MovimientoDto deposito(double importe, ClienteDto cliente) throws ModelException {
		 if (BigDecimal.valueOf(importe).scale()>2)
		    	throw new ModelException("El importe solo acepta una maximo de dos decimales");
		 Cliente clienteEntity = clienteRepository.findByDni(cliente.getDni()).get();
		 Cliente clienteTitular = getClienteTitular(clienteEntity);
		 Movimiento movimiento = new Movimiento(LocalDate.now(),LocalTime.now(),"Deposito",importe,clienteEntity);
		 movimiento.setCuenta(clienteTitular.getCuenta());
		 movimiento.getCuenta().setSaldoActual( movimiento.getCuenta().getSaldoActual() + importe );
		 movimientoRepository.save(movimiento);
		 cuentaRepository.save(movimiento.getCuenta());
		 logger.info("Se ha realizado con exito el deposito de " +importe+ " del cliente con id " + cliente.getId());
		 logger.info("Posee un saldo restante de " + movimiento.getCuenta().getSaldoActual());
		 return conversion.mapeoDtoAEntidadMovimiento(movimiento);
		
		
	}
	
	private void validarExtraccion(double importe, ClienteDto cliente) throws ModelException {
	    if (BigDecimal.valueOf(importe).scale()>2)
	    	throw new ModelException("El importe solo acepta una maximo de dos decimales");
		if (importe > cliente.getCuenta().getSaldoActual())
			throw new ModelException("No tiene saldo suficiente, ingrese otro monto");
		if (importe > cliente.getCuenta().getLimiteExtraccion())
			throw new ModelException("El monto sobrepasa el limite de extraccion");
	    if(cliente.getEstado().equalsIgnoreCase("inhabilitado") == true)	
			throw new ModelException("Su usuario esta inhabilitado para operar. Consulte con su banco por favor");
	}
	/**
	 * Metodo para obtener el cliente titular de algun adherente/cliente
	 * @param clienteEntity, adherente/cliente a evaluar
	 * @return retorna el clienteTitular, en caso de ya ser un titular, se retorna a si mismo
	 */
	private Cliente getClienteTitular(Cliente clienteEntity)
	{
		 if(clienteEntity.getCuenta()==null)
			 	return clienteEntity.getTitular();
		 else
			 	return clienteEntity;
	}
	

}
