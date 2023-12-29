package ar.edu.unju.fi.poo.model;

import java.util.ArrayList; 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private long dni;
	
	private String nombre;
	
	private String email;
	
	private String contrasenia;
	
	private String domicilio;
	
	private String estado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Cuenta_Id")
	private Cuenta cuenta;
	
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "titular")
	private List<Cliente> adherentes = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "Titular_id")
	private Cliente titular;
	
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,mappedBy = "cliente")
	private List<Movimiento> movimientos = new ArrayList<>();

	
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Cliente() {
		super();
	}

	public Cliente(long dni, String nombre, String email, String domicilio, String estado, Cuenta cuenta,
			List<Cliente> adherentes) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.domicilio = domicilio;
		this.estado = estado;
		this.cuenta = cuenta;
		this.adherentes = adherentes;
	}

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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Cliente> getAdherentes() {
		return adherentes;
	}

	public void setAdherentes(List<Cliente> adherentes) {
		this.adherentes = adherentes;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", email=" + email + ", contrasenia="
				+ contrasenia + ", domicilio=" + domicilio + ", estado=" + estado + ", cuenta=" + cuenta
				+ ", adherentes=" + adherentes + "]";
	}

	
	
	

	
}

