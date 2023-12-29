package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Cuenta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private int numero;
	
	private LocalDate fechaIngreso;
	
	private double saldoActual;
	
	private String estado;
	
	private double limiteExtraccion;
	
	@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, mappedBy = "cuenta")
	private List<Movimiento> movimientos = new ArrayList<Movimiento>();

	public Cuenta() {
		super();
	}

	public Cuenta(int numeroCuenta, LocalDate fechaIngreso, double saldoActual, String estado,
			double limiteExtraccion) {
		super();
		this.numero = numeroCuenta;
		this.fechaIngreso = fechaIngreso;
		this.saldoActual = saldoActual;
		this.estado = estado;
		this.limiteExtraccion = limiteExtraccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumeroCuenta() {
		return numero;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numero = numeroCuenta;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getLimiteExtraccion() {
		return limiteExtraccion;
	}

	public void setLimiteExtraccion(double limiteExtraccion) {
		this.limiteExtraccion = limiteExtraccion;
	}


	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", numero=" + numero + ", fechaIngreso=" + fechaIngreso + ", saldoActual="
				+ saldoActual + ", estado=" + estado + ", limiteExtraccion=" + limiteExtraccion  + "]";
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

}


