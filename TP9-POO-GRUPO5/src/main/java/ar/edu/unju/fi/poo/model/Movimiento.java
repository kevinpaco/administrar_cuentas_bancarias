package ar.edu.unju.fi.poo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


@Entity
@Table
public class Movimiento {
 
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	
	private LocalTime hora;
	
	private String tipo;
	
	private double importe;
    @OneToOne(fetch = FetchType.EAGER)
    private Cuenta cuenta;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Cliente cliente;
    
	public Movimiento() {
		super();
	}

	public Movimiento(LocalDate fecha, LocalTime hora, String tipo, double importe,Cliente cliente) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.tipo = tipo;
		this.importe = importe;
		this.cliente = cliente;
	}

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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", tipo=" + tipo + ", importe="
				+ importe + ", cuenta=" + cuenta + ", cliente=" + cliente + "]";
	}

	

		
}
