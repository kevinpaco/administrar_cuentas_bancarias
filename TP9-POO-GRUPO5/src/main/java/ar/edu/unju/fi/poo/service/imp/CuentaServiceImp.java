package ar.edu.unju.fi.poo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.dto.CuentaDto;
import ar.edu.unju.fi.poo.model.Cuenta;
import ar.edu.unju.fi.poo.repository.CuentaRepository;
import ar.edu.unju.fi.poo.service.CuentaService;

@Service
public class CuentaServiceImp implements CuentaService {
  ModelMapper mapper= new ModelMapper();
	
	
	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Override
	public void guardarCuenta(CuentaDto cuentaDto) {
		Cuenta cuenta=new Cuenta();
		mapper.map(cuentaDto, cuenta);
		cuentaRepository.save(cuenta);
	}

	@Override
	public CuentaDto buscarCuenta(Long id) {

		Cuenta cuenta = cuentaRepository.findById(id).get();
		
		CuentaDto cuentaDto=new CuentaDto();
	    mapper.map(cuenta , cuentaDto );
	    
	    return cuentaDto;
	}

	@Override
	public List<CuentaDto> listarCuenta() {
		List<CuentaDto> listCuentaDto=new ArrayList<CuentaDto>();
		CuentaDto cuentaDto=new CuentaDto();
		for(Cuenta e:  cuentaRepository.findAll()) {
			mapper.map(e, cuentaDto);
			listCuentaDto.add(cuentaDto);
		}
		
		return listCuentaDto;
	}

	@Override
	public void eliminarCuenta(Long id) {
		cuentaRepository.deleteById(id);
	}

}

