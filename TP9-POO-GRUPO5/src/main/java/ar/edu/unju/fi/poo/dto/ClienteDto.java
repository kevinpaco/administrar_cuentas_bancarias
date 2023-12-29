package ar.edu.unju.fi.poo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteDto implements Serializable {

	/**
	 * Clase Cliente DTO
	 */
	private static final long serialVersionUID = 1L;
 
    private Long id;
	
	private long dni;
	
	private String nombre;
	
	private String email;
	
	private String contrasenia;
	
	private String domicilio;
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	private String estado;
	
	private CuentaDto cuenta;
	
	private List<ClienteDto> adherentes = new ArrayList<ClienteDto>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public CuentaDto getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDto cuenta) {
		this.cuenta = cuenta;
	}

	public List<ClienteDto> getAdherentes() {
		return adherentes;
	}

	public void setAdherentes(List<ClienteDto> adherentes) {
		this.adherentes = adherentes;
	}
	public void setAdherente(ClienteDto adherente) {
		this.adherentes.add(adherente);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ClienteDto [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", domicilio="
				+ domicilio + ", estado=" + estado + ", cuenta=" + cuenta + ", adherentes=" + adherentes + "]";
	}
	
	
	
}
