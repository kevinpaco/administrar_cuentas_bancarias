package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class MovimientoDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Long id;
	
	private LocalDate fecha;
	
	private LocalTime hora;
	
	private String tipo;
	
	private double importe;
	
	private CuentaDto cuentaDto;
	
	private ClienteDto clienteDto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	
	
	public CuentaDto getCuentaDto() {
		return cuentaDto;
	}

	public void setCuentaDto(CuentaDto cuentaDto) {
		this.cuentaDto = cuentaDto;
	}

	public ClienteDto getClienteDto() {
		return clienteDto;
	}

	public void setClienteDto(ClienteDto clienteDto) {
		this.clienteDto = clienteDto;
	}

	@Override
	public String toString() {
		return "MovimientoDto [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", tipo=" + tipo + ", importe="
				+ importe + "]";
	}

	
}
